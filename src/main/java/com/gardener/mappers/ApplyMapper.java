package com.gardener.mappers;

import com.gardener.domain.Writer;

public interface ApplyMapper {

	Writer selectByLoginid(String loginid);

	boolean insertWriter(String loginid);

}
