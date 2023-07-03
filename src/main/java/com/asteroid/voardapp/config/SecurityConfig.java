package com.asteroid.voardapp.config;

import com.asteroid.voardapp.security.JwtAccessDeniedHandler;
import com.asteroid.voardapp.security.JwtAuthenticationEntryPoint;
import com.asteroid.voardapp.security.JwtAuthenticationFilter;
import com.asteroid.voardapp.security.JwtTokenProvider;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@Slf4j
@Configuration
@AllArgsConstructor
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {
    @Autowired
    private final JwtTokenProvider jwtTokenProvider;                            // JWT Token Provider

    @Override
    public void configure(WebSecurity web) throws Exception {
        web.ignoring().antMatchers("/js/**", "/css/**", "*.ico");   // 정적 컨텐츠 인가 & 인증 무시
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.httpBasic().disable()  // 1.http basic auth 기반 로그인 인증창 출력 여부. 사용하지 않으므로 disable 처리함.
            .csrf().disable()       // 2.csrf 보안 사용 여부. REST API 이므로 필요없어서 disable 처리함.

            .exceptionHandling()    // 3.인증, 인가에서 Error 발생 시 예외처리 -> 메인화면에서는 인증이 필요없는데 계속 예외처리됨!!! 좀따 확인해봐야지
            //.authenticationEntryPoint(new JwtAuthenticationEntryPoint())    // 비인증 접근 예외 처리
            //.accessDeniedHandler(new JwtAccessDeniedHandler())              // 비인가 접근 예외 처리

            .and()
            // 4.세션 사용여부? JWT 토큰을 이용해서 사용하지 않음. stateless 처리. (인증정보를 서버에 담지 않음)
            .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)

            .and()
            // 5.요청에 대한 사용권한 체크
            .authorizeRequests()

            /* .anyRequest(): 모든 요청
             * .antMatchers("/admin/**"): 해당 경로 요청
             * .authenticated(): 인증 필요
             * .hasRole("MEMBER"): 해당 Role 만 허용
             * .permitAll(): 모두 허용
             * */

            // 5-1.페이지 접속 허용
            .antMatchers("/").permitAll()                   // 루트 경로 (메인화면)
            .antMatchers("/home").permitAll()               // 메인화면
            .antMatchers("/detail/*").permitAll()           // 게시글 세부조회 화면
            .antMatchers("/logon", "/login").permitAll()    // 회원가입, 로그인 화면
            .antMatchers("/notFound").permitAll()           // 404 Not Found 화면

            // 5-2.API 접속 허용
            // 우선 permitAll()으로 함. 추후 동일 IP로 접속해야만 접속 가능하도록 수정할 것
            .antMatchers("/api/**").permitAll()

            // 5-3.그 외의 접속 인증 요청
            .anyRequest().authenticated()

            .and()
            // 6.cors 처리
            .cors()

            .and()
            // 7.로그인 관련 처리
            .formLogin()
            .loginPage("/login")                                    // 로그인 페이지 설정
            .defaultSuccessUrl("/")                                 // 로그인 성공 시 이동할 페이지
            .failureUrl("/login?error=true")      // 로그인 실패 시 이동할 페이지
            .usernameParameter("user_id")                           // 아이디 파라미터명
            .passwordParameter("password")                          // 비밀번호 파라미터명
            //.successHandler(loginSuccessHandler())		// 로그인 성공 후 핸들러
            //.failureHandler(loginFailureHandler())		// 로그인 실패 후 핸들러

            .and()
            // 8.로그아웃 관련 처리
            .logout()
            .logoutRequestMatcher(new AntPathRequestMatcher("/logout")) // 로그아웃 페이지 설정
            .logoutSuccessUrl(("/"))                                           // 로그아웃 성공 시 이동할 페이지
            .invalidateHttpSession(true)                                       // 로그아웃 시 생성된 세션 삭제 활성화 (나 세션 안쓰지않나)

            .and()
            // 9. 커스텀 필터 등록 (토큰 인증 수행)
            .addFilterBefore(new JwtAuthenticationFilter(jwtTokenProvider),
                                    UsernamePasswordAuthenticationFilter.class);
    }
}