package advice.teamproject.web.controller;

import advice.teamproject.domain.entity.Member;
import advice.teamproject.domain.service.RegisterService;
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

    private final RegisterService memberService;

    @GetMapping //그냥 signup 파일
    public String signup() {
        return "signup";
    }

    @PostMapping("/add")
    public String save(@ModelAttribute Member member, Model model) {
        Member savedMember = memberService.join(member);
        model.addAttribute("member", savedMember);
        return "/test/signupView";
    }

    // 지울 것 test용
    @GetMapping("/list")
    public String view(Model model) {
        List<Member> members = memberService.memberList();
        model.addAttribute("members", members);

        return "/test/memberList";
    }
}
