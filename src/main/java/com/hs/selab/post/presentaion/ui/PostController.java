package com.hs.selab.post.presentaion.ui;

import com.hs.selab.post.application.PostService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequiredArgsConstructor
@RequestMapping("/post")
public class PostController {
    private final PostService postService;


    @GetMapping()
    public String getAllPosts(
        @PageableDefault(sort = "id", size = 15, direction = Direction.ASC)
        Pageable pageable,
        Model model
    ) {
        var posts = postService.getAll(pageable);
        model.addAttribute("postList",posts);
        return null;
    }

}
