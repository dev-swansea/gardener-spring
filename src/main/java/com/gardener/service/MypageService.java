package com.gardener.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gardener.aop.exception.FindException;
import com.gardener.aop.exception.UpdateException;
import com.gardener.domain.Member;
import com.gardener.mappers.MemberMapper;

import lombok.extern.log4j.Log4j;

@Service
@Log4j
public class MypageService {

	@Autowired
	private MemberMapper mapper;

	public Member selectByLoginid(String loginid) throws FindException {
		return mapper.selectByLoginid(loginid);
	}

	public void updateMember(Member member) throws UpdateException {
		try {
			mapper.updateMember(member); // Member 정보 업데이트
		} catch (Exception e) {
			throw new UpdateException("Failed to update member information.", e);
		}
	}

	public void updateprofile(Member member) throws UpdateException {
		try {
			mapper.updateMember(member);
		} catch (Exception e) {
			throw new UpdateException("Failed to update member information.", e);
		}
	}

	public boolean deleteMember(String loginid) {
		return mapper.deleteMember(loginid);
	}

	public boolean deleteapply(String loginid) {
		return mapper.deleteapply(loginid);
	}
}
