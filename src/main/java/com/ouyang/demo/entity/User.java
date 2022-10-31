package com.ouyang.demo.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class User implements Serializable {
    private static final long serialVersionUID = -5838756847926488707L;

    private Long id;

    private String userName;

    private String password;


    public static  User defaultUser(){
        return User.builder().id(1L).userName("ouyang").password("123456").build();
    }




}
