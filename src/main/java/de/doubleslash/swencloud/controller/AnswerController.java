package de.doubleslash.swencloud.controller;

import de.doubleslash.swencloud.dao.Answer;
import de.doubleslash.swencloud.dao.Question;
import de.doubleslash.swencloud.dto.AnswerDto;
import de.doubleslash.swencloud.repository.ParticipantRepository;
import de.doubleslash.swencloud.repository.QuestionRepository;
import java.util.Set;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

@RestController
@RequestMapping("/rooms/{roomIdentifier}/questions/{questionId}/answers")
public class AnswerController {

  @Autowired
  private QuestionRepository questionRepository;

  @Autowired
  private ParticipantRepository participantRepository;

  @GetMapping
  public Set<Answer> findAll(@PathVariable String roomIdentifier, @PathVariable Long questionId) {
    return findQuestion(questionId, roomIdentifier).getAnswers();
  }

  @PostMapping
  public Answer add(@PathVariable String roomIdentifier, @PathVariable Long questionId, AnswerDto ans) {
    var question = findQuestion(questionId, roomIdentifier);
    var answer = new Answer();
    answer.setAnswer(ans.getAnswer());
    answer.setTrue(ans.isTrue());
    question.addAnswer(answer);
    questionRepository.save(question);
    return answer;
  }

  @PostMapping("{answerId}/participants/{id}")
  public Answer addParticipant(
      @PathVariable String roomIdentifier,
      @PathVariable Long questionId,
      @PathVariable String answerId,
      @PathVariable long id) {

    var participant = participantRepository.findById(id)
        .orElseThrow(() ->
            new ResponseStatusException(HttpStatus.NOT_FOUND,
                "Unable to find Participant with id: " + id));

    var question = findQuestion(questionId, roomIdentifier);
    var answer = question.getAnswers().stream()
        .filter(a -> a.getId().equals(answerId))
        .findFirst()
        .orElseThrow(() ->
            new ResponseStatusException(HttpStatus.NOT_FOUND,
                "Unable to find answer with id: " + answerId + " belonging to question with identifier: "
                    + questionId));
    answer.addParticipant(participant);
    return answer;
  }

  private Question findQuestion(Long questionId, String roomIdentifier) throws ResponseStatusException {
    return questionRepository
        .findById(questionId)
        .filter(q -> q.getRoom() != null)
        .filter(q -> q.getRoom().getRoomIdentifier().equals(roomIdentifier))
        .orElseThrow(() ->
            new ResponseStatusException(HttpStatus.NOT_FOUND,
                "Unable to find question with id: " + questionId + " belonging to room with identifier: "
                    + roomIdentifier));
  }
}
