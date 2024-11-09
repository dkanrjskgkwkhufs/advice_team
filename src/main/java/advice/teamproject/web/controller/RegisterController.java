package advice.teamproject.web.controller;

import advice.teamproject.domain.entity.Member;
import advice.teamproject.domain.service.MemberService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;


@Slf4j
@Controller
@RequiredArgsConstructor
@RequestMapping("/signup")
public class RegisterController {

    private final MemberService memberService;

    @GetMapping //그냥 signup 폼
    public String signup() {
        return "signup";
    }

    // 나중에는 로그인 페이지로 리다이렉트 되게
    @PostMapping
    public String save(@ModelAttribute("member")Member member) {
        memberService.join(member);
        return "/test/signupView";
    }

    // test용
//    @GetMapping("/list")
//    public String view(Model model) {
//        List<Member> members = memberService.findMembers();
//        model.addAttribute("members", members);
//
//        return "/test/memberList";
//    }
}
