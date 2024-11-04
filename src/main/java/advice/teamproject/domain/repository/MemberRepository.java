package advice.teamproject.domain.repository;

import advice.teamproject.domain.entity.Member;

import java.util.List;

public interface MemberRepository {

    public Member save(Member member);
    public Member findByEmail(String email);
    public List<Member> findAll();
}
