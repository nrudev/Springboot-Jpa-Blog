package com.cos.blog.controller.api;

import com.cos.blog.dto.ResponseDto;
import com.cos.blog.model.RoleType;
import com.cos.blog.model.User;
import com.cos.blog.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;

@RestController
public class UserApiController {

    @Autowired
    private UserService userService;

    @Autowired
    private HttpSession session;

    @PostMapping("/api/user")
    public ResponseDto<Integer> save(@RequestBody User user) {
        System.out.println("UserApiController: save 호출됨");
        // 실제로 DB에 insert를 하고 아래에서 return이 되면 돼요.
        user.setRole(RoleType.USER);
        int result = userService.회원가입(user);
        return new ResponseDto<>(HttpStatus.OK, result);
    }

    @PostMapping("/api/user/login")
    public ResponseDto<Integer> login(@RequestBody User user) {
        System.out.println("UserApiController: login 호출됨");
        User principal = userService.로그인(user); // principal = 접근 주체

        if (principal != null) {
            session.setAttribute("principal", principal);
        }
        return new ResponseDto<>(HttpStatus.OK, 1);
    }
}
