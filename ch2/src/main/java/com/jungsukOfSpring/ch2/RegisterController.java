package com.jungsukOfSpring.ch2;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

import javax.annotation.PostConstruct;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class RegisterController {

	//	@RequestMapping(value="/register/save", method=RequestMethod.POST, RequestMethod.GET ) // 기본값. GET,POST둘다 허용.
	//	@RequestMapping("/register/add") // 신규회원 가입 화면
	@GetMapping("/register/add") 							//view-controller로 생략하게끔 했다. 
	public String register() {
		return "registerForm"; // = WEB-INF/views/registerForm.jsp
	}

	
	//	@RequestMapping(value="/register/save", method=RequestMethod.POST) // get방식은 안되게 한다.
	@PostMapping("/register/save") // STS 4.3 부터 사용가능.
	public String save(User user, Model m) throws Exception {
		//1.유효성 검사\
		if(!isValid(user)) {
			String msg = URLEncoder.encode("id를 잘못입력하셨습니다.", "utf-8"); // -> view쪽에서 디코딩 해줘야함. 될때있고 안될때 있어서.
			
			m.addAttribute("msg", msg);
			return "redirect:/register/add"; // save껀데 자동으로 add도되게끔.

//			return "redirect:/register/add?msg="+msg; // URL재작성(rewriting)  -> 인코딩 필요해서 위에 URL인코딩 해줌.
		}
		//2.DB에 신규회원 정보를 저장
		return "registerInfo";
	}

	private boolean isValid(User user) {
		return true;
	}

}
