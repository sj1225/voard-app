package com.asteroid.voardapp.service;

import com.asteroid.voardapp.model.Board;

import java.util.List;

public interface BoardService {
    public List<Board> getBoardList();
    public List<Board> getBoardInfo(Integer board_no);
    public void insertBoardInfo(String board_title, String board_user_id, String board_content);
    public void updateBoardInfo(Integer board_no, String board_title, String board_user_id, String board_content);
}
