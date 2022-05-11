package de.doubleslash.swencloud.dao;

import com.fasterxml.jackson.annotation.JsonBackReference;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.Hibernate;


@Entity
@Getter
@Setter
@ToString
@RequiredArgsConstructor
@Table(name = "answer")
public class Answer {

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private Long id;

  @ManyToOne
  @JoinColumn(name = "question_id")
  @JsonBackReference
  private Question question;

  @ManyToMany(mappedBy = "answers")
  @JsonBackReference
  private Set<Participant> participants = new HashSet<>();

  @Column(name = "answer", nullable = false)
  private String answer;

  @Column(name = "is_true", nullable = false)
  private boolean isTrue;

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) {
      return false;
    }
    Answer a = (Answer) o;
    return id != null && Objects.equals(id, a.id);
  }

  @Override
  public int hashCode() {
    return getClass().hashCode();
  }

}
