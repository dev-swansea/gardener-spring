package com.gardener.controller;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.multipart.MultipartFile;

import com.gardener.aop.exception.AddException;
import com.gardener.aop.exception.FindException;
import com.gardener.domain.Member;
import com.gardener.service.MemberService;

import lombok.extern.log4j.Log4j;

@Controller
@Log4j
public class SignupController {

  @Autowired
  MemberService service;

  @GetMapping("/signup")
  public void signup() {
  }

  @PostMapping("/signup")
  public ResponseEntity<String> signup(Member m) {
    log.warn("회원가입 컨트롤러 => " + m);
    try {
      service.signup(m);
      return new ResponseEntity(HttpStatus.OK);
    } catch (AddException e) {
      e.printStackTrace();
      return new ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR);
    }
  }

  // 아이디 중복확인
  @GetMapping("/id_chk")
  public ResponseEntity<String> idDupChk(String loginid) {
    try {
      log.info("loginid:" + loginid);
      String result = service.idDupChk(loginid);
      log.info(result);
      return new ResponseEntity(result, HttpStatus.OK);
    } catch (FindException e) {
      e.printStackTrace();
      return new ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR);
    }
  }

  ;

  // 필명 중복확인
  @GetMapping("/name_chk")
  public ResponseEntity<String> NicknameDupChk(String nickname) {
    try {
      log.info(nickname);
      String result = service.NicknameDupChk(nickname);
      log.info(result);
      return new ResponseEntity(result, HttpStatus.OK);
    } catch (FindException e) {
      e.printStackTrace();
      return new ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR);
    }
  }

  ;

  @PostMapping("/profile")
  public ResponseEntity<String> uploadFormPost(MultipartFile[] uploadFile) {
    String uploadFolder = "C:\\upload";
    for (MultipartFile multipartFile : uploadFile) {
      log.info("--------------------------");
      log.info("upload File Name:" + multipartFile.getOriginalFilename());
      log.info("upload File Size:" + multipartFile.getSize());

      String uploadFileName = multipartFile.getOriginalFilename();

      File saveFile = new File(uploadFolder, multipartFile.getOriginalFilename());
      log.warn(saveFile);

      try {
        multipartFile.transferTo(saveFile);
      } catch (Exception e) {
        log.error(e.getMessage());
      }
    }
    return null;
  }

  private String getFolder() {
    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
    Date date = new Date();
    String str = sdf.format(date);
    return str.replace("-", File.separator);
  }

}
