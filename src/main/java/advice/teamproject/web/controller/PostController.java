package advice.teamproject.web.controller;


import advice.teamproject.domain.entity.Member;
import advice.teamproject.domain.entity.Post;
import advice.teamproject.domain.service.PostService;
import advice.teamproject.dto.PostForm;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.Set;

@Slf4j
@Controller
@RequiredArgsConstructor
@RequestMapping("/posts")
public class PostController {

    private final PostService postService;

    // 여기다가 로그인 되어있는지 확인하는 로직 listPosts 랑 viewPost 중에어디에 넣을지 투표 ㄱㄱ

    // 목록 페이지
    @GetMapping
    public String listPosts(Model model) {

        List<Post> posts = postService.findPosts();
        model.addAttribute("posts", posts);
        return "/post/posts";
    }

    // 게시글 상세 페이지 (리팩토링 1순위) TODO
    @GetMapping("/{postId}")
    public String viewPost(@SessionAttribute(name = "loginMember", required = false) Member loginMember, @PathVariable Long postId, Model model) {

        Optional<Post> postOptional = postService.findPost(postId);
        if (postOptional.isPresent()) {
            Post post = postOptional.get();
            // 만약에 참여버튼을 눌렀거나, 자리가 있는 경우만
            Set<Member> participants = post.getMembers();
            if (participants.contains(loginMember) || postService.hasSpaceForParticipants(postId)) {
                model.addAttribute("post", post);
            } else {
                // 여기서 null 넣지 말고 예외메시지 하기 TODO
                // 참여 자리가 가득 찼습니다.
                model.addAttribute("post", null);
            }
        } else {
            // 여기서 null 넣지 말고 예외메시지 하기 TODO
            // 없는 게시물입니다.
            model.addAttribute("post", null);
        }
        return "/post/post"; // 템플릿 리턴
    }


    // 게시글 추가 폼
    @GetMapping("/add")
    public String addPostForm(Model model) {
        model.addAttribute("postForm", new PostForm());
        return "/post/addForm";
    }

    // 게시글 저장
    @PostMapping("/add")
    public String savePost(@SessionAttribute(name = "loginMember", required = false) Member loginMember, @ModelAttribute PostForm postForm) {
        // 세션으로 넘어온 쿠키를 바탕으로 session 저장소에서 member 를 찾아옴, 거기서 email 을 꺼내서 post 에 저장해둠

        if (loginMember == null) {
            return "redirect:/login";
        }

        // postForm 은 제목이랑, 내용만 있기 때문에, post 를 새로 만들어서 넣어줌 + email + maxParticipants 도 넣음(작성자를 기억하기 위함)
        Post post = new Post(postForm.getTitle(), postForm.getContent());
        post.setAuthorEmail(loginMember.getEmail());
        post.setMaxParticipants(postForm.getMaxParticipants());

        Post savedPost = postService.save(post);
        return "redirect:/posts/" + savedPost.getId();
    }

    // 게시글 수정 폼 (이거 설정 인원도 수정할 수 있게 해야되긴 해;; ) TODO
    @GetMapping("/{postId}/edit")
    public String editPostForm(@SessionAttribute(name = "loginMember", required = false) Member loginMember, @PathVariable Long postId, Model model) {

        // 게시글이 존재하고, 작가와 수정하려는 사람이 같은지 확인
        Optional<Post> postoptional = postService.findPost(postId);
        if (postoptional.isPresent() && postoptional.get().getAuthorEmail().equals(loginMember.getEmail())) {
            model.addAttribute("post", postoptional.get());
        } else {
            // 지금은 500에러 뜸
            // 이부분에 , 글로벌 에러 만드는거 (작성자만 수정할 수 있습니다.) 이렇게 띄우기
            // 그리고 posts 로 redirect
            model.addAttribute("post", null);
        }
        return "/post/editForm";
    }

    // 게시글 수정 (이때, 참가인원 제한도 고칠수 있게 할건지 결정) TODO
    @PostMapping("/{postId}/edit")
    public String updatePost(@PathVariable Long postId, PostForm postForm) {
        Post post = new Post(postForm.getTitle(), postForm.getContent());
        postService.editPost(postId, post);
        return "redirect:/posts/" + postId;
    }

    @PostMapping("/{postId}/delete")
    public String deletePost(@SessionAttribute(name = "loginMember", required = false) Member loginMember, @PathVariable Long postId) {
        // 게시글이 존재하고, 작가와 삭제하려는 사람이 같은지 확인
        Optional<Post> postoptional = postService.findPost(postId);
        if (postoptional.isPresent() && postoptional.get().getAuthorEmail().equals(loginMember.getEmail())) {
            postService.deletePost(postId);
        } else {
            return "redirect:/posts/" + postId;
        }
        return "redirect:/posts";
    }

    // 참여버튼 눌렀을 때
    @PostMapping("/{postId}/participate")
    public String participatePost(@SessionAttribute(name = "loginMember", required = false) Member loginMember, @PathVariable Long postId) {
        if (loginMember == null) {
            return "redirect:/login";
        }
        postService.addParticipants(postId, loginMember);
        return "redirect:/posts/" + postId;
    }
}
