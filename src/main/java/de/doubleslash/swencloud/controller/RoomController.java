package de.doubleslash.swencloud.controller;

import de.doubleslash.swencloud.dao.Room;
import de.doubleslash.swencloud.repository.RoomRepository;
import java.util.List;
import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/rooms")
public class RoomController {

  @Autowired
  private RoomRepository roomRepository;

  @GetMapping
  public List<Room> findAll() {
    return (List<Room>) roomRepository.findAll();
  }

  @PostMapping
  public String add() {
    Room room = new Room();
    String rand = RandomStringUtils.randomAlphanumeric(8);
    room.setRoomIdentifier(rand);
    roomRepository.save(room);
    return rand;
  }
}
