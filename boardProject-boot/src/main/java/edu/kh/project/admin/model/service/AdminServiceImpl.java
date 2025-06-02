package edu.kh.project.admin.model.service;

import java.util.List;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import edu.kh.project.admin.model.mapper.AdminMapper;
import edu.kh.project.board.model.dto.Board;
import edu.kh.project.common.util.Utility;
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
	// 최대 조회수, 최대 좋아요수, 최대 댓글수 ----------------
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

	// 신규 가입회원 (숙제)-------------------------------------
		
	@Override
	public List<Member> recentSignUp() {
		return mapper.recentSignUp();
	}
		
	//-------------------------------------------------
	
	// 탈퇴 회원 조회
	@Override
	public List<Member> selectWithdrawnMemberList() {
		return mapper.selectWithdrawnMemberList();
	}
		
	// 삭제된 게시글 조회
	@Override
	public List<Board> selectDeleteBoardList() {
		return mapper.selectDeleteBoardList();
	}
	
	//탈퇴 회원 복구
	@Override
	public int restoreMember(int memberNo) {
		return mapper.restoreMember(memberNo);
	}
	
	// 삭제된 게시글 복구
	@Override
	public int restoreBoard(int boardNo) {
		return mapper.restoreBoard(boardNo);
	}
	//-------------------6/2관리자 계정 발급--------------------------
	
	// 관리자 이메일 중복 여부 검사
	@Override
	public int checkEmail(String memberEmail) {
		return mapper.checkEmail(memberEmail);
	}
	
	// 관리자 계정 발급
	@Override
	public String createAdminAccount(Member member) {
		
		/* common/util에 랜덤 비밀번호 발급 메서드 ( 관리자용 )만들겠다.*/
		
		
		//1. 영어 (대소문자) 6자리 난수로 만든 비밀번호를 암호화 한 값 구하기 
		String rawPw = Utility.generatePassword(); //평문 비밀번호
		
		//2. 평문 비밀번호를 암호화 하여 저장
		String encPw = bcrypt.encode(rawPw); // 암호화 비밀번호 
		
		//3. member 에 암호화 된 비밀번호 세팅 
		member.setMemberPw(encPw);
		
		//4. DB에 암호화된 비밀번호가 세팅된 member를 전달하여 계정 발급
		int result = mapper.createAdminAccount(member);
		
		//5. 계정 발급 정상처리 되었다면, 발급된 (평문) 비밀번호 리턴 
		if(result>0) {
			return rawPw;
		}else {
			return null;
		}
	}
	
	//관리자 목록 조회
	@Override
	public List<Member> selectAdminAccountList() {
		
		return mapper.selectAdminAccountList();
	}
	
	
}
