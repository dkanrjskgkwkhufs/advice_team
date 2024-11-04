package advice.teamproject.domain.repository;

import advice.teamproject.domain.entity.Member;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class MemoryMemberRepositoryTest {

    MemberRepository memberRepository =  new MemoryMemberRepository();

    @Test
    public void save(){
        // given
        Member member = new Member();
        member.setEmail("asdf@gmail.com");

        // when
        Member savedMember = memberRepository.save(member);

        // then
        Assertions.assertThat(savedMember.getEmail()).isEqualTo(member.getEmail());
    }

}