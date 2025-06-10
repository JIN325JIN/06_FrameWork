package edu.kh.todolist.model.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


import edu.kh.todolist.model.dto.Todo;
import edu.kh.todolist.model.mapper.TodoMapper;


//@Transactional
//- 트랜잭션 처리를 수행하라고 지시하는 어노테이션 : 스프링한테 시키는 : 개발자가 커밋롤백안함!
//- 정상 코드 수행 시 COMMIT
//- error 발생시 ROLLBACK
//-기본값 : SERVICE 내부코드 수행중 RUNTIME EXCEPTION 발생시 rollback
//rollback 속성 : 어떤 예외 발생했을때 rollback할지 지정하는 속성
//Exception.class ==모든 예외 발생 시 rollback 하겠다.

@Transactional(rollbackFor=Exception.class)
@Service //비즈니스 로직 ( 데이터 가공, 트랜잭션 처리등) 역할 명시  + BEAN 등록 (MVC+IOC 적용된)
public class TodoServiceImpl implements TodoService {
	
	@Autowired
	private TodoMapper mapper;//이제 DAO안쓰고 바로 SERVICE->MAPPER연결
	
	@Override
	public Map<String, Object> selectAll() {
		
		//1. 할 일 목록 조회
		List<Todo> todoList = mapper.selectAll();
		
		//2. 완료 된 할 일 개수 조회
		int completeCount = mapper.getCompleteCount();
		
		
		//3. 위 두개 결과값을 Map으로 묶어서 반환
		Map<String,Object> map = new HashMap<>();
		
		map.put("todoList",todoList);
		map.put("completeCount",completeCount);
		
		return map;
	}

	@Override
	public int addTodo(String todoTitle, String todoContent) {
		
		//마이 바티스에서 SQL에 전달 할 수 있는 파라미터 개수는 오직 1개!!!
		//-> todoTitle,todoContent 여러개인 파라미터를 전달 하려면
		//Todo DTO로 묶어서 전달 하면 된다!
		//List는 index관리고key 관리니까 list보다는 Map사용!
		
		Todo todo = new Todo();
		todo.setTodoTitle(todoTitle);
		todo.setTodoContent(todoContent);
		
		return mapper.addTodo(todo);
	}

	@Override
	public int changeComplete(Todo todo) {
		return mapper.changeComplete(todo);
	}

	@Override
	public int getTotalCount() {
		return mapper.getTotalCount();
	}

	@Override
	public int getCompleteCount() {
		return mapper.getCompleteCount();
	}

	@Override
	public List<Todo> selectList() {
		return mapper.selectAll();
	}

	@Override
	public Todo todoDetail(int todoNo) {
		return mapper.todoDetail(todoNo);
	}

	@Override
	public int todoUpdate(Todo todo) {
		return mapper.todoUpdate(todo);
	}

	@Override
	public int todoDelete(int todoNo) {
		return mapper.todoDelete(todoNo);
	}

}
