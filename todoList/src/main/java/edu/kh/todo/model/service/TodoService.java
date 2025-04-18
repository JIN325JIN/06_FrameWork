package edu.kh.todo.model.service;

import java.util.List;
import java.util.Map;

import edu.kh.todo.model.dto.Todo;



/**
 * 
 */
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


	/** 완료 여부 변경
	 * @param todo
	 * @return result
	 */
	int changeComplete(Todo todo);


	/** 할일 수정
	 * @return result
	 */
	int todoUpdate(Todo todo);
	
	
	/** 할일 삭제
	 * @param todoNo
	 * @return result
	 */
	int todoDelete(int todoNo);


	/** 전체 할일 개수 조회
	 * @return totalCount
	 */
	int getTotalCount();


	/**완료된 할 일 개수 조회
	 * @return completeCount
	 */
	int getCompleteCount();


	/** 할일 목록 조회
	 * @return todoList
	 */
	List<Todo> selectList();
		
}
	