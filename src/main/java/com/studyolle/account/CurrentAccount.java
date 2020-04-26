package com.studyolle.account;

import org.springframework.security.core.annotation.AuthenticationPrincipal;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME) // 런타임까지 유지
@Target(ElementType.PARAMETER) // 파라미터에만 붙일 수 있게
@AuthenticationPrincipal(expression = "#this == 'anonymousUser' ? null : account") // 현재 객체가 anonymousUser이면 null로 아니면 account로
public @interface CurrentAccount {
}
