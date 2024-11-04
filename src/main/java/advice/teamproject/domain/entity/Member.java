package advice.teamproject.domain.entity;

import lombok.Data;

@Data
public class Member {
    private Long id;

    private String username;
    private String email;
    private String password;

    private char campus;
}
