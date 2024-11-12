package advice.teamproject.domain.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;
import java.util.Set;

@Entity
@Getter
@Setter
public class Post{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String title;
    private String content;
    private String authorEmail;
    private int maxParticipants;
    // 이거 안하면 한사람이 하나의 게시물에만 participants 할 수 있음
    @ManyToMany
    @JoinTable(
        name = "post_members",
        joinColumns = @JoinColumn(name = "post_id"),
        inverseJoinColumns = @JoinColumn(name = "members_id"),
        uniqueConstraints = @UniqueConstraint(columnNames = {"post_id", "members_id"})
    )
    private Set<Member> members;
    private String theme;

    public Post() {
    }

    public Post(String title, String content) {
        this.title = title;
        this.content = content;
    }
}
