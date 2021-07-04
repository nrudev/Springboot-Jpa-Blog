package com.cos.blog.controller.api;

import com.cos.blog.dto.ResponseDto;
import com.cos.blog.model.User;
import com.cos.blog.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class UserApiController {

    @Autowired
    private UserService userService;

    @PostMapping("/auth/joinProc")
    public ResponseDto<Integer> save(@RequestBody User user) { // username, password, email
        System.out.println("UserApiController: save 호출됨");
        userService.회원가입(user);
        return new ResponseDto<>(HttpStatus.OK, 1); // 자바 오브젝트를 JSON으로 변환해서 리턴(Jackson)
    }

    @PutMapping("/user")
    public ResponseDto<Integer> update(@RequestBody User user) { // @RequestBody가 없으면 JSON 데이터 못 받고, key=value 형태의 데이터만 받을 수 있음.(x-www-form-urlencoded)
        userService.회원수정(user);
        return new ResponseDto<>(HttpStatus.OK, 1);
    }

    /* 전통적인 방식의 로그인 */
//    @PostMapping("/api/user/login")
//    public ResponseDto<Integer> login(@RequestBody User user, HttpSession session) {
//        System.out.println("UserApiController: login 호출됨");
//        User principal = userService.로그인(user); // principal = 접근 주체
//
//        if (principal != null) {
//            session.setAttribute("principal", principal);
//        }
//        return new ResponseDto<>(HttpStatus.OK, 1);
//    }
}
