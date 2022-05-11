package de.doubleslash.swencloud.repository;

import de.doubleslash.swencloud.dao.Answer;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AnswerRepository extends CrudRepository<Answer, Long> {

}
