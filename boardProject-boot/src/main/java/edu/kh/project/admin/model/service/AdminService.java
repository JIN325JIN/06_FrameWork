package edu.kh.project.admin.model.service;

import java.util.List;

import edu.kh.project.board.model.dto.Board;
import edu.kh.project.member.model.dto.Member;

public interface AdminService {

	/** 관리자 로그인
	 * @param inputMember
	 * @return
	 */
	Member login(Member inputMember);

	/** 신규 가입 회원
	 * @return
	 */
	List<Member> recentSignUp(); 
	
	/** 최대 조회수 게시글 조회
	 * @return
	 */
	Board maxReadCount();
	
	/** 최대 좋아요 게시글 조회
	 * @return
	 */
	Board maxLikeCount();
	
	/** 최대 댓글수 게시글 조회
	 * @return
	 */
	Board maxCommentCount();

	
} 