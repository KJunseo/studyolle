package com.studyolle.settings;

import com.studyolle.domain.Account;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class Notifications {

    private boolean studyCreatedByEmail; // 스터디가 만들어졌다는 것을 이메일로 받을 것 인가

    private boolean studyCreatedByWeb; // 스터디가 만들어졌다는 것을 웹으로 받을 것 인가

    private boolean studyEnrollmentResultByEmail; // 스터디 가입 신청 결과를 이메일로 받을 것 인가

    private boolean studyEnrollmentResultByWeb; // 스터디 가입 신청 결과를 웹으로 받을 것 인가

    private boolean studyUpdatedByEmail; // 스터디에 대한 바뀐 정보들을 이메일로 받을 것 인가

    private boolean studyUpdatedByWeb; // 스터디에 대한 바뀐 정보들을 웹으로 받을 것 인가

    public Notifications(Account account) {
        this.studyCreatedByEmail = account.isStudyCreatedByEmail();
        this.studyCreatedByWeb = account.isStudyCreatedByWeb();
        this.studyEnrollmentResultByEmail = account.isStudyEnrollmentResultByEmail();
        this.studyEnrollmentResultByWeb = account.isStudyEnrollmentResultByWeb();
        this.studyUpdatedByEmail = account.isStudyUpdatedByEmail();
        this.studyUpdatedByWeb = account.isStudyUpdatedByWeb();
    }
}
