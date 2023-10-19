package com.gardener.mappers;

import com.gardener.domain.Member;
import com.gardener.domain.Post;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface LibraryMapper {

  Integer getSubscribe(@Param("loginid") String loginid, @Param("writerId") String writerId);

  List<Post> getAllFavoritePostWithPaging(@Param("loginid") String loginid, @Param("num") int num);

  void insertSubscribe(@Param("loginid") String loginid, @Param("writerId") String writerId);

  void deleteSubscibe(@Param("loginid") String loginid, @Param("writerId") String writerId);

  List<Member> getAllSubscribeWithPaging(@Param("loginid") String loginid, @Param("num") int num);

  List<Member> getAllSubscribedWriter(String loginid);

  List<Post> getAllSubscribedWriterPost(@Param("writerId") String writerId, @Param("num") int num);


}
