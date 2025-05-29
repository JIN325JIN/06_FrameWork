package edu.kh.project.admin.model.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import edu.kh.project.board.model.dto.Board;
import edu.kh.project.member.model.dto.Member;

@Mapper
public interface AdminMapper {

	/** 관리자 로그인
	 * @param memberEmail
	 * @return
	 */
	Member login(String memberEmail);
	
	
	/** 신규 가입 회원 조회
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