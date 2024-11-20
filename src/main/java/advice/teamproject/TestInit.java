package advice.teamproject;


import advice.teamproject.domain.entity.Member;
import advice.teamproject.domain.service.MemberService;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class TestInit {

    private final MemberService memberService;

    @PostConstruct
    public void init() {
        Member member1 = new Member();
        member1.setEmail("asdf@hufs.ac.kr");
        member1.setUsername("asdf");
        member1.setPassword("asdf");
        member1.setCampus("S");

        Member member2 = new Member();
        member2.setEmail("1234@hufs.ac.kr");
        member2.setUsername("1234");
        member2.setPassword("1234");
        member2.setCampus("C");

        memberService.join(member1);
        memberService.join(member2);
    }

}
