package com.gardener.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class FavoriteDTO {

  private Long id;
  private Long postnum;
  private String loginid;

  public FavoriteDTO(Long postnum, String loginid) {
    this.postnum = postnum;
    this.loginid = loginid;
  }
}
