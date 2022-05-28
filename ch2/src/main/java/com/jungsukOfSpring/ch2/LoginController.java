package com.jungsukOfSpring.ch2;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/login")
public class LoginController {

	@GetMapping("/login")
	public String loginForm() {
		return "loginForm";
	}//loginForm
	
	@GetMapping("/logout")
	public String logOut(HttpSession session) { // 세션을 직접 매개변수로 직접가져옴. HttpRequest에서 가져오는거 말고
		// 1. 세션을 종료
		session.invalidate()  ;
		// 2. 홈으로 이동
		return "redirect:/";
	
	}
	
	@PostMapping("/login")
	public String login(String id, String pwd, boolean rememberId, HttpServletRequest request, HttpServletResponse response) throws Exception {
//		System.out.println("id : "+id);
//		System.out.println("pwd : "+pwd);
//		System.out.println("rememberId : "+rememberId); // String 일땐 값이 On으로 찍힌다.(체크박스 체크된거)
														// 체크박스에 value="true"로 주면 true오고..기본값이 on임.
		// 1.유효성검사 - id와 pwd를 확인
		if(!loginCheck(id,pwd)) {
			// 2-1 일치하지 않으면, loginForm으로 이동
			String msg = URLEncoder.encode("ID 또는 password가 일치하지 않습니다.", "utf-8");
			return "redirect:/login/login?msg="+msg; // redirect는 get방식으로 감. so @GetMapping으로 간다.
		}
		
		// 2-2 id와 pwd가 일치하면, 
		// 세션 객체 얻어오기  > request 매개변수 필요하고.
		HttpSession session = request.getSession();
		// 세션 객체에 id를 저장
		session.setAttribute("id", id);
		
		
		if(rememberId) {
			//쿠키 생성
			//		1.쿠키 생성
			Cookie cookie = new Cookie("id", id);
			//		2.응답(response)에 저장.
			response.addCookie(cookie);		//매개변수 response 만들어주고.
		}else {
			//쿠키 삭제
			Cookie cookie = new Cookie("id", id);
			cookie.setMaxAge(0); 		// 쿠키 유효기간.
			response.addCookie(cookie);		//매개변수 response 만들어주고.
		}
		//		3.홈으로 이동
		return "redirect:/";  // 홈으로 이동. > Spring 기본 home 고쳐줘야한다.
	}//login 
	// 매개변수 검사 해줘야하는데 여기선 간단히 pass
	private boolean loginCheck(String id, String pwd) {
		return "asdf".equals(id) && "1234".equals(pwd); // id.equals("asdf") 하면 id의 null 체크 해줘야한다. so 바꿔서 함. 
	}//loginCheck

}//LoginController

