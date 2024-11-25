package advice.teamproject.domain.repository.post;

import advice.teamproject.domain.entity.Post;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class DbPostRepositoryTest {

    @Autowired
    private DbPostRepository repository;

    @Test
    public void saveTest() {

        // given
        Post post1 = new Post();
        post1.setTheme("indoor");

        Post post2 = new Post();
        post2.setTheme("outdoor");

        // when
        repository.save(post1);
        repository.save(post2);

        // then
        repository.findAll().forEach(System.out::println);
        System.out.println("------------------");
        repository.findByTheme("indoor").forEach(System.out::println);
        System.out.println("------------------");
        repository.findByTheme("outdoor").forEach(System.out::println);
    }
}
