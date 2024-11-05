package advice.teamproject.domain.repository.member;


import advice.teamproject.domain.entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface DbMemberRepository extends JpaRepository<Member, Long>, MemberRepository {

	@Override
    Optional<Member> findByEmail(String email);

}