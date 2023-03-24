package com.asteroid.voardapp.mapper;

import com.asteroid.voardapp.model.Board;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
@Mapper
public interface BoardMapper {
    public List<Board> getBoardList();
    public List<Board> getBoardInfo(Integer board_no);
}
