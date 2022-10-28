package com.springboot.hello2.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class User {
    private String id;
    private String name;
    private String password;

}
