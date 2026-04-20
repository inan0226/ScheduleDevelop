package com.example.scheduledevelop.user.controller;

import com.example.scheduledevelop.user.dto.UserCreateRequest;
import com.example.scheduledevelop.user.dto.UserCreateResponse;
import com.example.scheduledevelop.user.dto.UserGetResponse;
import com.example.scheduledevelop.user.dto.UserPutRequest;
import com.example.scheduledevelop.user.entity.User;
import com.example.scheduledevelop.user.service.UserService;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;
    @PostMapping("/login")
    public ResponseEntity<LoginResponse> login(@Valid @RequestBody LoginRequest request, HttpSession session) {
        User user = userService.login(request);
        SessionUser sessionUser = new SessionUser(user);
        session.setAttribute("loginUser", sessionUser);

        LoginResponse response = new LoginResponse(user.getId(), user.getEmail());
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    // 유저 생성
    @PostMapping("/users")
    public ResponseEntity<UserCreateResponse> createUser(@RequestBody UserCreateRequest request){
        return ResponseEntity.status(HttpStatus.CREATED).body(userService.create(request));
    }
    // 전체 조회
    @GetMapping("/users")
    public ResponseEntity<List<UserGetResponse>> getUsers(){
        return ResponseEntity.status(HttpStatus.OK).body(userService.getAll());
    }
    // 단건 조회
    @GetMapping("/users/{id}")
    public ResponseEntity<UserGetResponse> getOneUser(@PathVariable Long id){
        return ResponseEntity.status(HttpStatus.OK).body(userService.getOneUser(id));
    }
    // 유저 수정
    @PutMapping("/users/{id}")
    public ResponseEntity<UserGetResponse> putOneUser(@PathVariable Long id, @RequestBody UserPutRequest request){
        return ResponseEntity.status(HttpStatus.OK).body(userService.putOneUser(id,request));
    }
    //유저 삭제
    @DeleteMapping("users/{id}")
    public ResponseEntity<Void> deleteOneUser(@PathVariable Long id){
        userService.deleteUser(id);
        return ResponseEntity.status(HttpStatus.OK).build();
    }
}
