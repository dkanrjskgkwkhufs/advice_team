package advice.teamproject.member;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;

@RestController
public class SignUpController {


    @GetMapping("/user/signup")
    public String test(@RequestParam("username") String username) {
        return "나는 rlarbals";
    }
}
