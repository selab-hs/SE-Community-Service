package com.hs.selab.post.presentaion.ui;

import com.hs.selab.post.application.PostService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequiredArgsConstructor
@RequestMapping("/post")
public class PostController {
    private final PostService postService;



}
