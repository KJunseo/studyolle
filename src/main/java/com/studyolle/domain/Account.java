package com.studyolle.domain;

import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

@Entity
@Getter @Setter @EqualsAndHashCode(of = "id")
@Builder @AllArgsConstructor @NoArgsConstructor
public class Account {

    @Id @GeneratedValue
    private Long id;

    @Column(unique = true)
    private String email; // 이메일로 로그인 가능하게 하기 위함

    @Column(unique = true)
    private String nickname; // 닉네임으로도 로그인 할 수 있게 하기 위함

    private String password;

    private boolean emailVerified; // 이메일 인증이 된 계정인지 확인

    private String emailCheckToken; // 이메일 검증 시 사용할 토큰 값

    private LocalDateTime emailCheckTokenGeneratedAt;

    private LocalDateTime joinedAt; // 인증 완료 시, 가입 날짜

    /* 프로필과 관련된 정보들 */
    private String bio; // 간단한 자기소개

    private String url; // 관련 웹사이트

    private String occupation; // 직업

    private String location; // 살고있는 지역

    @Lob @Basic(fetch = FetchType.EAGER)
    private String profileImage; // 프로필 이미지 (Lob: TEXT 타입) (EAGER: 해당 엔티티 조회 시 연관 엔티티 즉시 로딩)

    /* 알림에 관한 정보들 */
    private boolean studyCreatedByEmail; // 스터디가 만들어졌다는 것을 이메일로 받을 것 인가

    private boolean studyCreatedByWeb = true; // 스터디가 만들어졌다는 것을 웹으로 받을 것 인가

    private boolean studyEnrollmentResultByEmail; // 스터디 가입 신청 결과를 이메일로 받을 것 인가

    private boolean studyEnrollmentResultByWeb = true; // 스터디 가입 신청 결과를 웹으로 받을 것 인가

    private boolean studyUpdatedByEmail; // 스터디에 대한 바뀐 정보들을 이메일로 받을 것 인가

    private boolean studyUpdatedByWeb = true; // 스터디에 대한 바뀐 정보들을 웹으로 받을 것 인가

    @ManyToMany
    private Set<Tag> tags = new HashSet<>(); // Account에서 Tag를 참조(단반향)

    @ManyToMany
    private Set<Zone> zones = new HashSet<>();

    public void generateEmailCheckToken() {
        this.emailCheckToken= UUID.randomUUID().toString();
        this.emailCheckTokenGeneratedAt = LocalDateTime.now();
    }

    public void completeSignUp() {
        this.emailVerified = true;
        this.joinedAt = LocalDateTime.now();
    }

    public boolean isValidToken(String token) {
        return this.emailCheckToken.equals(token);
    }

    // 현재 시간에서 한 시간 뺀 것 보다 더 이전에 만들었다면, 즉 token을 만든 시간이 한 시간이 지나야만 보낼 수 있음
    public boolean canSendConfirmEmail() {
        return this.emailCheckTokenGeneratedAt.isBefore(LocalDateTime.now().minusHours(1));
    }
}
