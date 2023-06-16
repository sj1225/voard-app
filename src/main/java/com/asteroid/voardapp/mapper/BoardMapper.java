package com.asteroid.voardapp.mapper;

import com.asteroid.voardapp.model.Board;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
@Mapper
public interface BoardMapper {
    public List<Board> getBoardList(String search_word);
    public List<Board> getBoardDetail(Integer board_no);
    public void insertBoardDetail(String board_title, String board_user_id, String board_content);
    public void updateBoardDetail(Integer board_no, String board_title, String board_user_id, String board_content);
    public void deleteBoardDetail(Integer board_no);
}
