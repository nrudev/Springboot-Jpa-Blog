package com.cos.blog.service;

import com.cos.blog.dto.ReplySaveRequestDto;
import com.cos.blog.model.Board;
import com.cos.blog.model.Reply;
import com.cos.blog.model.User;
import com.cos.blog.repository.BoardRepository;
import com.cos.blog.repository.ReplyRepository;
import com.cos.blog.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
public class BoardService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private BoardRepository boardRepository;

    @Autowired
    private ReplyRepository replyRepository;

    @Transactional
    public void 글쓰기(Board board, User user) { // title, content
        board.setCount(0); // 조회수 기본값 0
        board.setUser(user);
        boardRepository.save(board);
    }

    @Transactional(readOnly = true)
    public Page<Board> 글목록(Pageable pageable) {
        return boardRepository.findAll(pageable); // JpaRepository의 메서드. SELECT * FROM board;
    }

    @Transactional(readOnly = true)
    public Board 글상세보기(int id) {
        return boardRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("글 상세보기 실패 : 아이디를 찾을 수 없습니다."));
    }

    @Transactional
    public void 글삭제하기(int id) {
        boardRepository.deleteById(id);
    }

    @Transactional
    public void 글수정하기(int id, Board requestBoard) {
        Board board = boardRepository.findById(id) // 영속화
                .orElseThrow(() -> new IllegalArgumentException("글을 수정할 수 없습니다."));

        board.setTitle(requestBoard.getTitle());
        board.setContent(requestBoard.getContent());
        // 해당 함수로 종료시(service 종료시) 트랜잭션 종료. 이때 더티체킹 -> 자동 업데이트 됨. DB flush
    }

    @Transactional
    public void 댓글쓰기(ReplySaveRequestDto replySaveRequestDto) {

//        User user = userRepository.findById(replySaveRequestDto.getUserId())
//                .orElseThrow(() -> new IllegalArgumentException("댓글 쓰기 실패 : 유저 id를 찾을 수 없습니다.")); // 영속화
//
//        Board board = boardRepository.findById(replySaveRequestDto.getBoardId())
//                .orElseThrow(() -> new IllegalArgumentException("댓글 쓰기 실패 : 게시글 id를 찾을 수 없습니다.")); // 영속화

//        Reply reply = Reply.builder()
//                .user(user)
//                .board(board)
//                .content(replySaveRequestDto.getContent())
//                .build();

//        Reply reply = new Reply();
//        reply.update(user, board, replySaveRequestDto.getContent());

        replyRepository.mySave(replySaveRequestDto.getUserId(),
                replySaveRequestDto.getBoardId(),
                replySaveRequestDto.getContent());
    }

    @Transactional
    public void 댓글삭제(int replyId) {
        replyRepository.deleteById(replyId);
    }
}
