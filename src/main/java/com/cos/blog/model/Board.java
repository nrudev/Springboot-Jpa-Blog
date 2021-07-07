package com.cos.blog.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
public class Board {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // auto_increment
    private int id;

    @Column(nullable = false, length = 100)
    private String title;

    @Lob// 대용량 데이터
    private String content; // 섬머노트 라이브러리 <html> 태그가 섞여서 디자인됨. => 글자 용량 커짐

    private int count; // 조회수

    // ManyToOne의 기본 전략은 fetch = FetchType.EAGER => user 정보 바로 갖고 옴.
    @ManyToOne(fetch = FetchType.EAGER) // Many = Board, User = One 한 명의 유저는 여러 개의 게시글을 쓸 수 있다.
    @JoinColumn(name = "userId")
    private User user; // DB는 오브젝트를 저장할 수 없다. FK, 자바는 오브젝트를 저장할 수 있다.

    // OneToMany는 기본 전략이 EAGER이 아님.(LAZY) 필요하면 들고 오고 필요하지 않으면 들고 오지 않는다. 그러나 지금은 한 화면에 댓글을 바로 보여줄 것이기 때문에 EAGER 로 수정!
    @OneToMany(mappedBy = "board", fetch = FetchType.EAGER) // mappedBy 연관관계의 주인이 아니다(FK가 아니다) => DB에 칼럼을 만들지 마시오.
    @JsonIgnoreProperties({"board"}) // 무한 참조 방지
    @OrderBy("id desc") // id 내림차순으로 정렬
    private List<Reply> replies;

    @CreationTimestamp
    private Timestamp createDate;
}
