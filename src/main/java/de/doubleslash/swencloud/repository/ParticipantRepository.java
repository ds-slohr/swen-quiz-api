package de.doubleslash.swencloud.repository;

import de.doubleslash.swencloud.dao.Participant;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ParticipantRepository extends CrudRepository<Participant, Long> {

}
