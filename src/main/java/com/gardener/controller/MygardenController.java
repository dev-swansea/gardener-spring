package com.gardener.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.gardener.domain.Member;
import com.gardener.domain.Search;
import com.gardener.service.MygardenService;

import lombok.extern.log4j.Log4j;

@Controller
@Log4j
public class MygardenController {
	
	@Autowired
	private MygardenService service;
	
	//내가 쓴 글 받는 객체
	List<Search> mygarden = new ArrayList<Search>();
	
  @GetMapping("/mygarden")
  public String mygarden(HttpSession session, Model model) {
	  
	  //loginid 가져온다.
	  Member member = (Member) session.getAttribute("member");
	  
	  //loginId만 가져온다.
	  String loginid = member.getLoginid();
	  
	  log.info(loginid);
	  
	  //loginid로 내가 쓴글 받는다.
	  mygarden = service.Mygarden(loginid);
	  
	  model.addAttribute("mygarden",mygarden);
	  model.addAttribute("mygardenSize",mygarden.size());

	  
	  
    return "mygarden";
  }

}
