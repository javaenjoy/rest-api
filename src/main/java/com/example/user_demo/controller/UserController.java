package com.example.user_demo.controller;

import com.example.user_demo.config.UserResourceAssembler;
import com.example.user_demo.domain.User;
import com.example.user_demo.exception.UserNotFoundException;
import com.example.user_demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;


@RestController
public class UserController {
    @Autowired
    private UserResourceAssembler userResourceAssembler;
    @Autowired
    private UserService userService;


    //전체 사용자 목록 조회
    @GetMapping("/api/users")
    public CollectionModel<EntityModel<User>> retriveAllUsers() {
        List<User> users =  userService.findAll();
        return userResourceAssembler.toCollectionModel(users);
    }


    //사용자 상세 조회
    @GetMapping("/api/users/{id}")
    public EntityModel<User> retriveUserById(@PathVariable Integer id) {
        User user = userService.findById(id);
        if (user == null) {
            throw new UserNotFoundException("존재하지 않는 사용자입니다.");
        }
        return userResourceAssembler.toModel(user);
    }


    //사용자 생성
    @PostMapping("/api/user")
    public ResponseEntity createUser(@RequestBody User user) {
        User savedUser = userService.save(user);

        URI location = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(savedUser.getId())
                .toUri();

        return ResponseEntity.created(location).build();
    }


    //사용자 삭제
    @DeleteMapping("/api/users/{id}")
    public ResponseEntity<String> removeUser(@PathVariable Integer id) {
        User user = this.userService.delete(id);

        if (user != null) {
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }

}







