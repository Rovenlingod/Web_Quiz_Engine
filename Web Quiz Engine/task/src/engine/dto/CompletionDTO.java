package engine.dto;

public class CompletionDTO {

    private Long id;
    private String completedAt;

    public CompletionDTO() {
    }

    public CompletionDTO(Long id, String completedAt) {
        this.id = id;
        this.completedAt = completedAt;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCompletedAt() {
        return completedAt;
    }

    public void setCompletedAt(String completedAt) {
        this.completedAt = completedAt;
    }
}
