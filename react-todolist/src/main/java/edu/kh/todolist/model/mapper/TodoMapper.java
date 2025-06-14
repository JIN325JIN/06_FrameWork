package edu.kh.todolist.model.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import edu.kh.todolist.model.dto.Todo;

/*
 * @Mapper
 * - Mybatis 에서 제공하는 어노테이션 
 * - Mybatis 에서 SQL과 JAVA 메서드를 연결해주는 인터페이스 (MAPPER)의 구현체를 스프링의 
 * 	BEAN으로 등록할 수있게 해주는 어노테이션.
 * 
 * 이 어노테이션을 붙이면 Spring이 Mapper 인터페이스를 인식하고, 자동으로 구현체 생성해줌.
 * -> 이 구현체가 BEAN으로 등록됨!
 * 
 * 
 * - 해당 어노테이션이 작성된 인터페이스는 namespace에 해당 인터페이스가 작성된 mapper.xml파일과 연결되어
 * 	 SQL호출/수행/결과 반환 가능
 * */

@Mapper
public interface TodoMapper {

	List<Todo> selectAll();

	int getCompleteCount();

	int addTodo(Todo todo);

	int changeComplete(Todo todo);

	int getTotalCount();

	Todo todoDetail(int todoNo);

	int todoUpdate(Todo todo);

	int todoDelete(int todoNo);
	
}
