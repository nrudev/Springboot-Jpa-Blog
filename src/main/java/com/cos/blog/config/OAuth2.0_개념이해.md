# OAuth2.0

## 1. OAuth란?

OAuth는 Open Authorization의 준말로, 내 웹서비스에서 **다른 서비스의 API에 접근하기 위해 사용자로부터 그 권한을 위임받는 방식**을 말한다.

스프링에서는 공식적인 OAuth 주체는 Facebook과 Google이며, OAuth Client 라이브러리를 통해 쉽게 연동이 가능하다.



## 2. OAuth 로그인 과정

![1](https://vividswan.github.io/assets/images/200904-1.png)



1. 사용자(User)가 API 서버로 직접 로그인 요청을 한다.
2. API 서버에서는 정상적인 접근 여부를 확인 후, 정상이면 클라이언트로 리다이렉트하면서 코드값을 함께 전달한다.(인증 처리 완료)
3. 클라이언트는 전달받은 코드값을 API 서버로 다시 보내 리소스 서버에 있는 사용자의 데이터에 접근할 권한을 요청한다.
4. API 서버는 코드값을 확인 후, 정상이면 클라이언트로 Access Token을 전달한다. Access Token은 리소스 서버의 데이터에 접근할 수 있는 권한을 부여받았다는 뜻이며, 클라이언트가 사용자로부터 자신의 정보에 접근할 수 있는 권한을 위임받았다는 뜻이다.



##### ✔︎ 참고

- [생활코딩 WEB2 - OAuth 2.0](https://opentutorials.org/course/3405)
- [OAuth와 춤을(OAuth의 탄생배경과 인증과정)](https://d2.naver.com/helloworld/24942)

- [OAuth 2.0 개요 및 보안 고려사항(pdf 파일)](https://www.fsec.or.kr/common/proc/fsec/bbs/42/fileDownLoad/265.do)

- [[Spring] OAuth 2.0 동작 과정](https://vividswan.github.io/2020/09/04/Spring-OAuth-2.0-%EB%8F%99%EC%9E%91-%EA%B3%BC%EC%A0%95.html)

