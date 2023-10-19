package com.gardener.controller;

import com.gardener.aop.exception.FindException;
import com.gardener.domain.Member;
import com.gardener.domain.Post;
import com.gardener.service.LibraryService;
import com.gardener.service.PostService;
import com.google.gson.Gson;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;

@RequiredArgsConstructor
@RequestMapping("/post")
@RestController
@Slf4j
public class PostApiController {

  private final PostService postService;
  private final LibraryService libraryService;

  @PostMapping(produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
  public Long savePost(@RequestBody Post post, HttpSession session) {
    Member member = (Member) session.getAttribute("member");
    post.setLoginid(member.getLoginid());
    Long postnum = 0L;
    if (post.getAttachDTOList() != null || post.getAttachDTOList().size() > 0) {
      postnum = postService.savePost(post, post.getAttachDTOList());
    } else {
      postnum = postService.savePost(post);
    }

    return postnum;
  }


  @GetMapping("/{postnum}")
  public ModelAndView findPostByPostnum(@PathVariable Long postnum, HttpSession session) throws FindException {
    Member member = (Member) session.getAttribute("member");
    Post post = postService.findPostByPostnum(postnum);
    Gson gson = new Gson();
    String subscribe = "";

    if (member != null) {
      subscribe = libraryService.findSubscribe(member.getLoginid(), post.getLoginid());
    }

    if (post == null) {
      throw new FindException();
    }

    ModelAndView mv = new ModelAndView();
    mv.addObject("post", gson.toJson(post));
    mv.addObject("subscribe", gson.toJson(subscribe));
    mv.setViewName("/post");

    return mv;
  }

  @RequestMapping(value = "/{postnum}", method = {RequestMethod.PATCH, RequestMethod.PUT}, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
  public Long updatePostBypostnum(@RequestBody Post post, HttpSession session) {
    Member member = (Member) session.getAttribute("member");
    post.setLoginid(member.getLoginid());
    postService.updatePostByPostnum(post);
    return post.getPostnum();
  }

  @DeleteMapping("/{postnum}")
  public @ResponseBody Long deletePostByPostnum(@PathVariable Long postnum) {
    return postService.deletePostByPostnum(postnum);
  }

}

