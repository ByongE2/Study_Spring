package com.jungsukOfSpring.ch2;

import java.io.FileNotFoundException;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;

//@ResponseStatus(HttpStatus.BAD_REQUEST)// 500 --> 400 status code 변경. 
class MyException extends RuntimeException{
	MyException(String msg){
		super(msg);
	}
	MyException(){ this(""); }
}


@Controller
public class ExceptionController2 {
// 클래스내에서만 적용된다. 즉 중복된다. 각클래스마다 핸들러 써줘야함. 중복제거는 ? > Globalcatcher 클래스에서, @ControllerAdvice로 하면된다.
	
	@RequestMapping("/ex3")
	public String main() throws Exception{
			throw new MyException("예외가 발생했습니다.");
	}// main
	@RequestMapping("/ex4")
	public String main2() throws Exception{
		throw new FileNotFoundException("예외가 발생했습니다.nullPointer");
	}// main2
}
