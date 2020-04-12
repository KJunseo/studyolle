package com.studyolle.account;

import lombok.Data;

/* 회원 가입  받아 올 데이터  */

@Data
public class SignUpForm {

    private String nickname;

    private String email;

    private String password;
}
