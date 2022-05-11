package de.doubleslash.swencloud.repository;

import de.doubleslash.swencloud.dao.Room;
import java.util.Optional;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoomRepository extends CrudRepository<Room, Long> {

  Optional<Room> findByRoomIdentifier(String roomIdentifier);
}
