package advice.teamproject.domain.repository.post;

import advice.teamproject.domain.entity.Post;
import org.springframework.data.jpa.repository.JpaRepository;



public interface DbPostRepository extends JpaRepository<Post, Long>, PostRepository {

}
