package engine.dto;

import java.util.ArrayList;

public class QuizDTO {

    private long id;
    private String title;
    private String text;
    private ArrayList<String> options;

    public QuizDTO(long id, String title, String text, ArrayList<String> options) {
        this.id = id;
        this.title = title;
        this.text = text;
        this.options = options;
    }

    public QuizDTO() {
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
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
}
