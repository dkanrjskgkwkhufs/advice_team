package advice.teamproject.domain.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Comment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String content;
    private String authorEmail;

    @ManyToOne
    @JoinColumn(name = "post_id")
    private Post post;
}
