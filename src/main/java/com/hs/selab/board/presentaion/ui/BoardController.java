package com.hs.selab.board.presentaion.ui;

import com.hs.selab.board.application.BoardService;
import com.hs.selab.post.application.PostService;
import java.util.ArrayList;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
@RequiredArgsConstructor
public class BoardController {
    private final BoardService boardService;
    private final PostService postService;

    @GetMapping("/boards")
    public String boardList(
        @PageableDefault(sort = "id", direction = Direction.DESC) Pageable pageable,
            Model model
    ) {
        var list = boardService.getAll();
        var post = new ArrayList<>();

        var postsB = postService.getAll(pageable, list.get(1).getId());
        var postsA = postService.getAll(pageable, list.get(0).getId());
        post.add(postsA);
        post.add(postsB);

        model.addAttribute("posts", post);
        model.addAttribute("list", list);

        return "board/board_list";
    }

    @GetMapping("/boards/{id}")
    public String viewBoardList(
        @PathVariable("id") Long id,
        @PageableDefault(sort = "id", direction = Direction.DESC) Pageable pageable,
        Model model
    ) {
        var posts = postService.getAll(pageable, id);
        model.addAttribute("post", posts);

        return "board/view-board-list";
    }
}
