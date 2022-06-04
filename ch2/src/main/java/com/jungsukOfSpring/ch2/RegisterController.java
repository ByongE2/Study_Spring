package com.jungsukOfSpring.ch2;

import java.net.URLEncoder;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.beans.propertyeditors.StringArrayPropertyEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Validator;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/register")
public class RegisterController {

	//데이터 변환. Date, String > String[]
	@InitBinder
	public void toDate(WebDataBinder binder) {
//		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
//		binder.registerCustomEditor(Date.class, new CustomDateEditor(df, false));
		binder.registerCustomEditor(String[].class, new StringArrayPropertyEditor("#", false));
																					//빈값 허용 할거냐 안할거냐.
		//	binder.registerCustomEditor(String[].class, "hobby", new StringArrayPropertyEditor("#", false)); // 필드적용.
//		binder.setValidator(new UserValidator()); // UserValidator를 WebDateBinder의 (로컬임)validator로 등록
		binder.addValidators(new UserValidator()); // 글로벌
		//확인 용.
		List<Validator> validatorList = binder.getValidators();
		System.out.println("validatorList" + validatorList);
	}															
	
	@GetMapping("/add") 	 						//view-controller로 생략하게끔 했다. 
	public String register() {
		return "registerForm"; // = WEB-INF/views/registerForm.jsp
	}
	@PostMapping("/add") 
	public String save(@Valid User user, BindingResult result, Model m) throws Exception {
		System.out.println("result="+result);
		System.out.println("user="+user);
		
		//수동 검증 - Validator를 직접 생성하고, validate()를 직접 호출.
		UserValidator userValidator = new UserValidator();
		userValidator.validate(user, result);
									//BindingResult가 Errors의 자손이라서 result 써도 된다.
		
		// User객체를 검증한 결과 에러가 있으면, registerForm을 이용해서 에러를 보여줘야한다.
		//1.유효성 검사\
//		if(!isValid(user)) {
//			String msg = URLEncoder.encode("id를 잘못입력하셨습니다.", "utf-8"); // -> view쪽에서 디코딩 해줘야함. 될때있고 안될때 있어서.
//			
//			m.addAttribute("msg", msg);
//			return "redirect:/register/add"; // save껀데 자동으로 add도되게끔.

//			return "redirect:/register/add?msg="+msg; // URL재작성(rewriting)  -> 인코딩 필요해서 위에 URL인코딩 해줌.
//		}
		//2.DB에 신규회원 정보를 저장
		return "registerInfo";
	}

	private boolean isValid(User user) {
		return true;
	}

}
