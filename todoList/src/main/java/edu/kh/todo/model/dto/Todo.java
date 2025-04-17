package edu.kh.todo.model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data //getter setter toString
@AllArgsConstructor
@NoArgsConstructor
public class Todo {
	
	
	
	//DB의 테이블을 보고 카멜케이스로 필드 선언
	
	private int todoNo;			//할 일 번호
	private String todoTitle;	//할 일 제목
	private String todoContent;	//할 일 내용
	private String complete;	//할 일 완료여부 ("Y","N")
	private String regDate;		//할 일 등록일
	
	
	

}
