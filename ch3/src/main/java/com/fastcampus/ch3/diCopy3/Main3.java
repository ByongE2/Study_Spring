package com.fastcampus.ch3.diCopy3;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import org.springframework.stereotype.Component; // 직접 추가해줬다.
import org.springframework.util.StringUtils;

import com.google.common.reflect.ClassPath;

@Component class Car{}
@Component class SportsCar extends Car{}
@Component class Truck extends Car{}

///*@Component*/ class Engine {}
@Component class Engine {}

class AppContext{
	Map map; // 객체 저장소
	
	AppContext(){
		map = new HashMap();
		doComponentScan();
		
	}//AppContext 생성자
	
	//guava library 가져온 다음에.
	private void doComponentScan() {
		try {
			//1. 패키지내의 클래스 목록을 가져온다.
			//2. 반복문으로 클래스를 하나씩 읽어와서 @Component이 붙어 있는지 확인
			//3. @Component가 붙어있으면 객체를 생성해서 map에 저장
			ClassLoader classLoader = AppContext.class.getClassLoader();
			ClassPath classPath = ClassPath.from(classLoader);
			
			Set<ClassPath.ClassInfo> set =  classPath.getTopLevelClasses("com.fastcampus.ch3.diCopy3");
			
			for(ClassPath.ClassInfo classInfo : set) {
				Class clazz = classInfo.load(); 
				Component component = (Component) clazz.getAnnotation(Component.class);
				if(component != null) {
					//첫 글자를 대문자로 바꿔서 map에 저장
					String id = StringUtils.uncapitalize(classInfo.getSimpleName());
					map.put(id, clazz.newInstance());
				}//if
			}//for
		} catch (Exception e) {
			e.printStackTrace();
		}
	}//doComponentScan

	Object getBean(String key) {
		return map.get(key);
	}//getBean(String key)
	
	Object getBean(Class clazz) { //byType . 이름이 아닌, 객체의 타입으로 찾기.
		for(Object obj : map.values()) {
			if(clazz.isInstance(obj))
				return obj;
		}
		return null;
	}//getBean(Class clazz)

}//AppContext

//자동 객체 등록 하기 - Component Scanning
public class Main3 {

	public static void main (String[] args) throws Exception {
		AppContext ac = new AppContext();
		Car car = (Car)ac.getBean("car");
		Car car2 = (Car)ac.getBean(Car.class); //byType으로 객체를 검색
		Engine engine = (Engine)ac.getBean("engine");
		System.out.println("car = " + car);
		System.out.println("car2 = " + car2);
		System.out.println("engine = " + engine);
	}//main
	
}//Main1
