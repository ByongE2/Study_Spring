package com.jungsukOfSpring.ch2;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class TwoDice {

	@RequestMapping("/rollDice")
	public void main(HttpServletResponse response) throws IOException {
		//입력은 없어도 되니까~ 작업에서 랜덤으로 만들어줘서.. 동적임.
		
		//작업
		int idx1 = (int)(Math.random()*6)+1;
		int idx2 = (int)(Math.random()*6)+1;
		
		
		//출력.
		response.setContentType("text/html");
		response.setCharacterEncoding("utf-8");
		PrintWriter out = response.getWriter();
		out.println("<html>");
		out.println("<head>");
		out.println("<body>");
		out.println("이미지삽입" + idx1 + ".jpg");
		out.println("이미지삽입" + idx2 + ".jpg");
		out.println("</body>");
		out.println("</head>");
		out.println("</html>");
	}
		

}
