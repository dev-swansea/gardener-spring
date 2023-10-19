package com.gardener.mappers;

import com.gardener.domain.dto.AttachDTO;

import java.util.List;

public interface AttachMapper {

  public void insert(AttachDTO dto);

  public void delete(String uuid);

  public List<AttachDTO> findByPostnum(Long postnum);

}
