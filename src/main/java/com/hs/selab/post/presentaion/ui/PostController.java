package com.hs.selab.post.presentaion.ui;

import com.hs.selab.auth.domain.UserDetail;
import com.hs.selab.auth.infrastructure.annotation.AuthMember;
import com.hs.selab.board.application.BoardService;
import com.hs.selab.post.application.PostService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

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

    @GetMapping("/viewPost/{id}")
    public String getPost(@PathVariable("id") Long id) {
        System.out.println(id);

        return "post/view-post";
    }

    @GetMapping("/updatePost/{id}")
    public String updatePost(@PathVariable("id") Long id,
        Model model) {

        var boards =boardService.getAll();
        model.addAttribute("boardId", id);
        model.addAttribute("boards", boards);
        return "post/update-post";
    }

    @GetMapping("/view/{id}")
    public String view(
        @PathVariable("id") Long id,
        @AuthMember UserDetail userInfo,
        Model model){

        var post = postService.get(id, userInfo);
        model.addAttribute("post",post);

        return "post/view-post";
    }
}