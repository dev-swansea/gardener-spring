package com.gardener.service;

import com.gardener.aop.exception.FindException;
import com.gardener.domain.Member;
import com.gardener.domain.Post;
import com.gardener.mappers.LibraryMapper;
import com.gardener.mappers.PostMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Slf4j
public class LibraryService {

  private final LibraryMapper libraryMapper;

  public String findSubscribe(String loginid, String writerId) throws FindException {
    Optional<Integer> subscribe = Optional.ofNullable(libraryMapper.getSubscribe(loginid, writerId));
    if (subscribe.isPresent()) {
      return writerId;
    }
    return "없음";
  }

  public List<Post> findAllFavoritePostWithPaging(String loginid, int num) {
    return libraryMapper.getAllFavoritePostWithPaging(loginid, num);
  }

  public void insertSubscribe(String loginid, String writerId) throws FindException {
    Optional<Integer> subscribe = Optional.ofNullable(libraryMapper.getSubscribe(loginid, writerId));
    if (subscribe.isPresent()) {
      libraryMapper.deleteSubscibe(loginid, writerId);
      throw new FindException("이미 존재");
    } else {
      libraryMapper.insertSubscribe(loginid, writerId);
    }
  }


  public List<Member> findAllSubscribeWithPaging(String loginid, int num) {
    return libraryMapper.getAllSubscribeWithPaging(loginid, num);
  }

  public List<Member> findAllSubscribedWriter(String loginid) {
    log.info("libraryMapper.getAllSubscribedWriter(loginid) => {}", libraryMapper.getAllSubscribedWriter(loginid));
    return libraryMapper.getAllSubscribedWriter(loginid);
  }

  public List<Post> findAllSubscribedWriterPost(String writerId, int num) {
    return libraryMapper.getAllSubscribedWriterPost(writerId, num);
  }

}