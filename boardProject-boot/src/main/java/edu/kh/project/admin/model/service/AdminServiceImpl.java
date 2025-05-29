package edu.kh.project.admin.model.service;

import java.util.List;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import edu.kh.project.admin.model.mapper.AdminMapper;
import edu.kh.project.board.model.dto.Board;
import edu.kh.project.member.model.dto.Member;
import lombok.RequiredArgsConstructor;

@Service
@Transactional(rollbackFor = Exception.class)
@RequiredArgsConstructor
public class AdminServiceImpl implements AdminService{

	private final AdminMapper mapper;

	private final BCryptPasswordEncoder bcrypt;
	
	//	관리자 로그인
	@Override
	public Member login(Member inputMember) {

		Member loginMember = mapper.login(inputMember.getMemberEmail());
	
		if(loginMember==null) {
			return null;
		}
	
		if(!bcrypt.matches(inputMember.getMemberPw(), loginMember.getMemberPw())) {
			return null;
		}
	
		loginMember.setMemberPw(null);//패스워드는 어디에도 저장 x
		
		return loginMember;
	}
	//-------------------------------------
	// 신규 가입 회원
	@Override
	public List<Member> recentSignUp() {
		return mapper.recentSignUp();
	}
	
	//-------------------------------------------------
	@Override
	public Board maxReadCount() {
		return mapper.maxReadCount();
	}

	@Override
	public Board maxLikeCount() {
		return mapper.maxLikeCount();
	}

	@Override
	public Board maxCommentCount() {
		return mapper.maxCommentCount();
	}


	

	
}
