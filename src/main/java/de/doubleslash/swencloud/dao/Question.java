package de.doubleslash.swencloud.dao;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
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
@Table(name = "question")
public class Question {

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private Long id;

  @ManyToOne
  @JoinColumn(name="room_id")
  @JsonBackReference
  private Room room;

  @Column(name = "question", nullable = false)
  private String question;

  @OneToMany(fetch = FetchType.EAGER, mappedBy = "id")
  private Set<Answer> answers = new HashSet<>();

  @JsonIgnore
  public void addAnswer(Answer answer) {
    answers.add(answer);
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) {
      return false;
    }
    Question q = (Question) o;
    return id != null && Objects.equals(id, q.id);
  }

  @Override
  public int hashCode() {
    return getClass().hashCode();
  }
}
