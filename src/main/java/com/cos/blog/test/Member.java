package com.cos.blog.test;

import lombok.*;

@Data
@NoArgsConstructor
//@RequiredArgsConstructor // final 붙은 필드로 생성자 생성
public class Member {

    private int id;
    private String username;
    private String password;
    private String email;

    @Builder
    public Member(int id, String username, String password, String email) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.email = email;
    }

    /* private 으로 만드는 이유 :
         변수에 직접 접근해서 값을 수정해서는 안됨. 객체지향과 맞지 않음.
         메서드에 의한 변경은 가능.
         따라서 변수에 대한 직접적 접근은 막고, 메서드를 통한 수정이 가능하도록 변수는 private 으로, 메서드는 public 으로 설정.
     */

    /* final 붙이는 이유 :
            불변성 유지를 위해
     */
}
