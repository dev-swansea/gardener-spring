package com.gardener.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gardener.aop.exception.FindException;
import com.gardener.aop.exception.UpdateException;
import com.gardener.domain.Member;
import com.gardener.domain.Search;
import com.gardener.mappers.MemberMapper;
import com.gardener.mappers.MygardenMapper;

import lombok.extern.log4j.Log4j;

@Service
@Log4j
public class MygardenService {

	@Autowired
	private MygardenMapper mapper;

	public List<Search> Mygarden(String loginid){
		return mapper.Mygarden(loginid);
	}

}
