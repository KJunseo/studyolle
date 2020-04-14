package com.studyolle.config;

import org.springframework.boot.autoconfigure.security.servlet.PathRequest;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration
@EnableWebSecurity // spring security 설정을 개발자가 하겠다는 뜻
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    /* 원하는 http 요청들을 시큐리티 인증 체크를 하지 않도록 하기 위한 것 */
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                // 해당 요청들은 GET이나 POST 상관 없이 모두 허용
                .mvcMatchers("/", "/login", "/sign-up", "/check-email", "/check-email-token",
                        "/email-login", "/check-email-login", "/login-link").permitAll()
                // 프로필 관련 요청은 GET 요청만 허용
                .mvcMatchers(HttpMethod.GET, "/profile/*").permitAll()
                // 나머지 요청들은 인증을 거쳐야함
                .anyRequest().authenticated();
    }

    /* static한 리소스들(로고 같은 것)에는 시큐리티 필터를 적용하지 않게 하는 것 */
    @Override
    public void configure(WebSecurity web) throws Exception {
        web.ignoring()
                .mvcMatchers("/node_modules/**")
                .requestMatchers(PathRequest.toStaticResources().atCommonLocations());
    }
}
