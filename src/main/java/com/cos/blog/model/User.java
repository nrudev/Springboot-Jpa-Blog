package com.cos.blog.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.sql.Timestamp;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder // 빌더 패턴
@Entity // User 클래스가 MariaDB에 테이블이 생성된다.
//@DynamicInsert // insert시에 null인 필드를 제외시켜준다.
public class User {

    @Id // Primary Key
    @GeneratedValue(strategy = GenerationType.IDENTITY) // 프로젝트에서 연결된 DB의 넘버링 전략을 따라간다. => auto_increment
    private int id; // auto_increment(오라클의 sequence)

    @Column(nullable = false, length = 100, unique = true)
    private String username; // 아이디

    @Column(nullable = false, length = 100) // 왜 이렇게 길게? 123456 => 해쉬(비밀번호 암호화)
    private String password;

    @Column(nullable = false, length = 50)
    private String email;

//    @ColumnDefault("user")
    @Enumerated(EnumType.STRING) // DB는 RoleType이라는 게 없다.
    private RoleType role; // 타입은 Enum을 쓰는게 좋다. Enum을 쓰면 데이터의 도메인을 만들어줄 수 있다. => 오타 방지, 범위 설정

    private String oauth; // 로그인한 사용자가 카카오 로그인을 사용했는지 여부(kakao, google)

    @CreationTimestamp // 시간이 자동 입력
    private Timestamp createDate;
}
