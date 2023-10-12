package com.hs.selab.post.presentaion.ui;

import com.hs.selab.board.application.BoardService;
import com.hs.selab.post.application.PostService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequiredArgsConstructor
public class PostController {
    private final BoardService boardService;
    private final PostService postService;



    @GetMapping("/posts")
    public String createPost(Model model){
        var boards =boardService.getAll();

        model.addAttribute("boards", boards);
        return "post/new_post";
    }

}
