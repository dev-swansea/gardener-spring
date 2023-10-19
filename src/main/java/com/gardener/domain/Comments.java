package com.gardener.domain;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@NoArgsConstructor
@Setter
@Getter
@ToString
@AllArgsConstructor
public class Comments {
	private int id; // pk
	private int postnum; // fk
	private int usernum; // fk
	private String content; // 600자까지
	private String createDate;
	private int report; // 좋아요 신고 수??

	private Member member; // 댓글을 쓴 사람

	public Comments(int id, int postnum, int usernum, String content, String createDate, int report) {
		this.id = id;
		this.postnum = postnum;
		this.usernum = usernum;
		this.content = content;
		this.createDate = createDate;
		this.report = report;
	}
}
