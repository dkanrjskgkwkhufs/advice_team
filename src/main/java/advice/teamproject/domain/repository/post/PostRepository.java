package advice.teamproject.domain.repository.post;


import advice.teamproject.domain.entity.Post;
import org.springframework.ui.context.Theme;

import java.util.List;
import java.util.Optional;

public interface PostRepository {

//    Post save(Post post);
//	Optional<Post> findById(Long id);
//	List<Post> findAll();
//	void deleteById(Long id);
	List<Post> findByTheme(String theme);
}
