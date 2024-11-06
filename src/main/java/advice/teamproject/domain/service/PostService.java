package advice.teamproject.domain.service;


import advice.teamproject.domain.entity.Post;
import advice.teamproject.domain.repository.post.PostRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Slf4j
@Service
@RequiredArgsConstructor
public class PostService {

    private final PostRepository postRepository;

    public Post save(Post post) {
        return postRepository.save(post);
    }

    public Optional<Post> findPost(Long postId) {
        return postRepository.findById(postId);
    }

    public List<Post> findPosts() {
        return postRepository.findAll();
    }

    public void editPost(Long postId, Post updateParam) {
        Optional<Post> postOptional = postRepository.findById(postId);
        if (postOptional.isPresent()) {
            Post post = postOptional.get();
            post.setTitle(updateParam.getTitle());
            post.setContent(updateParam.getContent());
            postRepository.save(post);
        }
    }
}
