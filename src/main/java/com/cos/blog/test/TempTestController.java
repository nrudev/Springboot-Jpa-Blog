package com.cos.blog.test;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class TempTestController {

    @GetMapping("/temp/home")
    public String tempHome() {
        System.out.println("temp home");
        return "/home.html";
    }

    @GetMapping("/temp/jsp") // 찾지 못함. jsp는 정적인 파일이 아닌 동적인 자바 파일이라 컴파일이 필요하기 때문.
    public String tempJsp() {
        return "test";
    }
}
