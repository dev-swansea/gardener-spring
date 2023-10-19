package com.gardener.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.gardener.domain.Search;
import com.gardener.service.SearchService;
import com.google.gson.Gson;

import lombok.extern.log4j.Log4j;

@Controller
@Log4j
public class SearchController {
	
	@Autowired
	private SearchService service;
	
	//Json으로 돌려줄려고 gson객체 생성
	Gson gson = new Gson();
	
	//검색한 결과를 받기위한 객체 생성
	List<Search> result = new ArrayList<Search>();
	
	//객체를 json으로 변환할 변수 생성
	String searchResult = null;
	
	@GetMapping(value="/search")
	public String search(@RequestParam String select, @RequestParam String text, 
			Model model, RedirectAttributes rttr) {
		
		
		
		//select option에 따라서 service메서드 호출
		if(select.equals("title")) {
			result = service.searchTitle(text);
			
		}else if(select.equals("name")){
			result = service.searchName(text);
			
		}else if(select.equals("content")){
			result = service.searchContent(text);
			
		}else if(select.equals("all")){
			result = service.searchAll(text);
		}
		
		//DB에 결과가 없을때 model에 저장 안하는 코드 삽입
		if(result.isEmpty()) {
			searchResult = text+"로 검색하는 결과가 없습니다"; 
			log.info("검색한 결과가 없을때 돌려주는 값:"+searchResult);
			rttr.addFlashAttribute("resultNone",searchResult);
			return "redirect:/";			
			
		}else {
			//searchResult = gson.toJson(result);
			log.info("model에 저장한 값:"+result);
			model.addAttribute("searchResult",result);
			log.info("gson으로 넘겨주는 값의 길이---"+result.size());
			model.addAttribute("resultTotal",result.size());
			model.addAttribute("text",text);
			model.addAttribute("select",select);
			return "search";	
		}
		

		
	
	}//search end
	
	@GetMapping(value="/magazine")
	public String searchAll(Model model) {
		
		result = service.magazine();
		model.addAttribute("magazine", result);
		model.addAttribute("resultTotal",result.size());
		
		return "magazine";
	}
	

	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}//search Controller end
