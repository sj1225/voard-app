package com.asteroid.voardapp.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
public class Board {
    private Integer board_no;
    private String board_title;
    private String board_writer;
    @JsonFormat(shape=JsonFormat.Shape.STRING, pattern="yyyyMMddHHmmss", timezone="Asia/Seoul")
    private Date board_date;
    @JsonFormat(shape=JsonFormat.Shape.STRING, pattern="yyyyMMddHHmmss", timezone="Asia/Seoul")
    private Date board_mdf_date;
    private String board_content;
}
