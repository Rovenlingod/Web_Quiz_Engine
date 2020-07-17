package engine.dto;

import java.util.ArrayList;

public class Answer {

    private ArrayList<Integer> answer;

    public Answer(ArrayList<Integer> answer) {
        this.answer = answer;
    }

    public Answer() {
    }

    public ArrayList<Integer> getAnswer() {
        return answer;
    }

    public void setAnswer(ArrayList<Integer> answer) {
        this.answer = answer;
    }
}
