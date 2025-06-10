package edu.kh.todolist.model.service;

import java.util.List;
import java.util.Map;

import edu.kh.todolist.model.dto.Todo;

public interface TodoService{
	
	/**할일 목록 + 완료된 할 일 갯수 조회
	 * @return map
	 */
	Map<String, Object> selectAll();

	
	/** 할일 목록 조회
	 * @return todoList
	 */
	List<Todo> selectList();

	/**할일 추가
	 * @param todoTitle
	 * @param todoContent
	 * @return result
	 */
	int addTodo(String todoTitle, String todoContent); 
	
	/** 완료 여부 변경
	 * @param todo
	 * @return result
	 */
	int changeComplete(Todo todo);


	/** 전체 할일 개수 조회
	 * @return totalCount
	 */
	int getTotalCount();


	/**완료된 할 일 개수 조회
	 * @return completeCount
	 */
	int getCompleteCount();


////////////////////////////
	Todo todoDetail(int todoNo);
	
	int todoUpdate(Todo todo);

	int todoDelete(int todoNo);


	


	


	
		
}
	