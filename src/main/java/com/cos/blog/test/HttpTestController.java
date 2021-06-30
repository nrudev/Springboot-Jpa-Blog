package com.cos.blog.test;

import org.springframework.web.bind.annotation.*;

@RestController // 사용자의 요청에 대해 Data를 응답하는 컨트롤러
public class HttpTestController {

    private static final String TAG = "HttpTestController: ";

    @GetMapping("/http/lombok")
    public String lombokTest() {
        Member m = Member.builder().username("ssar").password("1234").email("ssar@nate.com").build();
        System.out.println(TAG + "getter : " + m.getUsername());
        m.setUsername("cos");
        System.out.println(TAG + "setter : " + m.getUsername());

        return "lombok 테스트 완료";
    }

    @GetMapping("/http/get")
    public String getTest(Member m) { // ?id=1&username=ssar&password=1234&email=ssar@nate.com
        return "get 요청: " + m.getId() + ", " + m.getUsername() + ", " + m.getPassword() + ", " + m.getEmail();
    }

    @PostMapping("/http/post")
    public String postTest(@RequestBody Member m) {
        return "post 요청: " + m.getId() + ", " + m.getUsername() + ", " + m.getPassword() + ", " + m.getEmail();
    }

    @PutMapping("/http/put")
    public String putTest(@RequestBody Member m) {
        return "put 요청: " + m.getId() + ", " + m.getUsername() + ", " + m.getPassword() + ", " + m.getEmail();
    }

    @DeleteMapping("/http/delete")
    public String deleteTest() {
        return "delete 요청";
    }

}
