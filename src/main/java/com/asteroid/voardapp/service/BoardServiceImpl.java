package com.asteroid.voardapp.service;

import com.asteroid.voardapp.mapper.BoardMapper;
import com.asteroid.voardapp.model.Board;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class BoardServiceImpl implements BoardService{
    @Autowired
    BoardMapper board;

    @Override
    public List<Board> selectList() {
        return board.selectList();
    }
}
