package com.fastcampus.ch3.diCopy4;

import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component; // 직접 추가해줬다.
import org.springframework.util.StringUtils;

import com.google.common.reflect.ClassPath;

@Component class Car{
//	@Autowired
	
	@Resource  //톰캣 라이브러리 추가. J2EE의 애너테이션 필요해서
	Engine engine;
//	@Autowired Door door;
	@Resource Door door;
	
	
	@Override
	public String toString() {
		return "Car [engine=" + engine + ", door=" + door + "]";
	}
}
@Component class SportsCar extends Car{}
@Component class Truck extends Car{}

///*@Component*/ class Engine {}
@Component class Engine {}
@Component class Door{}
class AppContext{
	Map map; // 객체 저장소
	
	AppContext(){
		map = new HashMap();
		doComponentScan();
		doAutowired(); 
		doResource();
	}//AppContext 생성자
	
	private void doResource() {
		//map에 저장된 객체의 iv중에 @Resource가 붙어있으면
		//map에서 iv의 "이름"에 맞는 객체를 찾아서 연결(객체의 주소를 iv 저장)
		try {
			for(Object bean : map.values()) {
				for(Field fld : bean.getClass().getDeclaredFields()) {
						if(fld.getAnnotation(Resource.class) != null) //byName
							fld.set(bean, getBean(fld.getName())); // car.engine = obj;
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private void doAutowired() {
		//map에 저장된 객체의 iv중에 @Autowired가 붙어있으면
		//map에서 iv의 "타입"에 맞는 객체를 찾아서 연결(객체의 주소를 iv 저장)
		try {
			for(Object bean : map.values()) {
				for(Field fld : bean.getClass().getDeclaredFields()) {
						if(fld.getAnnotation(Autowired.class) != null) //byType
							fld.set(bean, getBean(fld.getType())); // car.engine = obj;
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}//doAutowired

	//guava library 가져온 다음에.
	private void doComponentScan() {
		try {
			//1. 패키지내의 클래스 목록을 가져온다.
			//2. 반복문으로 클래스를 하나씩 읽어와서 @Component이 붙어 있는지 확인
			//3. @Component가 붙어있으면 객체를 생성해서 map에 저장
			ClassLoader classLoader = AppContext.class.getClassLoader();
			ClassPath classPath = ClassPath.from(classLoader);
			
			Set<ClassPath.ClassInfo> set =  classPath.getTopLevelClasses("com.fastcampus.ch3.diCopy4");
			
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

//객체를 자동 연결하기. @Autowired, @Resource
public class Main4 {

	public static void main (String[] args) throws Exception {
		AppContext ac = new AppContext();
		Car car = (Car)ac.getBean("car");
		Engine engine = (Engine)ac.getBean("engine");
		Door door = (Door)ac.getBean(Door.class); //byType으로 객체를 검색

		// 수동으로 객체를 연결
//		car.engine = engine;
//		car.door = door;
		
		
		System.out.println("car = " + car);
		System.out.println("engine = " + engine);
		System.out.println("door = " + door);
	}//main
	
}//Main1
