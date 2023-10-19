package com.gardener.domain;

import com.gardener.domain.dto.AttachDTO;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Post {
  private Long postnum; // 회원.아이디(번호)시퀀스
  private String loginid; // 회원.아이디(번호)시퀀스
  private String mainTitle; // 제목
  private String subTitle; // 소제목
  private String content; // 내용
  private String mainTitleImg; // 제목이미지
  private String category; // 카테고리
  private boolean publicYn; // 비공개,공개(0,1)
  private int favorite; // 좋아요
  private int totalCnt; // 댓글 수
  private String createDate; // 글 생성일자

  private Member member; // member DTO
  private List<AttachDTO> attachDTOList;

}
