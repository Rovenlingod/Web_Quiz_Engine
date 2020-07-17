package engine.domain;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "completion_table")
public class Completion {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "completion_id")
    private Long id;

    @JoinColumn(name = "user_id")
    @ManyToOne(targetEntity = User.class)
    private User user;

    @JoinColumn(name = "quiz_id")
    @ManyToOne(targetEntity = Quiz.class)
    private Quiz quiz;

    @Column(name = "date")
    private Date date;

    public Completion(User user, Quiz quiz, Date date) {
        this.user = user;
        this.quiz = quiz;
        this.date = date;
    }

    public Completion() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Quiz getQuiz() {
        return quiz;
    }

    public void setQuiz(Quiz quiz) {
        this.quiz = quiz;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }
}
