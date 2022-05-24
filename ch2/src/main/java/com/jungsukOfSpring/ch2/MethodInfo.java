package com.jungsukOfSpring.ch2;

import java.lang.reflect.Method;
import java.lang.reflect.Parameter;
import java.util.StringJoiner;

public class MethodInfo {
	public static void main(String[] args) throws Exception{
		
		//YoilTeller의 객체를 생성.
		Class clazz = Class.forName("com.jungsukOfSpring.ch2.YoilTeller");
		Object obj = clazz.newInstance();
		
		Method[] methodArr = clazz.getDeclaredMethods();
		
		//YoilTeller의 모든 메서드를 가져와서 배열에 저장.
		for(Method m : methodArr) {
			String name = m.getName(); // 메서드이름
			Parameter[] paramArr = m.getParameters(); // 매개변수 목록 
//			Class[] paramTypeArr = m.getParameterTypes();
			Class returnType = m.getReturnType(); // 메서드 반환타입
			
			StringJoiner paramList = new StringJoiner(", ", "(", ")");
			
			for(Parameter param : paramArr) {
				String paramName = param.getName();
				Class  paramType = param.getType();
				
				paramList.add(paramType.getName() + " " + paramName);
			}
			
			System.out.printf("%s %s%s%n", returnType.getName(), name, paramList);
		}
	} // main
}