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
    private String board_user_id;
    private String board_date;
    private String board_mdf_date;
    private String board_content;
}
