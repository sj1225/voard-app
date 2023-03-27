package com.asteroid.voardapp.controller;

import com.asteroid.voardapp.mapper.BoardMapper;
import com.asteroid.voardapp.model.Board;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

@RestController
@RequiredArgsConstructor
public class BoardController {
    @Autowired
    BoardMapper boardMapper;

    @GetMapping("/api/v1/board")
    public List<Board> getBoardList() {
        List<Board> brd = boardMapper.getBoardList();
        return brd;
    }

    @GetMapping("/api/v1/board/{board_no}")
    public List<Board> getAllBoardList(@PathVariable(name = "board_no") Integer board_no) {
        List<Board> brd = boardMapper.getBoardInfo(board_no);
        return brd;
    }

    @PostMapping("/api/v1/board")
    public void insertBoardList(
            @RequestBody Board brdVo) {
        boardMapper.insertBoardInfo(brdVo.getBoard_title(), brdVo.getBoard_user_id(), brdVo.getBoard_content());
    }
}
