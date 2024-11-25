package advice.teamproject.domain.repository.member;


import advice.teamproject.domain.entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

// MemberRepository를 extends했기 때문에, RegisterService 의 코드 수정 없이 교체 가능
public interface DbMemberRepository extends JpaRepository<Member, Long>, MemberRepository {

	@Override
    Optional<Member> findByEmail(String email); // 이메일로도 찾을수 있게 추가

}