package com.hs.selab.member.presentaion.ui;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MemberController {

    @GetMapping("/login")
    public String memberLogin() {
        return "member/login";
    }

    @GetMapping("/sign-up")
    public String memberSignUp() {
        return "member/sign-up";
    }

    @GetMapping("/edit")
    public String memberEdit() {
        return "member/edit";
    }
}
