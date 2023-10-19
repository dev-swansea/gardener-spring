package com.gardener.service;

import com.gardener.domain.Post;
import com.gardener.domain.dto.AttachDTO;
import com.gardener.mappers.AttachMapper;
import com.gardener.mappers.PostMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class PostService {

  private final PostMapper postMapper;
  private final AttachMapper attachMapper;

  public Long savePost(Post post) {
    postMapper.insert(post);
    return post.getPostnum();
  }

  @Transactional
  public Long savePost(Post post, List<AttachDTO> dto) {
    postMapper.insert(post);
    dto.forEach(d -> {
      d.setPostnum(post.getPostnum());
      attachMapper.insert(d);
    });
    return post.getPostnum();
  }

  public Post findPostByPostnum(Long postnum) {
    return postMapper.findBypostnum(postnum);
  }

  public Long updatePostByPostnum(Post post) {
    return postMapper.updatePost(post);
  }

  public void updateFvorite(Long postnum, int favorite) {
    postMapper.updateFavorite(postnum, favorite);
  }

  public Long deletePostByPostnum(Long postnum) {
    postMapper.deletePost(postnum);
    return postnum;
  }

}
