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
}
