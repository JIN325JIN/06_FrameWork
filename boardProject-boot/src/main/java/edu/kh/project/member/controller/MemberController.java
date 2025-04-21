package edu.kh.project.member.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;


import edu.kh.project.member.model.dto.Member;
import edu.kh.project.member.model.service.MemberService;
import lombok.extern.slf4j.Slf4j;



@SessionAttributes({"loginMember"})
@Controller
@RequestMapping("member")
@Slf4j
public class MemberController {

	
	@Autowired
	private MemberService service;
	
	
	
	/*[로그인]
	 * - 특정 사이트에 아이디/비밀번호 등을 입력해서
	 * 해당 정보가 DB에 있으면 조회/서비스 이용
	 * 
	 * - 로그인 한 회원 정보는 session에 기록하여 
	 * 로그아웃 또는 브라우저 종료 시 까지 해당 정보를 계속 이용할 수 있게 해야함
	 * (로그아웃 = 세션 만료)
	 * 
	 * */
	
	
	/** 로그인 메서드
	 * @param inputMember : 커맨드 객체(@ModelAttribute 생략)
	 * 						memberEmail,memberPw 세팅된 상태
	 * 
	 * @param ra: 리다이렉트시 request scope인 애를 잠시 session scope로 변경했다가 다시 복귀시킴
	 * request scope->session scope->request로 데이터 전달
	 * 
	 * 
	 * @param model : 데이터 전달용 객체 ( 기본 request scope) 
	 * /@SessionAttributes 어노테이션과 함께 사용시 session scope 이동)
	 * 
	 * @return 
	 */
	@PostMapping("login")
	public String login(Member inputMember,RedirectAttributes ra,Model model) {
		
		//로그인 서비스 호출
		Member loginMember = service.login(inputMember);
		
		
		//로그인 실패시
		if(loginMember==null) {
			
			ra.addFlashAttribute("message","아이디 또는 비밀번호가 일치하지 않습니다.");
			
		}else {//로그인 성공시
					
			//session scope 에 loginMember 추가
			model.addAttribute("loginMember",loginMember);
			//1단계 : model을 이용하여 request scope에 세팅됨
			
			//2단계 : 클래스위에 @SessionAttributes()어노테이션 작성하여 session scope로 loginMember를 이동
		}
		
		
		
		
		
		return "redirect:/";//메인 페이지 재요청
		
		
		
		
	}
	
}
