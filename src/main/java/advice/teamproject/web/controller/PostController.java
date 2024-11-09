package advice.teamproject.web.controller;


import advice.teamproject.domain.entity.Post;
import advice.teamproject.domain.service.PostService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Slf4j
@Controller
@RequiredArgsConstructor
@RequestMapping("/posts")
public class PostController {

    private final PostService postService;


    // 목록 페이지
    @GetMapping
    public String listPosts(Model model) {

        // 여기에 로그인되어 있는지 체크하는 로직을 추가할 것;
        List<Post> posts = postService.findPosts();
        model.addAttribute("posts", posts);
        return "/post/posts";
    }

    // 게시글 상세 페이지
    @GetMapping("/{postId}")
    public String viewPost(@PathVariable Long postId, Model model) {

        Optional<Post> post = postService.findPost(postId);
        if (post.isPresent()) {
            model.addAttribute("post", post.get());
        } else {
            model.addAttribute("post", null);
        }
        return "/post/post"; // 템플릿 리턴
    }


    // 게시글 추가 폼
    @GetMapping("/add")
    public String addPostForm() {
        return "/post/addForm";
    }

    // 게시글 저장
    @PostMapping("/add")
    public String savePost(@ModelAttribute Post post) {
        Post savedPost = postService.save(post);
        return "redirect:/posts/" + savedPost.getId();
    }

    // 게시글 수정 폼
    @GetMapping("/{postId}/edit")
    public String editPostForm(@PathVariable Long postId, Model model) {
        // 여기서 작성한 사람과, 현재 로그인한 사람이 같은지 확인할 것


        Optional<Post> post = postService.findPost(postId);
        if (post.isPresent()) {
            model.addAttribute("post", post.get());
        } else {
            model.addAttribute("post", null);
        }
        return "/post/editForm";
    }

    // 게시글 수정
    @PostMapping("/{postId}/edit")
    public String updatePost(@PathVariable Long postId, Post post) {
        postService.editPost(postId, post);
        return "redirect:/posts/" + postId;
    }
}
