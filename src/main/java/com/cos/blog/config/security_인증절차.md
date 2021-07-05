# Security 인증 절차

![img](https://blog.kakaocdn.net/dn/cs4tgw/btqEIKq0DF4/Fm93FYLNM8fZKmSwFrEchK/img.png)

세션에는 SecurityContext가 존재하고, 그 안에 Authentication이라는 객체가 들어간다. 이 객체가 들어가야 세션에 값이 저장이 된 상태가 되고, 필요한 곳에서 @AuthenticationPrincipal 을 사용하여 Authentication 객체를 가져올 수 있다.

<br/>

### Authentication 생성 과정

1. 사용자가 로그인 요청을 하면 AuthenticationFilter를 거침.(Body에 usernamer과 password를 담고 있음)
2. Body의 username과 password를 바탕으로 UsernamePasswordAuthenticationToken을 만듦. 이 토큰을 AuthenticationManager가 UserDetailsService 에게 전달.
3. (Authentication 생성 조건 1) UserDetailsService는 username을 통해 DB에 해당 사용자가 있는지 확인함. 존재하면 AuthenticationManager로 전달.
4. (Authentication 생성 조건 2) password는 AuthenticationManager가 관리. BCryptPasswordEncoder 로 인코딩하여 password 비교를 함.
5. username이 존재하고, 인코딩된 password가 일치하면 AuthenticationManager는 Authentication 객체를 생성하여 세션 내 SecurityContext에 저장.

<br/>

