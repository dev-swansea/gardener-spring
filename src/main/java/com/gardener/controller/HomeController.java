package com.gardener.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.gardener.domain.Search;
import com.gardener.service.HomeService;
import com.gardener.service.SearchService;

@Controller
public class HomeController {
	
	@Autowired
	private HomeService service;
	
	//좋아요가 가장 많은거 4개 받는 객체
	List<Search> faveriteTop = new ArrayList<Search>();
	
	//좋아요가 가장많은 글의 작가의 글을 모두 가져오는 객체
	List<Search> TopCollection = new ArrayList<Search>();
	
  @GetMapping("/")
  public String home(Model model) {
	  
	  faveriteTop = service.FavoriteTop();
	  
	  TopCollection = service.TopCollection();
	  
	  model.addAttribute("faveriteTop",faveriteTop);
	  model.addAttribute("faveriteTopSize",faveriteTop.size());
	  
	  model.addAttribute("TopCollection",TopCollection);
	  model.addAttribute("TopCollectionSize",TopCollection.size());
	  
	  
    return "index";
  }

}
