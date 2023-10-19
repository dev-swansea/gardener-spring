package com.gardener.util;

import com.gardener.domain.Reply;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;

import java.util.List;

@ToString
@Getter
@AllArgsConstructor
public class ReplyPaging {

  private int replyCnt;
  private List<Reply> list;

}
