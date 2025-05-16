package edu.kh.project.websocket.interceptor;

import java.util.Map;

import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.http.server.ServletServerHttpRequest;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.WebSocketHandler;
import org.springframework.web.socket.server.HandshakeInterceptor;

import jakarta.servlet.http.HttpSession;

/*SessionHandshakeInterceptor 클래스
 * 
 * WebSocketHandler 가 동작하기 전/ 후에
 * 연결된 클라이언트 세션을 가로채는 동작을 작성할 클래스
 * 
 * */


@Component//Bean 등록
public class SessionHandShakeInterceptor implements HandshakeInterceptor {

	
	//핸들러 동작 전에 수행되는 메서드
	@Override
	public boolean beforeHandshake(ServerHttpRequest request, 
									ServerHttpResponse response,
									WebSocketHandler wsHandler,
									Map<String, Object> attributes) throws Exception {
		
		// ServerHttpRequest : HTTPServletRequest의 부모 인터페이스
		// ServerHttpResponse : HTTPServerHttpResponse의 부모 인터페이스
		
		
		// 1. 다운캐스팅 해야함(그전에 instance of로 확인하기)

		// request가 참조하는 객체가 ServletServerHttpRequest로 다운 캐스팅 가능한가?
		// ServerHttpRequest -> ServletServerHttpRequest->HttpServletRequest
		
		
		if(request instanceof ServletServerHttpRequest) {
			
			//다운캐스팅 수행
			ServletServerHttpRequest servletRequest = (ServletServerHttpRequest)request; 
			
			
			//웹소켓 동작을 요청한 클라이언트의 세션을 얻어옴
			HttpSession session= servletRequest.getServletRequest().getSession();
			
			
			//가로챈 세션을 Handler에 전달 할 수있게 세팅
			attributes.put("session", session);
		}
		//가로 채기 진행 여부 : true 로 작성해야 세션을 가로채서 Handler 에게 전달 가능
		return true;
	}

	//핸들러 동작 후에 수행되는 메서드 
	@Override
	public void afterHandshake(ServerHttpRequest request, ServerHttpResponse response, WebSocketHandler wsHandler,
			Exception exception) {
		// TODO Auto-generated method stub
		
	}
	
	
	// Handshake: 클라이언트 와 서버가 체결하는 행위
	// 클라이언트와 서버가 WebSockey연결을 수립하기 위해, 
	// Http 프로토콜을 통해 수행하는 초기 단계
	// -> 기존 HTTP 연결을 WebSocket 연결로 변경
	
	
	
}
