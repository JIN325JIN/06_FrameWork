package edu.kh.todolist.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;


import edu.kh.todolist.model.dto.Todo;
import edu.kh.todolist.model.service.TodoService;
import lombok.extern.slf4j.Slf4j;

@CrossOrigin(origins="http://localhost:5173"/*,allowCredentials ="true"*/)
//,allowCredentials ="true" : 클라이언트로부터 들어오는 쿠키 허용
@Slf4j
@RestController
@RequestMapping("todo")
public class TodoController {

	@Autowired
	private TodoService service;
	
	
	@GetMapping("selectAll")
	public ResponseEntity<Object> selectAll() {
	    try {
	        Map<String, Object> result = service.selectAll(); // 할 일 목록 가져오기
	        return ResponseEntity.ok(result); // 프론트에 JSON 응답
	    } catch (Exception e) {
	        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
	            .body("TODO 조회 중 문제 발생: " + e.getMessage());
	    }
	}
	
	
	/** todo 추가하기
	 * @return
	 */
	@PostMapping("add")
	public ResponseEntity <Object> addTodo(@RequestBody Todo todo){
		
		try {
			        int result = service.addTodo(todo.getTodoTitle(), todo.getTodoContent());

			        if(result > 0) {
			            return ResponseEntity.ok("할 일 추가 성공!");
			        }
			        else {
			            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("추가 실패");
			        }
		}catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("TODO 추가 중 문제발생 : " + e.getMessage());
		}
	}
	
	
	@GetMapping("detail")
	public ResponseEntity<Object> todoDetail (@RequestParam("todoNo")int todoNo) {
		
		try {
	        Todo todo = service.todoDetail(todoNo);

	        if (todo != null) {
	            return ResponseEntity.ok(todo);
	        } else {
	            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("해당 할 일을 찾을 수 없습니다.");
	        }
	    } catch (Exception e) {
	        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR) .body("TODO 조회 중 문제 발생: " + e.getMessage());
	    }
	}
	
	
	// /todo/changeComplete?todoNo=4&complete=Y(N이였는데 바뀌어서 들어오는것)
	/**완료 여부 변경
	 * @param todo : 커맨드 객체 (@ModelAttribute 생략된 상태)
	 * - 파라미터의 key와 Todo객체의 필드명이 일치하면 
	 * - 일치하는 필드값이 파라미터의 value값으로 세팅된 상태
	 * - 즉,todoNo와 complete 두 필드가 세팅 완료된 상태
	 * @return
	 */
	@PostMapping("changeComplete")
	public ResponseEntity<Object> changeComplete(@RequestBody Todo todo) {
		
		
		try {	
	        int result = service.changeComplete(todo);

	        if(result > 0) {
	            return ResponseEntity.ok("완료 상태 변경 성공!");
	        }
	        else {
	            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("변경 실패");
	        }
		}catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("TODO 완료 상태 변경 중 문제발생 : " + e.getMessage());
		}
	}
	
	@PutMapping("update")
	public ResponseEntity<Object> todoUpdate(@RequestBody Todo todo){
		
		try {
	        int result = service.todoUpdate(todo);

	        if(result > 0) {
	            return ResponseEntity.ok("할 일 수정 성공!");
	        }
	        else {
	            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("수정 실패");
	        }
		}catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("TODO 수정 중 문제발생 : " + e.getMessage());
		}
	}
	
	@DeleteMapping("delete")
	public ResponseEntity <Object> todoDelete (@RequestParam("todoNo")int todoNo){
		try {
	        int result = service.todoDelete(todoNo);

	        if(result > 0) {
	            return ResponseEntity.ok("할 일 삭제 성공!");
	        }
	        else {
	            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("삭제 실패");
	        }
			}catch (Exception e) {
				return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("TODO 삭제 중 문제발생 : " + e.getMessage());
			}
		
	}
	
	// react는 보이는데 서버랑 연결이 안되는듯
}
