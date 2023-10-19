package com.gardener.service;

import com.gardener.aop.exception.FindException;
import com.gardener.domain.Post;
import com.gardener.domain.dto.FavoriteDTO;
import com.gardener.mappers.FavoriteMapper;
import com.gardener.mappers.PostMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

@Service
@RequiredArgsConstructor
@Slf4j
public class FavoriteService {

  private final PostMapper postMapper;
  private final FavoriteMapper favoriteMapper;


  public Map<String, List<FavoriteDTO>> selectFavoByloginid(Long postnum, String loginid) throws FindException {
    List<FavoriteDTO> allFavorite = favoriteMapper.findFavoriteByLoginid(new FavoriteDTO(postnum, loginid));
    Map<String, List<FavoriteDTO>> map = new HashMap<>();
    List<FavoriteDTO> yesList = new ArrayList<>();
    List<FavoriteDTO> noList = new ArrayList<>();
    for (FavoriteDTO favorite : allFavorite) {
      log.info("favorite.getLoginid().equals(loginid) => {}", favorite);
      if (favorite.getLoginid().equals(loginid)) {
        yesList.add(favorite);
        map.put("yes", yesList);
      } else {
        noList.add(favorite);
        map.put("no", noList);
      }
    }
    log.info("넘길 map => {}", map);
    return map;
  }

  @Transactional(rollbackFor = FindException.class)
  public void insertFavorite(Long postnum, String loginid) throws FindException {
    Post post = postMapper.findBypostnum(postnum);
    FavoriteDTO dto = new FavoriteDTO(postnum, loginid);
    favoriteMapper.insertFavorite(dto);

    List<FavoriteDTO> allFavorite = favoriteMapper.findFavoriteByLoginid(dto);
    log.info("좋아요요오오 => {}", allFavorite);

    long count = allFavorite.stream().filter(e -> e.getLoginid().equals(loginid)).count();
    log.info("count => {}", count);
    if (count > 1) {
      throw new FindException();
    }
    log.info("post.getFavorite() => {}", post.getFavorite());
    postMapper.updateFavorite(postnum, post.getFavorite() + 1);
  }

  @Transactional
  public void deleteFavorite(Long postnum, String loginid) {
    favoriteMapper.deleteFavorite(new FavoriteDTO(postnum, loginid));
    Post post = postMapper.findBypostnum(postnum);
    int favorite = post.getFavorite();
    if (favorite < 0) {
      throw new IllegalStateException("좋아요 수가 0 이하");
    }
    postMapper.updateFavorite(postnum, favorite - 1);
  }
}
