package com.gardener.domain.dto;

import lombok.Data;

@Data
public class ImgDTO {
  private String fileName;
  private String uploadPath;
  private String uuid;
  private boolean image;
}
