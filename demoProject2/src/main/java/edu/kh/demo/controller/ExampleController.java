package edu.kh.demo.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import edu.kh.demo.model.dto.Student;
import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;

@Controller
@RequestMapping("example") //example로 시작하는 주소를 해당 컨트폴러에 매핑
@Slf4j //lombok 라이브러리가 제공하는 로그객체 어노테이션


	public class ExampleController {
	
	@GetMapping("ex1") //example/ex1 GET방식 요청 매핑
	public String ex1(HttpServletRequest req, Model model) {
		
		
		/*Model
		 * 
		 * import org.springframework.ui.Model;
		 * -Spring 에서 데이터 전달 역할을 하는 객체
		 * 임포트할때 유의하기!!!!
		 * 
		 * 기본 scope: request
		 * 
		 * -@SessionAttribute와 함께 사용시 session scope 변환
		 * 
		 * [기본 사용법]
		 * model.addAttribute("key",value);
		 * 
		 * */
		
		//Servlet 내장객체 범위
		//page<request<session<application
		//request : HTTPServletRequest req
		req.setAttribute("test1", "HttpServletRequest로 전달한 값");
		model.addAttribute("test2","MODEL로 전달한 값");
		
		
		
		//단일 값(숫자, 문자열) Model을 이용해서 html 로 전달
		model.addAttribute("productName","종이컵");
		model.addAttribute("price",2000);
		
		
		//복수 값(배열,List)Model 이용해서 html로 전달
		List<String> fruitList= new ArrayList<>();
		fruitList.add("사과");
		fruitList.add("딸기");
		fruitList.add("바나나");
		
		
		model.addAttribute("fruitList",fruitList);
		
		//DTO객체 Model을 이용해서 html 로 전달
		Student std = new Student();
		
		std.setStudentNo("12345");
		std.setName("홍길동");
		std.setAge(22);
		
		model.addAttribute("std",std);
		
		//List<Student>객체 Model을 이용해서 html로 전달
		List<Student> stdList = new ArrayList<>();
		
		
		stdList.add(new Student("11111","김일번",20));
		stdList.add(new Student("22222","최이번",23));
		stdList.add(new Student("33333","홍삼번",22));
		
		
		
		
		model.addAttribute("stdList",stdList);
		
		
		
		
		
		
		//src/main/resources/templates/example/ex1.html로 forward
		return "example/ex1";
	}

	
	@PostMapping("ex2")
	public String ex2(Model model) {// /example/ex2 post 방식 요청 매핑
	
		model.addAttribute("str","<h1>테스트중 &times;</h1>");
		
		return "example/ex2";
		//접두사 접미사 항상 생각하고 파일 vs코드에서 만들기
	}
	
	@GetMapping("ex3")
	public String ex3(Model model) {
		
		model.addAttribute("key","제목");
		model.addAttribute("query","검색어");
		model.addAttribute("boardNo",10);
		
		return "example/ex3";
	}
	
	@GetMapping("ex3/{path:[0-9]}")
	public String pathVariableTest(@PathVariable("path") int path) {
		//controller에서 해야하는 일이 동일한 경우에
		//example/ex3/1, example/ex3/2, example/ex3/3 ......
		//주소 중 {path}부분의 값을 가져와서 매개변수로 저장
		//controller단의 메서드에서 사용할 수 있도록 해줌
		//+ request scope 자동 세팅
		
		
		//게시판 같은 경우는 응답페이지는 같고, 맨뒤에 데이터만 갈아끼우는것임
		//forward하는 html이 여러개 일 필요가 없다.
		
		log.debug("path:"+path);
		
		return "example/testResult";
	}
	

	
	@GetMapping("ex4")
	public String ex4(Model model) {
		
		
		Student std = new Student("67890","잠만보",22);
		model.addAttribute("std",std);
		model.addAttribute("num",100);
		
		
		return "example/ex4";
	}
	
	@GetMapping("ex5")
	public String ex5(Model model) {
		
		model.addAttribute("message","타임리프+JavaScript 사용연습");
		model.addAttribute("num1",12345);
		
		Student std = new Student();
		std.setStudentNo("22222");
		model.addAttribute("std",std);
		
		return"example/ex5";
	}
}
