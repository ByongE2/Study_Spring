package com.projectA.shopingmall;

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

@Controller //호출가능한 클래스.객체로.프로그램으로 등록한다.
@RequestMapping("/login2")
public class LoginController {
	// Get방식 로그인 페이지
	@GetMapping("/login")
	public String loginForm() {
		return "loginForm";
	}
	// Post방식 로그인 페이지
	@PostMapping("/login")
	public String login(String id, String pwd, String toURL, /*boolean rememberId,*/ 
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		// 1. id pwd 확인
		// 2. id pwd 일치하지 않으면, loginForm으로 이동
		// 3. id pwd 일치하면, ~로 이동

		// 일치하지 않을 때,
		if(!loginCheck(id,pwd)) {
			String msg = URLEncoder.encode("ID 또는 password가 일치하지 않습니다.", "utf-8");
			return "redirect:/login2/login?msg="+msg; //redirect는 get방식으로 되는것을 이용. 
		}
		//일치 할 때,
		Cookie cookie = new Cookie("id", id);
		response.addCookie(cookie);//매개변수 response 만들어주고.

		//로그인 후 가야할 곳.(default: 홈(/))
		toURL = toURL==null || toURL.equals("") ? "/" : toURL; // toURL 값이 제대로안오면 홈으로 가게끔.
		return "redirect:" + toURL;  // 홈으로 이동. > Spring 기본 home 고쳐줘야한다.
	}//end of login 

	//db없어서 간단히
	private boolean loginCheck(String id, String pwd) {
		return "asdf".equals(id) && "1234".equals(pwd); // id.equals("asdf") 하면 id의 null 체크 해줘야한다. so 바꿔서 함. 
	}//end of loginCheck
}//end of LoginController





//로그아웃
//	@GetMapping("/logout")
//	public String logOut(HttpSession session) {
//		// 세션 종료
//		session.invalidate()  ;
//		// 홈으로 이동
//		return "redirect:/";
//	}

//세션
//		HttpSession session = request.getSession();
//		session.setAttribute("id", id);

//아이디 기억 체크박스 기능 
//		if(rememberId) {
//			//쿠키
//			Cookie cookie = new Cookie("id", id);
//			response.addCookie(cookie);		//매개변수 response 만들어주고.
//		}else {
//			//쿠키 삭제
//			Cookie cookie = new Cookie("id", id);
//			cookie.setMaxAge(0); 		// 쿠키 유효기간.
//			response.addCookie(cookie);		//매개변수 response 만들어주고.
//		}