package com.asteroid.voardapp.service;

import com.asteroid.voardapp.mapper.BoardMapper;
import com.asteroid.voardapp.model.Board;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BoardServiceImpl implements BoardService{
    @Autowired
    BoardMapper board;

    @Override
    public List<Board> getBoardList(String search_word) {
        return board.getBoardList(search_word);
    };

    @Override
    public List<Board> getBoardDetail(Integer board_no) {
        return board.getBoardDetail(board_no);
    };

    @Override
    public void insertBoardDetail(String board_title, String board_user_id, String board_content) {
        board.insertBoardDetail(board_title, board_user_id, board_content);
    };

    @Override
    public void updateBoardDetail(Integer board_no, String board_title, String board_user_id, String board_content) {
        board.updateBoardDetail(board_no, board_title, board_user_id, board_content);
    };

    @Override
    public void deleteBoardDetail(Integer board_no) {
        board.deleteBoardDetail(board_no);
    };
}
