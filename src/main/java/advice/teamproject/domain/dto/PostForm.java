package advice.teamproject.domain.dto;


import jakarta.validation.constraints.NotEmpty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PostForm {


    @NotEmpty
    private String title;

    @NotEmpty
    private String content;

    @NotEmpty
    private int maxParticipants;

    @NotEmpty
    private String theme;

    public PostForm() {

    }
    public PostForm(String title, String content, int maxParticipants) {
        this.title = title;
        this.content = content;
        this.maxParticipants = maxParticipants;
    }
}
