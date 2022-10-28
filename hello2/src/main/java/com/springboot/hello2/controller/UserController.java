package com.springboot.hello2.controller;

import com.springboot.hello2.dao.UserDao;
import com.springboot.hello2.domain.User;
import com.springboot.hello2.domain.dto.UserRequestDto;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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

    @PostMapping("/user")
    public ResponseEntity<Integer> add(@RequestBody UserRequestDto userRequestDto){
        User user = new User(userRequestDto.getId(), userRequestDto.getName(), userRequestDto.getPassword());
        return ResponseEntity
                .ok()
                .body(userDao.add(user));
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
