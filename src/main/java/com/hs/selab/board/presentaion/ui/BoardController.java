package com.hs.selab.board.presentaion.ui;

import com.hs.selab.board.application.BoardService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequiredArgsConstructor
@RequestMapping("/boards")
public class BoardController {
    private final BoardService boardService;

    @GetMapping("/list")
    public String boardList(
        Model model
    ){
        var list = boardService.getAll();
        model.addAttribute("list", list);
        return "board/board_list";
    }
}
