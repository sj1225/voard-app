package com.asteroid.voardapp.service;

import com.asteroid.voardapp.model.Board;

import java.util.List;

public interface BoardService {
    public List<Board> getBoardList();
    public List<Board> getBoardInfo(Integer board_no);
}
