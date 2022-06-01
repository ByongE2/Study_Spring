package com.jungsukOfSpring.ch2;

import java.io.FileNotFoundException;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

//@ControllerAdvice // 모든 패키지에 적용
@ControllerAdvice("com.jungsukOfSpring.ch3") // com.jungsukOfSpring.ch2 패키지에만 적용.
public class GlobalCatcher {
	@ExceptionHandler(Exception.class)				//예외 중복된것 처리해주는것. cahct 블락의 역할을 한다.
	public String catcher(Exception ex, Model m) {	// 해당 정해진 예외없을땐 최고조상인 Exception 이 처리.
		m.addAttribute("ex", ex);
		return "error";		
	}
	@ExceptionHandler({NullPointerException.class, FileNotFoundException.class}) // 2개 이상으로 하고싶으면 배열형태로.			
	public String catcher2(Exception ex, Model m) {
		m.addAttribute("ex", ex);
		return "error";		
	}
}
