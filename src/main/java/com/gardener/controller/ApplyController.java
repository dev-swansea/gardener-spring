package com.gardener.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.gardener.aop.exception.FindException;
import com.gardener.domain.Member;
import com.gardener.domain.Writer;
import com.gardener.service.ApplyService;

import lombok.extern.log4j.Log4j;

@Controller
@Log4j
@RequestMapping("/apply")
public class ApplyController {

	@Autowired
	private ApplyService service;

//	@Autowired
//	private MypageService s;

	/**
	 * 작가신청
	 */
	@GetMapping
	public void apply(Model model, HttpSession session) {
		Member member = (Member) session.getAttribute("member");
		Writer writer = member.getWriter();
		log.info("loginid => " + member.getLoginid());

		model.addAttribute("writer", writer);
	}

	@PostMapping
	public ResponseEntity<String> insertWriter(@RequestParam("loginid") String loginid, HttpSession session) {
		log.warn("loginid  " + loginid);
		if (loginid == null) {
			return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("false");
		}
		// Member m = (Member) session.getAttribute("Member");꺼내오는코드
		boolean result = service.insertWriter(loginid);
		Writer w = null;
		try {
			w = service.selectByLoginid(loginid);
		} catch (FindException e) {
			e.printStackTrace();
		}
		session.setAttribute("writer", w.isType());
		log.warn("result:" + result);
		return ResponseEntity.ok(String.valueOf(result));
	}

}
