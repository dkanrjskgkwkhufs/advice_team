package advice.teamproject.domain.service;


import advice.teamproject.domain.entity.Member;
import advice.teamproject.domain.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;


@Slf4j
@Service
@RequiredArgsConstructor
public class RegisterService {
    private final MemberRepository memberRepository;

    public Member join(Member member) {
        return memberRepository.save(member);
    }
    public Member findMember(String email){
        return memberRepository.findByEmail(email);
    }

    public List<Member> memberList(){
        return memberRepository.findAll();
    }
}
