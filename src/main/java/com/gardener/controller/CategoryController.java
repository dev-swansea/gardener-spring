package com.gardener.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.gardener.domain.Search;
import com.gardener.service.CategoryService;
import com.gardener.service.SearchService;
import com.google.gson.Gson;

import lombok.extern.log4j.Log4j;

@Controller
@Log4j
public class CategoryController {
	
	@Autowired
	private CategoryService service;
	
	@GetMapping("/category")
	public String category(@RequestParam String category, Model model, RedirectAttributes rttr) {
		
		//Json으로 돌려줄려고 gson객체 생성
		Gson gson = new Gson();
				
		//검색한 결과를 받기위한 객체 생성
		List<Search> result = new ArrayList<Search>();
				
		//객체를 json으로 변환할 변수 생성
		String searchResult = null;
		
		//넘어온 카테고리 값으로 검색
		result = service.searchCategory(category);
		
		if(result.isEmpty()) {
			searchResult = category+"의 카테고리에 글이 없습니다"; 
			log.info("검색한 결과가 없을때 돌려주는 값:"+searchResult);
			rttr.addFlashAttribute("resultNone",searchResult);
			return "redirect:/";			
			
		}else {
			//searchResult = gson.toJson(result);
			log.info("model에 저장한 값:"+result);
			model.addAttribute("searchResult",result);
			log.info("gson으로 넘겨주는 값의 길이---"+result.size());
			model.addAttribute("resultTotal",result.size());
			model.addAttribute("category",category);
			return "category";	
		}
	}
}
