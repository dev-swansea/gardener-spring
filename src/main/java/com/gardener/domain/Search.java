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
public class Search {
	private int postnum;			// 글 번호 (시퀀스)   	post
	private String maintitle; 		// 메인 제목			post
	private String maintitleimg; 	// 메인 이미지			post
	private String subtitle; 		// 서브 제목			post
	private String content; 		// 내용				post
	private String category;		// 카테고리 			post
	private int totalcnt;			// 댓글 총 수 			post
	private int favorite;			// 좋아요 수			post
	private String createdate; 		// 글 생성일자			post
	private String nickname; 		// 필명 필드name		member
	private String intro;			// 자기소개 			member
}
