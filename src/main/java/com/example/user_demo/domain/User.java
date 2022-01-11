package com.example.user_demo.domain;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class User {
    private Integer id;

    private String name;
    private String joinDate;

    public User(String name, String joinDate) {
        this.name = name;
        this.joinDate = joinDate;
    }
}
