package edu.kh.project.admin.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.SessionAttributes;

import edu.kh.project.admin.model.service.AdminService;
import edu.kh.project.board.model.dto.Board;
import edu.kh.project.member.model.dto.Member;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;


@SessionAttributes({"loginMember"})
@RestController
@CrossOrigin(origins="http://localhost:5173"/*,allowCredentials ="true"*/)
//,allowCredentials ="true" : 클라이언트로부터 들어오는 쿠키 허용
@Slf4j
@RequestMapping("admin")
@RequiredArgsConstructor

public class AdminController {
	//매서드 하나하나마다 responsebody 달 필요없다.

	private final AdminService service;
	
	@PostMapping("login")
	public Member login(@RequestBody Member inputMember, Model model) {
		
		
		Member loginMember = service.login(inputMember);
		
		
		if(loginMember==null) {
			return null;
		}
		
		model.addAttribute(loginMember);
		return loginMember;
	}
	
	
	/** 관리자 로그아웃
	 * @return
	 */
	@GetMapping("logout")
	public ResponseEntity <String> logout(HttpSession session){
		
		//ResponseEntity
		//Spring에서 제공하는 Http응답 데이터를 커스터 마이징 할 수 있도록
		//지원하는 클래스 
		//-> HTTP 상태코드 , 헤더, 응답 본문 (body)을 모두 설정 가능 
		try {
			session.invalidate(); // 세션 무효화 처리 
			return ResponseEntity.status(HttpStatus.OK) //200 : 정상 처리 
					.body("로그아웃이 완료 되었습니다."); 
			
		} catch (Exception e) {
			//세션 무효화 중 예외 발생한 경우
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
					.body("로그아웃 중 예외 발생 :" + e.getMessage());
		
		}
	}
	//---------------------신규 가입 회원 조회 ------
	@GetMapping("recentSignUp")
	public ResponseEntity<List<Member>> recentSignUp() {
	    try {
	        List<Member> recentMembers = service.recentSignUp(); // 이미 List<Member> 반환
	        return ResponseEntity.status(HttpStatus.OK).body(recentMembers);
	    } catch (Exception e) {
	        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
	    }
	}
	//-----------------------------통계----------------------------
	/** 최대 조회수 게시글 조회 
	 * @return 
	 */
	@GetMapping("maxReadCount")
	public ResponseEntity<Object> maxReadCount(){
		try {
			Board board = service.maxReadCount();
			return ResponseEntity.status(HttpStatus.OK).body(board);
		}catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
		}
	}
	
	/** 최대 좋아요 게시글 조회 
	 * @return 
	 */
	@GetMapping("maxLikeCount")
	public ResponseEntity<Object> maxLikeCount(){
		try {
			Board board = service.maxLikeCount();
			return ResponseEntity.status(HttpStatus.OK).body(board);
		}catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
		}
	}
	
	/** 최대 댓글수 게시글 조회 
	 * @return 
	 */
	@GetMapping("maxCommentCount")
	public ResponseEntity<Object> maxCommentCount(){
		try {
			Board board = service.maxCommentCount();
			return ResponseEntity.status(HttpStatus.OK).body(board);
		}catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
		}
	}
}
