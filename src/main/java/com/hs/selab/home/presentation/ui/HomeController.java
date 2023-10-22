package com.hs.selab.home.presentation.ui;

import com.hs.selab.auth.domain.UserDetail;
import com.hs.selab.auth.infrastructure.annotation.AuthMember;
import com.hs.selab.board.application.BoardService;
import com.hs.selab.home.application.HomeService;
import com.hs.selab.post.application.PostService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
@RequiredArgsConstructor
public class HomeController {
    private final HomeService homeService;
    private final BoardService boardService;
    private final PostService postService;

    @GetMapping(path = {"/", "/home"})
    public String home(
            @AuthMember UserDetail userInfo,
            @PageableDefault(sort = "id", direction = Sort.Direction.DESC) Pageable pageable,
            Model model
    ) {
        var response = homeService.getHome(userInfo, pageable);
        model.addAttribute("home", response);
        return "home";
    }
}
