package com.cos.blog.config;

import com.cos.blog.config.auth.PrincipalDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

// 아래 3개 어노테이션은 세트라고 생각하면 된다.
@Configuration // 빈 등록 : 스프링 컨테이너에서 객체를 관리할 수 있게 하는 것(IoC 관리)
@EnableWebSecurity // 시큐리티 필터가 등록이 된다 = 스프링 시큐리티가 활성화가 되어 있는데 어떤 설정을 해당 파일에서 하겠다.
@EnableGlobalMethodSecurity(prePostEnabled = true) // 특정 주소로 접근을 하면 권한 및 인증을 미리 체크하겠다는 뜻.
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private PrincipalDetailService principalDetailService;

    @Bean
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }

    @Bean // return한 객체를 스프링이 관리!(IoC)
    public BCryptPasswordEncoder encodePWD() {
        return new BCryptPasswordEncoder(); // 비밀번호를 해쉬화하여 고정길이의 문자열로 바꿔줌.
    }

    /* 시큐리티가 대신 로그인해줄 때, password를 가로채기 하는데
    해당 password가 뭘로 해쉬가 되어 회원가입이 되었는지 알아야
    같은 해쉬로 암호화해서 DB에 있는 해쉬랑 비교할 수 있음. */
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(principalDetailService).passwordEncoder(encodePWD());
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
            .csrf().disable() // csrf 토큰 비활성화 (테스트시 걸어두는 게 좋음)
            .authorizeRequests()
                .antMatchers("/", "/auth/**", "/js/**", "/css/**", "/image/**", "/dummy/**") // 여기에 적힌 주소로는 누구나 접근 가능!
                .permitAll()
                .anyRequest() // "/auth/**" 이외의 주소들은 모두
                .authenticated() // 로그인을 해야 접근 가능!
            .and()
                .formLogin()
                .loginPage("/auth/loginForm")
                .loginProcessingUrl("/auth/loginProc") // 스프링 시큐리티가 해당 주소로 요청오는 로그인을 가로채서 대신 로그인 해준다.
                .defaultSuccessUrl("/");

    }
}