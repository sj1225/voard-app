package com.asteroid.voardapp.service;

import com.asteroid.voardapp.model.Board;

import java.util.List;

public interface BoardService {
    public List<Board> getBoardList(String search_word);
    public List<Board> getBoardDetail(Integer board_no);
    public void insertBoardDetail(String board_title, String board_user_id, String board_content);
    public void updateBoardDetail(Integer board_no, String board_title, String board_user_id, String board_content);
    public void deleteBoardDetail(Integer board_no);
}
