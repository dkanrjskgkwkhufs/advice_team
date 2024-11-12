package advice.teamproject.web.controller;


import advice.teamproject.domain.entity.Member;
import advice.teamproject.domain.service.MemberService;
import advice.teamproject.dto.LoginForm;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
@RequiredArgsConstructor
public class LoginController {

    private final MemberService memberService;

    // 로그인 페이지
    @GetMapping("/login")
    public String loginForm(@ModelAttribute LoginForm loginForm) {
        return "login";
    }


    @PostMapping("/login")
    public String login(@Valid @ModelAttribute LoginForm loginForm, BindingResult bindingResult, HttpServletRequest request) {

        // empty 잡기
        if (bindingResult.hasErrors()) {
            return "login";
        }

        Member loginMember = memberService.login(loginForm.getEmail(), loginForm.getPassword());

        // email 로 찾은 객체.password 랑 입력한 password 가 같은지 확인
        if (loginMember == null) {
            bindingResult.reject("loginFail", "이메일 또는 비밀번호가 맞지 않습니다.");
            return "login";
        }

        HttpSession session = request.getSession();// 세션이 없으면 만듬
        session.setAttribute("loginMember", loginMember);

        return "redirect:/board";
    }

    @PostMapping("/logout")
    public String logout(HttpServletRequest request) {
        HttpSession session = request.getSession(false);
        if (session != null) {
            session.invalidate();
        }
        return "redirect:/";
    }
}
