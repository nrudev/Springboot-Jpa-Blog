# XSS 와 CSRF

**XSS (Cross-Site Scripting)** : 자바스크립트 공격

=> 네이버가 제공하는 오픈소스 [Lucy XSS Filter](https://naver.github.io/lucy-xss-filter/kr/) 사용하여 막을 수 있음.



**CSRF (Cross Site Request Forgery)** : 사이트간 요청 위조

사이트 관리자 권한을 갖고 있는 사람이 해커가 보낸 URL 을 열게 하여 보안을 취약하게 함.

**같은 도메인상에서 요청이 들어오지 않는다면 차단**하도록 하는 **Referrer 검증** 방법이나, 사용자의 **모든 요청에 CSRF 토큰을 사용하여 서버단에서 검증**하도록 하는 방법 등을 통해 막을 수 있다. 또한 form 태그 입력시 GET 방식이 아닌 **POST 방식을 쓰도록** 해야 한다.



##### ✔︎ 참고

- [[보안] CSRF(Cross Site Request Forgery)란 무엇인가?](https://sj602.github.io/2018/07/14/what-is-CSRF/)

