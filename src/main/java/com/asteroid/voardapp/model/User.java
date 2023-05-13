package com.asteroid.voardapp.model;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class User {
    private String user_id;
    private String user_nm;
    private String password;
}

