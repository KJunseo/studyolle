package com.studyolle.settings;

import com.studyolle.domain.Account;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;

// form을 채울 객체
@Data
public class Profile {

    @Length(max = 35)
    private String bio; // 간단한 자기소개

    @Length(max = 50)
    private String url; // 관련 웹사이트

    @Length(max = 50)
    private String occupation; // 직업

    @Length(max = 50)
    private String location; // 살고있는 지역

    private String profileImage;
}
