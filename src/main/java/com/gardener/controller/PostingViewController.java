package com.gardener.controller;


import com.gardener.domain.Member;
import com.gardener.domain.Post;
import com.gardener.service.PostService;
import com.google.gson.Gson;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpSession;

@Controller
@RequiredArgsConstructor
public class PostingViewController {

  private final PostService postService;

  @GetMapping("/posting")
  public String getPostingView(@RequestParam(required = false) Long postnum, Model model, HttpSession session) {
    if (postnum != null) {
      Member member = (Member) session.getAttribute("member");
      Post postByPostnum = postService.findPostByPostnum(postnum);

      model.addAttribute("post", new Gson().toJson(postByPostnum));
      model.addAttribute("writer", new Gson().toJson(member.getWriter().isType()));
    }
    return "/posting";
  }
}
