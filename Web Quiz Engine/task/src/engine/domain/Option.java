package engine.domain;

import javax.persistence.*;

@Entity
@Table(name = "option_table")
public class Option {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "option_id")
    private Long id;

    @Column(name = "option")
    private String option;

    @ManyToOne(targetEntity = Quiz.class)
    @JoinColumn(name = "quiz_id")
    private Quiz quiz;

    public Option(String option) {
        this.option = option;
    }

    public Option(String option, Quiz quiz) {
        this.option = option;
        this.quiz = quiz;
    }

    public Option() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getOption() {
        return option;
    }

    public void setOption(String option) {
        this.option = option;
    }

    public Quiz getQuiz() {
        return quiz;
    }

    public void setQuiz(Quiz quiz) {
        this.quiz = quiz;
    }
}
