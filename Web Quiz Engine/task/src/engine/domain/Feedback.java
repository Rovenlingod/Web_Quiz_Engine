package engine.domain;

public class Feedback {

    private Boolean success;
    private String feedback;

    public Feedback(Boolean success, String feedback) {
        this.success = success;
        this.feedback = feedback;
    }

    public Feedback() {
    }

    public Boolean getSuccess() {
        return success;
    }

    public void setSuccess(Boolean success) {
        this.success = success;
    }

    public String getFeedback() {
        return feedback;
    }

    public void setFeedback(String feedback) {
        this.feedback = feedback;
    }
}
