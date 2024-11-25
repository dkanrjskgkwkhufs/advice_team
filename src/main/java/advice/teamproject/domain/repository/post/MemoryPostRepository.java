package advice.teamproject.domain.repository.post;


import advice.teamproject.domain.entity.Post;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;
import org.springframework.ui.context.Theme;

import java.util.*;

@Slf4j
//@Repository
public class MemoryPostRepository implements PostRepository {


    private static Map<Long, Post> store = new HashMap<>();
    private static long sequence = 0L;


//    @Override
//    public Post save(Post post) {
//        if (post.getId() == null) { // update하는 경우에도 save메서드를 사용하기 위함
//            post.setId(++sequence);
//        }
//        store.put(post.getId(), post);
//        return post;
//    }
//
//    @Override
//    public Optional<Post> findById(Long id) {
//        return Optional.ofNullable(store.get(id));
//    }
//
//
//    @Override
//    public List<Post> findAll() {
//        return new ArrayList<>(store.values());
//    }
//
//    @Override
//    public void deleteById(Long id) {
//        store.remove(id);
//    }

    @Override
    public List<Post> findByTheme(String theme) {
        List<Post> posts = new ArrayList<>();
        for (Post value : store.values()) {
            if (value.getTheme().equals(theme)) {
                posts.add(value);
            }
        }
        return posts;
    }

}
