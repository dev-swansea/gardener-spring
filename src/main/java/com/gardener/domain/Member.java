package com.gardener.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Member {

	private String loginid; // 아이디, PK
	private String pwd; // 패스워드
	private String email; // 이메일
	private String nickname; // 필명
	private String intro; // 자기소개
	private String createDate; // 가입날짜
	private String profile; // 프로필사진
	private Writer writer;

	public Member(String loginid, String pwd, String email, String nickname, String intro) {
		this.loginid = loginid;
		this.pwd = pwd;
		this.email = email;
		this.nickname = nickname;
		this.intro = intro;
	}

}