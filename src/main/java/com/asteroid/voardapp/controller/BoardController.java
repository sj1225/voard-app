package com.asteroid.voardapp.controller;

import com.asteroid.voardapp.mapper.BoardMapper;
import com.asteroid.voardapp.model.Board;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;
import java.util.List;

@RestController
@RequiredArgsConstructor
public class BoardController {
    @Autowired
    BoardMapper boardMapper;

    @GetMapping("/api/v1/board")
    public List<Board> getBoardList() {
//        Board board = new Board();
//
//        board.setBoard_no(1);
//        board.setBoard_title("게시판 테스트입니다!");
//        board.setBoard_writer("ADMIN");
//        board.setBoard_date(new Date());
//        board.setBoard_mdf_date(new Date());
//        board.setBoard_content("내용물");

        List<Board> brd = boardMapper.selectList();

        return brd;
    }
}
