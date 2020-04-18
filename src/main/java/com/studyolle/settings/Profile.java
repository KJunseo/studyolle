package com.studyolle.settings;

import com.studyolle.domain.Account;
import lombok.Data;

// form을 채울 객체
@Data
public class Profile {

    private String bio; // 간단한 자기소개

    private String url; // 관련 웹사이트

    private String occupation; // 직업

    private String location; // 살고있는 지역

    // account의 정보를 조회해서 채워줘야하기 때문에
    public Profile(Account account) {
        this.bio = account.getBio();
        this.url = account.getUrl();
        this.occupation = account.getOccupation();
        this.location = account.getLocation();
    }
}
