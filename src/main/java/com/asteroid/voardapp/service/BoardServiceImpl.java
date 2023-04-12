package com.asteroid.voardapp.service;

import com.asteroid.voardapp.mapper.BoardMapper;
import com.asteroid.voardapp.model.Board;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class BoardServiceImpl implements BoardService{
    @Autowired
    BoardMapper board;

    @Override
    public List<Board> getBoardList() {
        return board.getBoardList();
    };

    @Override
    public List<Board> getBoardListSearch(String search_word) {
        return board.getBoardListSearch(search_word);
    };

    @Override
    public List<Board> getBoardInfo(Integer board_no) {
        return board.getBoardInfo(board_no);
    };

    @Override
    public void insertBoardInfo(String board_title, String board_user_id, String board_content) {
        board.insertBoardInfo(board_title, board_user_id, board_content);
    };

    @Override
    public void updateBoardInfo(Integer board_no, String board_title, String board_user_id, String board_content) {
        board.updateBoardInfo(board_no, board_title, board_user_id, board_content);
    };

    @Override
    public void deleteBoardInfo(Integer board_no, String board_user_id) {
        board.deleteBoardInfo(board_no, board_user_id);
    };
}
