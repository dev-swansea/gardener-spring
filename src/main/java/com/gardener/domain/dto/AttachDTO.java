package com.gardener.domain.dto;

import lombok.Data;

@Data
public class AttachDTO {

  private String uuid;
  private String uploadPath;
  private String fileName;
  private boolean fileType;

  private Long postnum;

}
