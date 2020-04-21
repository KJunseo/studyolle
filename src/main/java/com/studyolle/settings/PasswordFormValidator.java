package com.studyolle.settings;

import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

public class PasswordFormValidator implements Validator {

    // 어떤 타입의 폼 객체를 검증할 것인가
    @Override
    public boolean supports(Class<?> aClass) {
        return PasswordForm.class.isAssignableFrom(aClass);
    }

    @Override
    public void validate(Object target, Errors errors) {
        PasswordForm passwordForm = (PasswordForm)target;
        if(!passwordForm.getNewPassword().equals(passwordForm.getNewPasswordConfirm())) {
            errors.rejectValue("newPassword", "wrong.value", "입력한 새 패스워드가 일치하지 않습니다");
        }
    }
}
