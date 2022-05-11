package de.doubleslash.swencloud.controller;

import de.doubleslash.swencloud.dao.Question;
import de.doubleslash.swencloud.dao.Room;
import de.doubleslash.swencloud.repository.QuestionRepository;
import de.doubleslash.swencloud.repository.RoomRepository;
import java.util.Set;
import org.hibernate.Hibernate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

@RestController
@RequestMapping("/rooms/{roomIdentifier}/questions")
public class QuestionController {

  @Autowired
  private RoomRepository roomRepository;

  @Autowired
  private QuestionRepository questionRepository;

  @GetMapping
  public Set<Question> findAll(@PathVariable String roomIdentifier) {
    var room = findRoom(roomIdentifier);
    return room.getQuestions();
  }

  @PostMapping
  public Question add(@PathVariable String roomIdentifier, String questionString) {
    var question = new Question();
    question.setQuestion(questionString);
    question.setRoom(findRoom(roomIdentifier));
    questionRepository.save(question);
    return question;
  }

  private Room findRoom(String roomIdentifier) {
    return roomRepository
        .findByRoomIdentifier(roomIdentifier)
        .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,
            "unable to find room with identifier: " + roomIdentifier));
  }
}
