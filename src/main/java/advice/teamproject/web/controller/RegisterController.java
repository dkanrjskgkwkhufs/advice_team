package advice.teamproject.web.controller;

import advice.teamproject.domain.entity.Member;
import advice.teamproject.domain.service.MemberService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;


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

    @PostMapping
    public String save(@ModelAttribute("member")Member member) {
        memberService.join(member);
        return "signupComplete";
    }

    // test용 지울것 TODO
//    @GetMapping("/list")
//    public String view(Model model) {
//        List<Member> members = memberService.findMembers();
//        model.addAttribute("members", members);
//
//        return "/test/memberList";
//    }
}
