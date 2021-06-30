package com.cos.blog.test;

import com.cos.blog.model.RoleType;
import com.cos.blog.model.User;
import com.cos.blog.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.function.Supplier;

@RestController
public class DummyController {

    @Autowired // 의존성 주입(DI)
    private UserRepository userRepository;

    @DeleteMapping("/dummy/user/{id}")
    public String delete(@PathVariable int id) {
        try {
            userRepository.deleteById(id);
        } catch (EmptyResultDataAccessException e) {
            return "삭제에 실패하였습니다. 해당 아이디는 존재하지 않습니다.";
        }

        return "삭제되었습니다. id: " + id;
    }

    @Transactional
    @PutMapping("/dummy/user/{id}")
    public User updateUser(@PathVariable int id, @RequestBody User requestUser) { // json 데이터를 요청 -> java object(MessageConverter의 Jackson 라이브러리가)로 변환해서 받아줌
        System.out.println("id: " + id);
        System.out.println("password: " + requestUser.getPassword());
        System.out.println("email: " + requestUser.getEmail());

        User user = userRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("수정에 실패하였습니다."));
        user.setPassword(requestUser.getPassword());
        user.setEmail(requestUser.getEmail());

//        userRepository.save(user); // update 할 때는 그냥 바로 save()를 사용하지 않음. save()는 값을 준 것들만 저장하기 때문에 나머지는 null이 됨.
                                    // save() 메서드는 id를 전달하지 않으면 insert를 해주고,
                                    // id를 전달했을 때, 해당 id에 대한 데이터가 있으면 update를 해주고, 데이터가 없으면 insert를 해줌.
                                    // => update를 하기 위해 select가 선행된다.
                                    // => @Transactional 을 사용하면 save()를 하지 않아도 update가 된다 = 더티 체킹
        return user;
    }

    @GetMapping("/dummy/users")
    public List<User> list() {
        return userRepository.findAll();
    }

    // 한 페이지당 2건의 데이터를 리턴받자
    @GetMapping("/dummy/user")
    public List<User> pageList(@PageableDefault(size=2, sort="id", direction = Sort.Direction.DESC) Pageable pageable) {
        Page<User> pagingUser = userRepository.findAll(pageable);
        List<User> users = pagingUser.getContent();
        return users;
    }

    // {id} 주소로 파라미터를 전달받을 수 있음.
    @GetMapping("/dummy/user/{id}")
    public User detail(@PathVariable int id) {
        // findById의 타입은 Optional => 데이터베이스에서 찾지 못하면 user가 null이 됨 => 문제 발생
        // => Optional로 User객체를 감싸서 가져올테니 null인지 아닌지 판단해서 return 해라.
        User user = userRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("해당 사용자는 없습니다."));

        return user;
        // User 객체는 자바 오브젝트.
        // 요청은 웹 브라우저에서 했음.
        // RestController는 html 파일이 아니라 data를 리턴해줌.
        // 그런데 User를 리턴해? 웹 브라우저는 User 객체를 이해하지 못함.
        // => User 객체를 웹 브라우저가 이해할 수 있는 데이터로 변환해야 한다. => json
        // => 스프링부트는 MessageConverter가 응답시 자동 작동. Jackson 라이브러리 호출해서 User -> json 변환하여 브라우저에게 줌.
    }

    @PostMapping("/dummy/join")
    public String join(User user) { // key=value (약속된 규칙)
        System.out.println("id: " + user.getId());
        System.out.println("username: " + user.getUsername());
        System.out.println("password: " + user.getPassword());
        System.out.println("email: " + user.getEmail());
        System.out.println("role: " + user.getRole());
        System.out.println("createDate: " + user.getCreateDate());

        user.setRole(RoleType.USER);
        userRepository.save(user);
        return "회원가입이 완료되었습니다.";
    }
}
