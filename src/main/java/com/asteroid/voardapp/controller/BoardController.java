package com.asteroid.voardapp.controller;

import com.asteroid.voardapp.model.Board;
import com.asteroid.voardapp.service.BoardServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/api/v1")
public class BoardController {
    @Autowired
    BoardServiceImpl boardService;  // 게시판 서비스 생성

    /**
     * 게시글 목록을 반환한다.
     * @param search_word 검색어(제목, 본문 포함)
     * @return 게시글 목록
     */
    @GetMapping("/board")
    public List<Board> getBoardList(String search_word) {
        List<Board> board_list = boardService.getBoardList(search_word);
        return board_list;
    }

    /**
     * 게시글 상세정보를 반환한다.
     * @param board_no 게시글 번호
     * @return 해당 번호의 게시글 상세정보
     */
    @GetMapping("/board/{board_no}")
    public List<Board> getBoardDetail(
            @PathVariable(name = "board_no") Integer board_no) {
        List<Board> board_info = boardService.getBoardDetail(board_no);
        return board_info;
    }

    /**
     * 게시글을 등록한다.
     * @param board 게시글 정보 (제목, 작성자, 본문)
     */
    @PostMapping("/board")
    public void insertBoardList(
            @RequestBody Board board) {
        boardService.insertBoardDetail(board.getBoard_title(), board.getBoard_user_id(), board.getBoard_content());
    }

    /**
     * 게시글을 수정한다.
     * @param board 게시글 정보(번호, 제목, 작성자, 본문)
     */
    @PatchMapping("/board/{board_no}")
    public void updateBoardList(
            @PathVariable(name = "board_no") Integer board_no,
            @RequestBody Board board) {
        boardService.updateBoardDetail(board_no, board.getBoard_title(), board.getBoard_user_id(), board.getBoard_content());
    }

    /**
     * 게시글을 삭제한다.
     * @param board_no 게시글 번호
     */
    @DeleteMapping("/board/{board_no}")
    public void deleteBoardList(
            @PathVariable(name = "board_no") Integer board_no) {
        boardService.deleteBoardDetail(board_no);

        
    }
}
