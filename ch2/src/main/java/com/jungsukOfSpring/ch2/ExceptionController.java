package com.jungsukOfSpring.ch2;

import java.io.FileNotFoundException;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;

@Controller
public class ExceptionController {

	@ExceptionHandler(Exception.class)		//예외 중복된것 처리해주는것. cahct 블락의 역할을 한다.
	@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR/*500번에러*/ )		// 상태코드를 500번으로 바꾼다. 즉 200 -> 500으로
	public String catcher(Exception ex, Model m) {	// 해당 정해진 예외없을땐 최고조상인 Exception 이 처리.
//		m.addAttribute("ex", ex);		// view 페이지에서 isErrorPage=true 하면, 안해도되고 직접 예외객체 사용가능.
		return "error";		 // 응답코드가 200번이 나온다. 즉 요청성공으로 나와버린다. so @ResponseStatus 사용해야한다.
	}
	@ExceptionHandler({NullPointerException.class, FileNotFoundException.class}) // 2개 이상으로 하고싶으면 배열형태로.			
	public String catcher2(Exception ex, Model m) {
		System.out.println("m=" + m);   // 캐처의 m과 main의 m이 다른객체임을 확인하기 위한 출력, 조심혀라~
										// 캐처의 Model은 View의 error.jsp에게 보내기 위한것. 
		m.addAttribute("ex", ex);
		return "error";		
	}
	
	@RequestMapping("/ex")
	public String main(Model m) throws Exception{
//		 try {									//@ExceptionHandler 정의해줘서 안해줘도되고.
			// 지금 컨트롤러의 Model m 은 예외핸들러에게 예외를 전달하기 위한 m.
			m.addAttribute("msg", "massage from ExceptionController.main()");
			throw new Exception("예외가 발생했습니다.");
//		} catch (Exception e) {
//			return "error";
//		}
	}// main
	@RequestMapping("/ex2")
	public String main2() throws Exception{
//		try {
		throw new FileNotFoundException("예외가 발생했습니다.nullPointer");
//		} catch (Exception e) {
//			return "error";
//		}
	}// main2
}
