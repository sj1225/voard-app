package com.asteroid.voardapp.controller;

import com.asteroid.voardapp.model.Board;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;

@RestController
@RequiredArgsConstructor
public class BoardController {
    @GetMapping("/board")
    public Object getBoardList() {
        Board board = new Board();

        board.setBoard_no(1);
        board.setBoard_title("게시판 테스트입니다!");
        board.setBoard_writer("ADMIN");
        board.setBoard_date(new Date());
        board.setBoard_mdf_date(new Date());
        board.setBoard_content("내용물");

        return board;
    }
}
