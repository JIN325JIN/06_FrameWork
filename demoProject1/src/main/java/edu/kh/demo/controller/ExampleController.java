package edu.kh.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller//요청/응답 제어 역할 명시 + Bean 등록
public class ExampleController {

	//요청주소 
	//  todo/test/insert /todo/test/update,   /todo/delete
	
	
	
	/* 1) @RequestMapping : ("주소") 
	 * 
	 * 2)@GetMapping("주소")    : GET (조회) 방식 요청 매핑
	 * 	 @PostMapping("주소")   : POST (삽입) 방식 요청 매핑
	 * 	 @PutMapping("주소")    : PUT (수정) 방식 요청 매핑 (form, a 태그 요청 불가)
	 *   @DeleteMapping("주소") : DELETE  (삭제) 방식 요청 매핑 (form, a 태그 요청 불가)
	 *   
	 *   
	 *   
	 * */
	
	
	/*"/"를 앞에 안붙이는 이유 
	 * 
	 * 1)Spring  Boot에서는 요청주소 앞에 "/"가 없어도 요청처리가 잘 된다! 오류 발생 X
	 * 
	 * 2) 프로젝트를 AWS같은 호스팅 서비스를 이용하여 배포시 리눅스 OS를 이용하면 BUILD하는 과정에서 오류 발생.....
	 * */

	
	@GetMapping("example")// /example GET방식 요청 매핑 : 앞으로는 /를 빼서 표기
	public String exampleMethod() {
			
		
		//forward 하려는 html 파일 경로 return작성
		//단, ViewResolver가 제공하는 타임리프의 접두사, 접미사 제외하고 작성
		
		//접두사 : classpath:/templates/
		//접미사 : .html

		
		return "example";
		//classpath:/templates/example.html
	}

}
