package com.studyolle.config;

import com.studyolle.account.AccountService;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.autoconfigure.security.servlet.PathRequest;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.authentication.rememberme.JdbcTokenRepositoryImpl;
import org.springframework.security.web.authentication.rememberme.PersistentTokenRepository;

import javax.sql.DataSource;

@Configuration
@EnableWebSecurity // spring security 설정을 개발자가 하겠다는 뜻
@RequiredArgsConstructor
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    private final AccountService accountService; // UserDetailsService 구현체
    private final DataSource dataSource;

    /* 원하는 http 요청들을 시큐리티 인증 체크를 하지 않도록 하기 위한 것 */
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                // 해당 요청들은 GET이나 POST 상관 없이 모두 허용
                .mvcMatchers("/", "/login", "/sign-up", "/check-email-token",
                        "/email-login", "/check-email-login", "/login-link").permitAll()
                // 프로필 관련 요청은 GET 요청만 허용
                .mvcMatchers(HttpMethod.GET, "/profile/*").permitAll()
                // 나머지 요청들은 인증을 거쳐야함
                .anyRequest().authenticated();

        // 커스텀 로그인 폼 사용
        http.formLogin()
                .loginPage("/login").permitAll();

        http.logout()
                .logoutSuccessUrl("/");

        http.rememberMe()
                .userDetailsService(accountService)
                .tokenRepository(tokenRepository()); // username, 토큰, 시리즈를 조합한 토큰 정보를 DB에 저장(rememberMe 쿠키랑 일치하는 지 확인하기 위함)
    }

    // tokenRepository의 구현체
    @Bean
    public PersistentTokenRepository tokenRepository() {
        JdbcTokenRepositoryImpl jdbcTokenRepository = new JdbcTokenRepositoryImpl();
        jdbcTokenRepository.setDataSource(dataSource);
        return jdbcTokenRepository;
    }

    /* static한 리소스들(로고 같은 것)에는 시큐리티 필터를 적용하지 않게 하는 것 */
    @Override
    public void configure(WebSecurity web) throws Exception {
        web.ignoring()
                .mvcMatchers("/node_modules/**")
                .requestMatchers(PathRequest.toStaticResources().atCommonLocations());
    }
}
