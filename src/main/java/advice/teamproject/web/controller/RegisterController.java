package advice.teamproject.web.controller;

import advice.teamproject.domain.entity.Member;
import advice.teamproject.domain.service.MemberService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
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
    public String signup(Model model) {
        model.addAttribute("member", new Member());
        return "signup";
    }

    // 나중에는 로그인 페이지로 리다이렉트 되게
    @PostMapping
    public String save(@ModelAttribute("member") @Valid Member member, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "signup";
        }
        try {
            // 회원가입 처리
            memberService.join(member);
        } catch (DataIntegrityViolationException e) {
            e.printStackTrace();
            // 예외가 발생했을 때 이메일 중복 체크를 위한 오류 메시지 추가
            bindingResult.reject("signupFailed", "이미 등록된 사용자입니다.");
            return "signup"; // 다시 signup 페이지로 돌아가게 함
        }
        return "signupComplete";
    }
}