package advice.teamproject.domain.service;

import advice.teamproject.domain.entity.Member;
import advice.teamproject.domain.repository.MemoryMemberRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;


class MemberServiceTest {
    private final RegisterService memberService = new RegisterService(new MemoryMemberRepository());

    @Test
    public void test() {

        // given
        Member member = new Member();
        member.setEmail("user@gmail.com");
        memberService.join(member);

        // when
        Member findMember = memberService.findMember("user@gmail.com");

        // then
        Assertions.assertThat(findMember).isEqualTo(member);

    }
  
}