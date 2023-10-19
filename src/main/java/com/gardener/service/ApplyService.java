package com.gardener.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gardener.aop.exception.FindException;
import com.gardener.domain.Writer;
import com.gardener.mappers.ApplyMapper;

import lombok.extern.log4j.Log4j;

@Service
@Log4j
public class ApplyService {

	@Autowired
	private ApplyMapper mapper;

	public Writer selectByLoginid(String loginid) throws FindException {
		return mapper.selectByLoginid(loginid);
	}

	public boolean insertWriter(String loginid) {
		return mapper.insertWriter(loginid);

	}

}
