package com.gardener.mappers;

import com.gardener.domain.dto.FavoriteDTO;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface FavoriteMapper {
  void insertFavorite(FavoriteDTO dto);


  List<FavoriteDTO> findFavoriteByLoginid(FavoriteDTO dto);

  void deleteFavorite(FavoriteDTO dto);
}
