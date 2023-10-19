package com.gardener.service;


import com.gardener.aop.exception.FindException;
import com.gardener.domain.Reply;
import com.gardener.mappers.PostMapper;
import com.gardener.mappers.ReplyMapper;
import com.gardener.util.Criteria;
import com.gardener.util.ReplyPaging;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
public class ReplyService {

  private final ReplyMapper replyMapper;
  private final PostMapper postMapper;

  @Transactional
  public int insert(Reply reply) {
    int result = replyMapper.insert(reply);
    int totalCnt = replyMapper.count(reply.getPostnum());
    log.info("totalCnt => {}", totalCnt);

    postMapper.updateTotalCnt(reply.getPostnum(), totalCnt);
    return result;
  }

  public Reply findById(Long id) throws FindException {
    return replyMapper.findById(id);
  }

  public List<Reply> findAll(Criteria cri, Long postnum) throws FindException {
    return replyMapper.findAll(cri, postnum);
  }

  public ReplyPaging findList(Criteria cri, Long postnum) throws FindException {
    return new ReplyPaging(replyMapper.count(postnum), replyMapper.findAll(cri, postnum));
  }

  public int delete(Long id) {
    return replyMapper.delete(id);
  }

  public int update(Reply reply) {
    return replyMapper.update(reply);
  }

  public int count(Long postnum) {
    return replyMapper.count(postnum);
  }

}

