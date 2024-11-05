package advice.teamproject.domain.repository;

import advice.teamproject.domain.entity.Member;
import advice.teamproject.domain.repository.member.MemberRepository;
import advice.teamproject.domain.repository.member.MemoryMemberRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

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