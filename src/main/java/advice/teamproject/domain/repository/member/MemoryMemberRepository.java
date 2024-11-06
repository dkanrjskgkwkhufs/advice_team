package advice.teamproject.domain.repository.member;


import advice.teamproject.domain.entity.Member;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;

import java.util.*;


@Slf4j
// @Repository

// 그냥 참고만 DbMemberRepository 도 내부적으로 동일한 method 이름 사용함(이 코드 원리랑 유사함)

public class MemoryMemberRepository implements MemberRepository {

	private static Map<Long, Member> store = new HashMap<>();
	private static long sequence = 0L;

	// 회원 저장시 id를 하나씩 증가하면서 저장함( member 엔티티에
	//  @Id
	//	@GeneratedValue(strategy = GenerationType.IDENTITY)
	//  이렇게 했을 때, spring에서 내부적으로 해주는 로직이랑 유사
	@Override
	public Member save(Member member) {
		member.setId(++sequence);
		store.put(member.getId(), member);
		return member;
	}

	// 아이디로 찾기
	@Override
	public Optional<Member> findById(Long id) {
		return Optional.ofNullable(store.get(id));
	}

	// 이메일로 찾기
	@Override
	public Optional<Member> findByEmail(String email) {
		return store.values().stream()
				.filter(member -> member.getEmail().equals(email))
				.findAny();
	}

	// 전체 조회
	@Override
	public List<Member> findAll() {
		return new ArrayList<>(store.values());
	}

	// 저장소 리셋(사용자가 사용할 일 없음)
	public void clearStore() {
		store.clear();
	}
}