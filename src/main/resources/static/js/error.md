# 에러 체크

Refused to execute script from 'http://localhost:8080/auth/loginForm' because its MIME type ('text/html') is not executable, and strict MIME type checking is enabled.

SecurityConfig에서 ```"/auth/**"``` 주소만 허용한 바람에 "/js/**"에 있는 자바스크립트 파일은 스프링 시큐리티에 의해 실행이 막혀버림!

```.antMatchers("/", "/auth/**", "/js/**", "/css/**", "/image/**")```

위와 같이 정적 파일 관련 폴더들도 로그인을 하지 않아도 사용 가능하도록 수정해주었더니 해결.