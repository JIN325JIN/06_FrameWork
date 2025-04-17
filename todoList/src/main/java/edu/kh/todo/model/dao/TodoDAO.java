package edu.kh.todo.model.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import edu.kh.todo.model.mapper.TodoMapper;

@Repository//DAO 계층 역할 명시 + BEAN 등록 (MVC+IOC)
public class TodoDAO {

	
	//DAO는 todoMapper와 같이 이용해야함
	
	@Autowired //의존성 주입 (DI)-> 같은 타입 + 상속관계 BEAN을 의존성 주입 (DI)
	private TodoMapper mapper;//mapper에는 TodoMapper의 구현체가 의존성 주입됨
							  //그 구현체가 sqlSessionTemplate이용

	public String testTitle() {
		
		//결과 저장용 변수 선언
		//SQL작성
		//pstmt/ResultSet등 객체 생성/ 세팅/사용
		//결과값 얻어온 것 가공
		
		return mapper.testTitle();
	}
	
	
	
	
}
