package edu.kh.project;
// JUnit 테스트 코드는 반드시 src/test/java 경로에 위치해야함!

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;

import edu.kh.project.test.Calculator;

// src/main/java : 실제 애플리케이션 코드
// src/test/java : 테스트 코드 ( JUnit 포함됨)


// 테스트 특징과 규칙
// 1. 기본적으로 JUnit5는 테스트 메서드의 실행 순서를 보장하지 않는다.
// 2. 테스트는 순서에 의존하지 않도록 설계하는것이 권장 된다.
// 3. 테스트는 독립적이어야 하며, 순서에 따라 실패 or 통과하면 안된다.
// -> 테스트가 순서에 의존하면 좋은 테스트가 아님..
// 4. 그래도 순서를 따지고 싶다면 @TestMetodOrder / @Order 어노테이션을 사용할 수 있다.

@TestMethodOrder(OrderAnnotation.class)
public class CalculatorTest {

	//인스턴스화 먼저 되어야
	private static Calculator calculator ;
	
	
	// @BeforeAll , AfterAll 매서드는 무조건 static 메서드에서 사용!!!!!!!!
	@BeforeAll
	public static void initAll() {
		// 모든 테스트 실행 전에 1번만 실행 
		calculator = new Calculator();
		System.out.println("@BeforeAll - 테스트 시작 전 초기화 ");
	}
	
	@AfterAll
	static void end() {
	// 모든 테스트가 끝난 뒤 1번만 실행
		System.out.println("@AfterAll - 모든 테스트가 완료");
	}
	
	@Test // 테스트 하는 메서드임을 표시
	@Order(1)
	void testAdd() {
		// 더하기 하는 메서드를 테스트 해보겠다.
		System.out.println("testAdd 실행!");
		assertEquals(5,calculator.add(2, 3),"2 + 3은 5 이어야 합니다.");
		// assertEquals ( expected, actual) : 두 값이 같은가? 같으면Pass. 아니면 Fail
	}
	
	@Test
	@Order(2)
	void testSubtract() {
		System.out.println("testSubtract 실행");
		assertEquals(6, calculator.subtract(12, 6),"12 -6 은 6이어야 합니다.");
	}
	
	@Test
	@Order(3)
	void testMultiply() {
		System.out.println("testMultiply 실행");
		assertEquals(6, calculator.multiply(2, 3),"2 * 3은 6이어야 합니다.");
	}
	
	@Test
	@Order(4)
	void testDivide() {
		System.out.println("testDivide 실행");
		assertEquals(2, calculator.divide(4, 2),"4 / 2는  2이어야 합니다.");
	}
	
	@Test
	@Order(5)
	void testTrueFalse() {
		int result = calculator.add(2, 2);
		assertTrue(result ==4 , "결과가 4이어야 합니다."); //true라서 pass되어야함
		assertFalse(result ==5 ,"결과가 5면 안됩니다."); // false가 떠야 통과
	}
	@Test
	@Order(6)
	void testDivideByZero() {
		System.out.println("testDivideByZero 실행");
		
		
		//assertThrows( Exception.class , executable ) : 예외 발생 여부 따져주는 test method
		
		Exception exception = assertThrows(IllegalArgumentException.class,
				()->{
					calculator.divide(10, 0);
				});
		
		assertEquals("0으로 나눌수 없음!!",exception.getMessage());
	}
}
