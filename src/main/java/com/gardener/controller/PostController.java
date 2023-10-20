package com.gardener.controller;

import com.gardener.domain.dto.ImgDTO;
import com.google.gson.Gson;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import net.coobird.thumbnailator.Thumbnailator;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;

/**
 * main image와 content image를 저장하고 클라이언트 쪽으로 데이터를 넘긴다.
 */

@Slf4j
@RestController
@RequiredArgsConstructor
public class PostController {

  private final String uploadDir = Paths.get("C:", "upload").toString();

  /**
   * 메인 이미지 저장
   *
   * @param image
   * @return MainImgDTO: 이미지 파일의 메타 데이터를 담고있다.
   */
  @PostMapping(value = "/image", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
  public ResponseEntity<String> uploadMainImage(MultipartFile image, String type) {
    ImgDTO dto = new ImgDTO();
    Gson gson = new Gson();

    if (image.isEmpty()) {
      throw new IllegalArgumentException();
    }

    String originalFileName = image.getOriginalFilename();
    String uuid = UUID.randomUUID().toString().substring(0, 8);
    String saveFileName = uuid + "_" + originalFileName;
    File uploadPath = null;
    if (type.equals("main")) {
      uploadPath = new File(uploadDir, getFolder() + "\\main");
    } else {
      uploadPath = new File(uploadDir, getFolder() + "\\content");
    }

    dto.setFileName(saveFileName);
    dto.setUuid(uuid);
    dto.setUploadPath(getFolder());

    if (!uploadPath.exists()) {
      uploadPath.mkdirs();
    }

    FileOutputStream fos = null;
    try {
      File saveFile = new File(uploadPath, saveFileName);
      image.transferTo(saveFile);
      dto.setImage(true); // 사실 필요 없음,

      fos = new FileOutputStream(new File(uploadPath, "s_" + saveFileName));
      Thumbnailator.createThumbnail(image.getInputStream(), fos, 250, 250);

      return ResponseEntity.ok().body(gson.toJson(dto));
    } catch (IOException e) {
      throw new RuntimeException();
    } finally {
      try {
        fos.close();
      } catch (IOException e) {
      }
    }
  }

  /**
   * 이미지 프린트
   *
   * @param filename - 저장되는 이름
   * @param sort     - main, content
   * @return byte[]
   */
  @GetMapping(value = "/image-print", produces = {MediaType.IMAGE_GIF_VALUE, MediaType.IMAGE_JPEG_VALUE, MediaType.IMAGE_PNG_VALUE})
  public ResponseEntity<byte[]> printEditorImage(@RequestParam String filename, String sort) {
    String uploadPath = "";

    if (sort.equals("main")) {
      uploadPath = Paths.get(uploadDir, filename).toString();
    } else {
      uploadPath = Paths.get(uploadDir, filename).toString();
    }

    File uploadFile = new File(uploadPath);

    if (!uploadFile.exists()) {
      uploadFile.mkdirs();
    }

    try {
      byte[] imgBytes = Files.readAllBytes(uploadFile.toPath());
      return ResponseEntity.ok().body(imgBytes);
    } catch (IOException e) {
      throw new RuntimeException();
    }
  }

  /**
   * 날짜별로 폴더 만들기
   *
   * @return 날짜별로 만들어진 파일 경로
   */
  private String getFolder() {
    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
    Date date = new Date();
    String str = sdf.format(date);
    return str.replace("-", File.separator);
  }
}