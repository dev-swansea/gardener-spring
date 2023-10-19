package com.gardener.controller;

import com.gardener.aop.exception.FindException;
import com.gardener.domain.Member;
import com.gardener.domain.Reply;
import com.gardener.service.ReplyService;
import com.gardener.util.Criteria;
import com.gardener.util.ReplyPaging;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/reply")
@Slf4j
public class ReplyController {

  private final ReplyService replyService;

  @PostMapping
  public ResponseEntity<String> insertReply(@RequestBody Reply reply, HttpSession session) {
    Member member = (Member) session.getAttribute("member");
    reply.setLoginid(member.getLoginid());
    int result = replyService.insert(reply);

    return result == 1 ? new ResponseEntity<>("Success", HttpStatus.OK)
            : new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
  }

  @GetMapping(value = "/{postnum}/{page}")
  public ResponseEntity<ReplyPaging> getAllReply(@PathVariable Long postnum, @PathVariable int page) throws FindException {
    Criteria cri = new Criteria(page, 5);
    List<Reply> allReply = replyService.findAll(cri, postnum);
    int count = replyService.count(postnum);

    return new ResponseEntity<>(new ReplyPaging(count, allReply), HttpStatus.OK);
  }

  @RequestMapping(value = "/{id}", method = {RequestMethod.PUT, RequestMethod.PATCH})
  public ResponseEntity<String> modify(HttpSession session, @RequestBody Reply reply, @PathVariable Long id) {
    Member member = (Member) session.getAttribute("member");
    reply.setLoginid(member.getLoginid());
    int result = replyService.update(reply);

    return result == 1
            ? new ResponseEntity<>("result " + result, HttpStatus.OK)
            : new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
  }

  @DeleteMapping("/{id}")
  public ResponseEntity<String> remove(@PathVariable Long id) {
    int result = replyService.delete(id);

    return result == 1
            ? new ResponseEntity<>("result " + result, HttpStatus.OK)
            : new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
  }
}
