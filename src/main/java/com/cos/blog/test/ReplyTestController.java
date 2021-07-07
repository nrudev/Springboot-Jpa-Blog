package com.cos.blog.test;

import com.cos.blog.model.Board;
import com.cos.blog.model.Reply;
import com.cos.blog.repository.BoardRepository;
import com.cos.blog.repository.ReplyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class ReplyTestController {

    @Autowired
    private BoardRepository boardRepository;

    @Autowired
    private ReplyRepository replyRepository;

    @GetMapping("/dummy/board/{id}")
    public Board getBoard(@PathVariable int id) {
        return boardRepository.findById(id).get(); // jackson 라이브러리 작동
        // 무한참조 왜?
        /* Board 를 가져오기 위해서는 User와 Reply를 가져오게 되어 있음.(자동 JOIN)
        문제는 Reply. 여기서 return 할 때 jackson 라이브러리가 발동(오브젝트를 json으로 리턴) => 모델의 getter 호출
        Reply에서는 다시 getBoard 를 리턴하기 때문에 무한하게 반복됨.
         */
    }

    @GetMapping("/dummy/reply")
    public List<Reply> getReply() {
        return replyRepository.findAll();
    }
}
