package advice.teamproject.domain.service;

import advice.teamproject.domain.entity.Post;
import advice.teamproject.domain.repository.post.DbPostRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class PostServiceTest {

    @Autowired
    private DbPostRepository repository;

    @Test
    @DisplayName("findByTheme 테스트")
    public void findByThemeTest() {

        // given
        PostService postService = new PostService(repository);
        Post post1 = new Post();
        post1.setTheme("indoor");

        Post post2 = new Post();
        post2.setTheme("outdoor");

        // when
        postService.save(post1);
        postService.save(post2);

        // then
        postService.findPosts().forEach(System.out::println);
        System.out.println("------------------");
        postService.findPostsByTheme("indoor").forEach(System.out::println);
        System.out.println("------------------");
        postService.findPostsByTheme("outdoor").forEach(System.out::println);
    }
}