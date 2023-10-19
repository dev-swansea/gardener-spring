package com.gardener.controller;

import com.gardener.aop.exception.FindException;
import com.gardener.domain.Member;
import com.gardener.domain.Post;
import com.gardener.service.LibraryService;
import com.google.gson.Gson;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/library")
@RequiredArgsConstructor
@Slf4j
public class LibraryController {

  private final LibraryService libraryService;

  @GetMapping(produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
  public String getAllFavoritePost(HttpSession session, Model model) throws FindException {
    Member member = (Member) session.getAttribute("member");
    if (member == null) {
      log.info("member err=> {}", member);
      return "redirect:/";
    }
    String allFavoritePostWithPaging = getAllFavoritePostWithPaging(session, 1);
    model.addAttribute("post", allFavoritePostWithPaging);
    return "library";
  }

  @ExceptionHandler(FindException.class)
  @GetMapping(value = "/favorite/{num}", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
  public @ResponseBody String getAllFavoritePostWithPaging(HttpSession session, @PathVariable int num) throws FindException {
    Member member = (Member) session.getAttribute("member");
    Gson gson = new Gson();
    List<Post> allPost = libraryService.findAllFavoritePostWithPaging(member.getLoginid(), num);

    // 이 부분 수정이 필요함,
    /*if (allPost.isEmpty()) {
      //throw new FindException("가져올 글이 없습니다.");
    }*/

    allPost.forEach(post -> {
      String s = post.getContent().replaceAll("<[^>]*>", "");
      post.setContent(s);
    });

    return gson.toJson(allPost);
  }

  @GetMapping("/subscribe")
  public void getAllSubscribe(HttpSession session, Model model) throws FindException {
    String allSubscribeWithPaging = getAllSubscribeWithPaging(session, 1);
    model.addAttribute("member", allSubscribeWithPaging);
  }

  @GetMapping(value = "/subscribe/{num}", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
  public @ResponseBody String getAllSubscribeWithPaging(HttpSession session, @PathVariable int num) throws FindException {
    Member member = (Member) session.getAttribute("member");
    List<Member> allSubscribe = libraryService.findAllSubscribeWithPaging(member.getLoginid(), num);

    Gson gson = new Gson();

    if (allSubscribe.isEmpty()) {
      throw new FindException();
    }

    return gson.toJson(allSubscribe);
  }

  @PostMapping("/subscribe")
  public void insertSubscribe(HttpSession session, String writerId) throws FindException {
    Member member = (Member) session.getAttribute("member");
    libraryService.insertSubscribe(member.getLoginid(), writerId);
  }

  @GetMapping(value = "/writer/{writerId}")
  public String writerPage(@PathVariable String writerId, HttpSession session, Model model) throws FindException {
    writerPageWithPaging(writerId, 1, session, model);
    return "/library/writer";
  }

  @GetMapping(value = "/writer/{writerId}/{num}", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
  public @ResponseBody List<String> writerPageWithPaging(@PathVariable String writerId, @PathVariable int num, HttpSession session, Model model) throws FindException {
    Member member = (Member) session.getAttribute("member");
    List<String> list = new ArrayList<>();
    Gson gson = new Gson();

    List<Post> allSubscribedWriterPost = libraryService.findAllSubscribedWriterPost(writerId, num);

    allSubscribedWriterPost.forEach(post -> {
      String s = "";
      try {
        post.getContent().replaceAll("<[^>]*>", "");
        post.setContent(s);
      } catch (NullPointerException e) {
      }
    });

    model.addAttribute("writer", gson.toJson(allSubscribedWriterPost));
    list.add(gson.toJson(allSubscribedWriterPost));

    log.info("allSubscribedWriterPost => {}", allSubscribedWriterPost);

    if (member != null && num == 1) {
      model.addAttribute("subscribe", gson.toJson(libraryService.findAllSubscribedWriter(member.getLoginid())));
      list.add(gson.toJson(libraryService.findAllSubscribedWriter(member.getLoginid())));
    }
    return list;
  }
}