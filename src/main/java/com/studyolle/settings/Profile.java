package com.studyolle.settings;

import com.studyolle.domain.Account;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;

// form을 채울 객체
@Data
@NoArgsConstructor // 이게 없으면 update 시, 바인딩 받을 Profile을 생성할 때 Account정보가 없으므로 null point exception이 발생한다.
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

    // account의 정보를 조회해서 채워줘야하기 때문에
    public Profile(Account account) {
        this.bio = account.getBio();
        this.url = account.getUrl();
        this.occupation = account.getOccupation();
        this.location = account.getLocation();
        this.profileImage = account.getProfileImage();
    }
}
