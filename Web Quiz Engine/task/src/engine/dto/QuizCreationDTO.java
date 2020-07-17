package engine.dto;

import java.util.ArrayList;

public class QuizCreationDTO {

    private String title;
    private String text;
    private ArrayList<String> options;
    private ArrayList<Integer> answer;

    public QuizCreationDTO(String title, String text, ArrayList<String> options, ArrayList<Integer> answer) {
        this.title = title;
        this.text = text;
        this.options = options;
        this.answer = answer;
    }

    public QuizCreationDTO() {
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public ArrayList<String> getOptions() {
        return options;
    }

    public void setOptions(ArrayList<String> options) {
        this.options = options;
    }

    public ArrayList<Integer> getAnswer() {
        return answer;
    }

    public void setAnswer(ArrayList<Integer> answer) {
        this.answer = answer;
    }
}
