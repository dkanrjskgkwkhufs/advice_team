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
public class MemberService {

    private final MemberRepository memberRepository;

    // 가입 (저장)
    public Member join(Member member) {
        return memberRepository.save(member);
    }

    // 회원 1명 찾기 (id로 찾음, id는 member 객체가 만들어질때마다 1씩 자동으로 증가함)
    public Optional<Member> findMemberById(Long memberId) {
		return memberRepository.findById(memberId);
	}

    // 회원 1명 찾기 (Email 로 찾음, login 시에 입력 들어왔을때, member 객체 찾아오기 위함)
    public Optional<Member> findMemberByEmail(String email) {
        return memberRepository.findByEmail(email);
    }

    // 모든 멤버 조회(사용자가 사용할 일은 없음)
    public List<Member> findMembers() {
		return memberRepository.findAll();
	}

    // 없으면 null 반환
    public Member login(String email, String password) {
        return memberRepository.findByEmail(email).filter(m -> m.getPassword().equals(password))
                .orElse(null);
    }
}
