package com.core.service.member.presentaion.ui;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MemberController {

    @GetMapping("/login")
    public String memberLogin() {
        return "member/login";
    }

}
