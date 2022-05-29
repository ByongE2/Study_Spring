package com.jungsukOfSpring.ch2;

import java.io.FileNotFoundException;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class ExceptionController2 {
// 클래스내에서만 적용된다. 즉 중복된다. 각클래스마다 핸들러 써줘야함. 중복제거는 ? > Globalcatcher 클래스에서, @ControllerAdvice로 하면된다.
	
	
//	@ExceptionHandler(Exception.class)				//예외 중복된것 처리해주는것. cahct 블락의 역할을 한다.
//	public String catcher(Exception ex, Model m) {	// 해당 정해진 예외없을땐 최고조상인 Exception 이 처리.
//		m.addAttribute("ex", ex);
//		return "error";		
//	}
//	@ExceptionHandler({NullPointerException.class, FileNotFoundException.class}) // 2개 이상으로 하고싶으면 배열형태로.			
//	public String catcher2(Exception ex, Model m) {
//		m.addAttribute("ex", ex);
//		return "error";		
//	}
	
	@RequestMapping("/ex3")
	public String main() throws Exception{
//		 try {									//@ExceptionHandler 정의해줘서 안해줘도되고.
			throw new Exception("예외가 발생했습니다.");
//		} catch (Exception e) {
//			return "error";
//		}
	}// main
	@RequestMapping("/ex4")
	public String main2() throws Exception{
//		try {
		throw new FileNotFoundException("예외가 발생했습니다.nullPointer");
//		} catch (Exception e) {
//			return "error";
//		}
	}// main2
}
