package advice.teamproject.domain.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.Objects;

@Entity
@Getter
@Setter
public class Member {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(unique = true)
    private String username;
    @Column(unique = true)
    private String email;
    private String password;
    private String campus;

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true; // 같은 객체면 바로 true 반환
        if (obj == null || getClass() != obj.getClass()) return false; // null이거나, 클래스가 다르면 false
        Member member = (Member) obj;
        return email != null && email.equals(member.email); // email 비교
    }

    @Override
    public int hashCode() {
        return Objects.hash(email);
    }
}
