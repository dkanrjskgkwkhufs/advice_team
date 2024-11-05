package advice.teamproject.domain.service;


import advice.teamproject.domain.entity.Member;
import advice.teamproject.domain.repository.member.MemberRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


@Slf4j
@Service
@RequiredArgsConstructor
public class RegisterService {
    private final MemberRepository memberRepository;

    public Member join(Member member) {
        return memberRepository.save(member);
    }
    public Optional<Member> findMember(Long memberId) {
		return memberRepository.findById(memberId);
	}

    public List<Member> findMembers() {
		return memberRepository.findAll();
	}
}
