package com.jungsukOfSpring.ch2;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Method;
import java.util.Iterator;
import java.util.Map;
import java.util.Scanner;

import org.springframework.ui.Model;
import org.springframework.validation.support.BindingAwareModelMap;

public class MethodCall2 {
	public static void main(String[] args) throws Exception{

		//1.YoilTellerMVC의 객체를 생성
		Class clazz = Class.forName("com.jungsukOfSpring.ch2.YoilTellerMVC");
		Object obj = clazz.newInstance();
		//2."main"메서드의 정보를 가져온다.(int 3개 Mode.class 일치)
		Method main = clazz.getDeclaredMethod("main", int.class, int.class, int.class, Model.class);
		//3. Model 생성
		Model model = new BindingAwareModelMap();  // model 인터페이스임. BindingAwareModelMap은 모델의 구현체.
		System.out.println("[before] model="+model);
		//4. main메서드를 호출.
		// String viewName = obj.main(2021, 10, 1, model); //아래줄과 동일 , reflection api 안쓰면 이걸로.
		String viewName = (String)main.invoke(obj, new Object[] { 2021, 10, 1, model }); // reflection api로 할 때.
		// Refliction API로 호출해서 더 유연해지긴함. 매개변수를 배열로 받음. 바뀔 수 있으니.
		System.out.println("viewName="+viewName);	
		
		// Model의 내용을 출력 
		System.out.println("[after] model="+model);
				
		// 텍스트 파일을 이용한 rendering
		render(model, viewName);			
	} // main
	
	static void render(Model model, String viewName) throws IOException {
		String result = "";
		
		// 1. 뷰의 내용을 한줄씩 읽어서 하나의 문자열로 만든다.
		Scanner sc = new Scanner(new File("src/main/webapp/WEB-INF/views/"+viewName+".jsp"), "utf-8");
		
		while(sc.hasNextLine())
			result += sc.nextLine()+ System.lineSeparator();
		
		// 2. model을 map으로 변환 
		Map map = model.asMap();
		
		// 3.key를 하나씩 읽어서 template의 ${key}를 value바꾼다.
		Iterator it = map.keySet().iterator();
		
		while(it.hasNext()) {
			String key = (String)it.next();

			// 4. replace()로 key를 value 치환한다.
			result = result.replace("${"+key+"}", ""+map.get(key));
		}
		
		// 5.렌더링 결과를 출력한다.
		System.out.println(result);
	}
}