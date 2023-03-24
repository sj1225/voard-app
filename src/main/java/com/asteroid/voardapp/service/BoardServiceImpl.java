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
    }
    @Override
    public List<Board> getBoardInfo(Integer board_no) {
        return board.getBoardInfo(board_no);
    }
}
