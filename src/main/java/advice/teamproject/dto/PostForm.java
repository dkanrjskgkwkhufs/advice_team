package advice.teamproject.dto;


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

    public PostForm() {

    }
    public PostForm(String title, String content, int maxParticipants) {
        this.title = title;
        this.content = content;
        this.maxParticipants = maxParticipants;
    }
}
