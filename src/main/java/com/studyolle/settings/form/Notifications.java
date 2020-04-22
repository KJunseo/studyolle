package com.studyolle.settings.form;

import lombok.Data;

@Data
public class Notifications {

    private boolean studyCreatedByEmail; // 스터디가 만들어졌다는 것을 이메일로 받을 것 인가

    private boolean studyCreatedByWeb; // 스터디가 만들어졌다는 것을 웹으로 받을 것 인가

    private boolean studyEnrollmentResultByEmail; // 스터디 가입 신청 결과를 이메일로 받을 것 인가

    private boolean studyEnrollmentResultByWeb; // 스터디 가입 신청 결과를 웹으로 받을 것 인가

    private boolean studyUpdatedByEmail; // 스터디에 대한 바뀐 정보들을 이메일로 받을 것 인가

    private boolean studyUpdatedByWeb; // 스터디에 대한 바뀐 정보들을 웹으로 받을 것 인가
}
