package advice.teamproject.domain.repository;


import advice.teamproject.domain.entity.Member;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


@Slf4j
@Repository
public class MemoryMemberRepository implements MemberRepository{
    public static Map<String, Member> store = new HashMap<>();
    private static long sequence = 0L;


    @Override
    public Member save(Member member) {
        store.put(member.getEmail(), member);
        return member;
    }

    @Override
    public Member findByEmail(String email) {
        return store.get(email);
    }

    public List<Member> findAll() {
        return new ArrayList<>(store.values());
    }
}
