package edu.kh.todo.model.service;

import java.util.Map;

import edu.kh.todo.model.dto.Todo;



public interface TodoService{


	//추상메서드만 넣고 상속은 todoserviceimpl이 상속받음
	
	/**(TEST) todoNo가 1인 할일 제목 조회
	 * @return title
	 */
	String testTitle();

	
	/**할일 목록 + 완료된 할 일 갯수 조회
	 * @return map
	 */
	Map<String, Object> selectAll();


	/**할일 추가
	 * @param todoTitle
	 * @param todoContent
	 * @return result
	 */
	int addTodo(String todoTitle, String todoContent); 
	
	
	/** 할일 상세 조회
	 * @param todoNo
	 * @return todo
	 */
	Todo todoDetail(int todoNo);
	
}
	