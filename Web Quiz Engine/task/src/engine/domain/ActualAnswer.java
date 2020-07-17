package engine.domain;

import javax.persistence.*;

@Entity
@Table(name = "actual_answer")
public class ActualAnswer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "answer_id")
    private Long id;

    @Column(name = "answer")
    private int answer;

    @ManyToOne(targetEntity = Quiz.class)
    @JoinColumn(name = "quiz_id")
    private Quiz quiz;


    public ActualAnswer() {
    }

    public ActualAnswer(int answer) {
        this.answer = answer;
    }

    public ActualAnswer(int answer, Quiz quiz) {
        this.answer = answer;
        this.quiz = quiz;
    }

    public int getAnswer() {
        return answer;
    }

    public void setAnswer(int answer) {
        this.answer = answer;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Quiz getQuiz() {
        return quiz;
    }

    public void setQuiz(Quiz quiz) {
        this.quiz = quiz;
    }
}
