package com.asteroid.voardapp.mapper;

import com.asteroid.voardapp.model.Board;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
@Mapper
public interface BoardMapper {
    public List<Board> getBoardList();
    public List<Board> getBoardInfo(Integer board_no);
    public void insertBoardInfo(String board_title, String board_user_id, String board_content);
    public void updateBoardInfo(Integer board_no, String board_title, String board_user_id, String board_content);
    public void deleteBoardInfo(Integer board_no, String board_user_id);
}
