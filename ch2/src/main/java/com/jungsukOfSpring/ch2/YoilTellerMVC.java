package com.jungsukOfSpring.ch2;

import java.io.IOException;
import java.util.Calendar;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
@Controller
public class YoilTellerMVC { // http://localhost:8080/ch2/getYoilMVC?year=2021&month=10&day=1
	@RequestMapping("/getYoilMVC")
//	public void main(HttpServletRequest request, HttpServletResponse response) throws IOException {
		public String main(int year, int month, int day, Model model) throws IOException {
		// 반환타입 void로 하면 맵핑된 주소"getYoilMVC"로 된다. 물론 getYoilMVC.jsp는 만들어줘야한다.
		// 반환타입을 ModelAndView로 하면, 매개변수 model 없애고, ModelAndView 객체 생성하고. ModelAndView의 참조변수를 반환하면된다.
		//   >> ModelAndView mv 에 생성 했다면, mv.addObject(~,~); mv.setViewName("yoil"); 해주고. return mv 해주면된다.
		
		//1. 유효성 검사
		if(!isValid(year, month, day))
			return "yoilError";
			

		//2. 요일 계산
		char yoil = getYoil(year, month, day);
		
		//3. 계산한 결과를 model에 저장
		model.addAttribute("year", year);
		model.addAttribute("month", month);
		model.addAttribute("day", day);
		model.addAttribute("yoil", yoil);
		
		return "yoil"; // WEB-INF/views/yoil.jsp 랑 같다. 앞 디렉토리는 매번 같아서 yoil만
		
//		//3. 출력
//		response.setContentType("text/html"); // 응답의 형식을 html로 지정
//		response.setCharacterEncoding("utf-8");// 응답의 인코딩을 utf-8로 지정
//		PrintWriter out = response.getWriter();// 브라우저로의 출력 스트림(out)을 얻는다.
//		out.println("<html>");
//		out.println("<head>");
//		out.println("</head>");
//		out.println("<body>");
//		out.println(year + "년" + month + "월" + day + "일은");
//		out.println(yoil + "요일입니다.");
//		out.println("</body>");
//		out.println("</html>");
//		out.close();
		
	}

private char getYoil(int year, int month, int day) {
	Calendar cal = Calendar.getInstance();
	cal.set(year,month-1, day);
	
	int dayOfWeek = cal.get(Calendar.DAY_OF_WEEK);
	return  " 일월화수목금토".charAt(dayOfWeek);
}

private boolean isValid(int year, int month, int day) {
	return true;
}

}
