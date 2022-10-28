package com.springboot.hello2.controller;

import com.springboot.hello2.dao.UserDao;
import com.springboot.hello2.domain.User;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/v1/user")
public class UserController {
    private final UserDao userDao;

    public UserController(UserDao userDao){
        this.userDao = userDao;
    }

    @GetMapping("/")
    public String hello(){
        return "Hello World";
    }

    @GetMapping("/user")
    public User addAndGet(){
        userDao.add(new User("1","kwon","1234"));
        return userDao.findById("1");
    }

    @DeleteMapping("/user")
    public ResponseEntity<Integer> deleteAll(){
        return ResponseEntity
                .ok()
                .body(userDao.deleteAll());
    }
}
