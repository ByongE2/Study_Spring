package com.jungsukOfSpring.ch2;

import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;


	public class UserValidator implements Validator {
		@Override
		public boolean supports(Class<?> clazz) {
//			return User.class.equals(clazz); // 검증하려는 객체가 User타입인지 확인
			return User.class.isAssignableFrom(clazz); // clazz가 User 또는 그 자손인지 확인
		}

		@Override
		public void validate(Object target, Errors errors) { 
			System.out.println("UserValidator.validate() is called");

//			if(!(target instanceof User)) return; // 위 supports에서 검증을 했기 때문에, 생략. 안해도 된다.
			User user = (User)target;
			
			String id = user.getId();
			
	//		if(id==null || "".equals(id.trim())) {
	//			errors.rejectValue("id", "required");
	//		}
			ValidationUtils.rejectIfEmptyOrWhitespace(errors, "id",  "required");
			ValidationUtils.rejectIfEmptyOrWhitespace(errors, "pwd", "required");
			//더 알아보고 추가 하든가~
			
			if(id==null || id.length() <  5 || id.length() > 12) {
				errors.rejectValue("id", "invalidLength");
			}
		}
	}