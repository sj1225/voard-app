package com.asteroid.voardapp.controller;

import com.asteroid.voardapp.model.Board;
import com.asteroid.voardapp.service.BoardServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class BoardController {
    @Autowired
    BoardServiceImpl boardService;

    @GetMapping("/api/v1/board")
    public List<Board> getBoardList() {
        List<Board> brd = boardService.getBoardList();
        return brd;
    }

    @GetMapping("/api/v1/board/search")
    public List<Board> getBoardListSearch(
            @RequestParam String search_word) {
        List<Board> brd = boardService.getBoardListSearch(search_word);
        return brd;
    }

    @GetMapping("/api/v1/board/{board_no}")
    public List<Board> getAllBoardList(@PathVariable(name = "board_no") Integer board_no) {
        List<Board> brd = boardService.getBoardInfo(board_no);
        return brd;
    }

    @PostMapping("/api/v1/board")
    public void insertBoardList(
            @RequestBody Board brdVo) {
        boardService.insertBoardInfo(brdVo.getBoard_title(), brdVo.getBoard_user_id(), brdVo.getBoard_content());
    }

    @PatchMapping("/api/v1/board")
    public void updateBoardList(
            @RequestBody Board brdVo) {
        boardService.updateBoardInfo(brdVo.getBoard_no(), brdVo.getBoard_title(), brdVo.getBoard_user_id(), brdVo.getBoard_content());
    }

    @DeleteMapping("/api/v1/board")
    public void deleteBoardList(
            @RequestParam Integer board_no, @RequestParam String board_user_id) {
        boardService.deleteBoardInfo(board_no, board_user_id);
    }
}
