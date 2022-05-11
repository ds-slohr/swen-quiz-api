package de.doubleslash.swencloud.controller;

import de.doubleslash.swencloud.dao.Participant;
import de.doubleslash.swencloud.repository.ParticipantRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/participants")
public class ParticipantController {

  @Autowired
  private ParticipantRepository participantRepository;

  @GetMapping
  public List<Participant> findAll() {
    return (List<Participant>) participantRepository.findAll();
  }

  @PostMapping
  public Participant add(String name) {
    var participant = new Participant();
    participant.setName(name);
    return participantRepository.save(participant);
  }
}
