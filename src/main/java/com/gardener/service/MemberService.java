package com.gardener.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gardener.aop.exception.AddException;
import com.gardener.aop.exception.FindException;
import com.gardener.domain.Member;
import com.gardener.mappers.MemberMapper;

import lombok.extern.log4j.Log4j;

@Service
@Log4j
public class MemberService {

	@Autowired
	private MemberMapper mapper;

	// 로그인
	public Member login(String loginId, String pwd) throws FindException {
		Member m = mapper.selectByLoginid(loginId);
		if (pwd.equals(m.getPwd())) {
			return m; // 로그인 성공
		} else {
			throw new FindException("로그인 실패");
		}
	}

	// 회원가입
	public void signup(Member m) throws AddException {
		log.info(" 넘어오는 서비스 => " + m);
		mapper.insert(m);
	}

	// ID중복확인
	public String idDupChk(String loginid) throws FindException {
		Member m = null;
		m = mapper.selectByLoginid(loginid);

		if (m != null) {
			log.info("사용 불가:" + loginid);
			System.out.println("이미 사용중인 아이디 입니다");
			// loginId에 해당 고객이 있는 경우(중복인 경우)
		} else {
			log.info("사용 가능:" + loginid);
			System.out.println("사용 가능한 아이디 입니다");
			// loginId에 해당 고객이 없는 경우(id 사용가능한 경우)
			return null;
		}
		return m.getLoginid();
	}

	// 필명 중복확인
	public String NicknameDupChk(String nickname) throws FindException {
		Member m = null;
		m = mapper.selectByNickname(nickname);

		if (m != null) {
			log.info(nickname);
			System.out.println("이미 사용중인 필명입니다");
			return m.getNickname();
			// 필명에 해당 고객이 있는 경우(중복인 경우)
		} else {
			log.info(nickname);
			System.out.println("사용 가능한 필명 입니다");
			// 필명에 해당 고객이 없는 경우(필명 사용 가능)
			return null;
		}
	}

	// 아이디찾기
	public String findLoginid(String name, String email) throws FindException {

		Member m = mapper.selectByNickname(name);

		if (m != null) {
			if (name.equals(m.getNickname()) && email.equals(m.getEmail())) {
				return m.getLoginid();
			} else {
				throw new FindException("잘못된 정보입니다");
			}
		} else {
			throw new FindException("잘못된 정보입니다");
		}
	}

	// 비밀번호찾기
	public String findPwd(String loginid, String email) throws FindException {

		Member m = mapper.selectByLoginid(loginid);

		if (m != null) {
			if (loginid.equals(m.getLoginid()) && email.equals(m.getEmail())) {
				return m.getPwd();
			} else {
				throw new FindException("잘못된 정보입니다, 다시 입력해주세요");
			}
		} else {
			throw new FindException("잘못된 정보입니다. 다시 입력해주세요");
		}
	}

}
