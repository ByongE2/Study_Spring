//package com.fastcampus.ch3;
//
//import java.util.Arrays;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.beans.factory.annotation.Value;
//import org.springframework.context.ApplicationContext;
//import org.springframework.context.support.GenericXmlApplicationContext;
//import org.springframework.stereotype.Component;
//
//
//@Component class Engine{}
//// @Component("engine") class Engine{} //<bean id="engine" class="com.fastcampus.ch3.Engine"/>
//@Component class SuperEngine extends Engine{}
//@Component class TurboEngine extends Engine{}
//@Component class Door{}
//@Component
//class Car{
//	@Value("red") String color;
//	@Value("100") int oil; // String 타입으로 입력해도 oil의 타입이 int라서 자동형변환 해줌.
//	@Autowired Engine engine;
//	@Autowired Door[] doors; //@Autowired는 타입에 맞는것 1개만 넣어주는거라 Door값은 1개만 들어갔다.
//	//@Autowired는 byType인데 = 타입으로 먼저 검색하고, 여러개면 이름으로 검색한다. > 상속받은애들 있어도 첫글자 소문자인것으로 찾는다.
//																	// 정확히 일치하는게 없고, 후보 여러개면 에러난다.
//																	// @Autowired @Qualifier("superEngine")쓰면 된다.
//																	// 아니면 Resource("name=superEngine") 해서 byName로 하던가.
//	public Car() {}
//	public Car(String color, int oil, Engine engine, Door[] doors) {
//		super();    
//		this.color = color;
//		this.oil = oil;
//		this.engine = engine;
//		this.doors = doors;
//	}
//	public String getColor() {
//		return color;
//	}
//	public void setColor(String color) {
//		this.color = color;
//	}
//	public int getOil() {
//		return oil;
//	}
//	public void setOil(int oil) {
//		this.oil = oil;
//	}
//	public Engine getEngine() {
//		return engine;
//	}
//	public void setEngine(Engine engine) {
//		this.engine = engine;
//	}
//	public Door[] getDoors() {
//		return doors;
//	}
//	public void setDoors(Door[] doors) {
//		this.doors = doors;
//	}
//	@Override
//	public String toString() {
//		return "Car [color=" + color + ", oil=" + oil + ", engine=" + engine + ", doors=" + Arrays.toString(doors)
//				+ "]";
//	}
//		
//}//Car
// 
//public class SpringDITest {
//	public static void main(String[] args) {
//		
//		ApplicationContext ac = new GenericXmlApplicationContext("config.xml");
//
//		//byName
//		Car car = (Car)ac.getBean("car", Car.class);
////		Car car = ac.getBean("car", Car.class);
//		//byType
////		Car car2 = (Car)ac.getBean(Car.class);  // 왜 에러나지? 같은객체라고? 영상에선 되는데? engine은 2까지 되는데 Car는 왜 안되냐?;;
//											 	// Engine과 Door를 Car의 자손으로 해놨어서 에러 났었다. 상위클래스 Car의 참조변수가 2개라서 그런가바. 뭘따라가야하는지.
//		
//		Engine engine = (Engine)ac.getBean("engine"); //byName
////		Engine engine = (Engine)ac.getBean(Engine.class); //byType -> 상속받은 자손클래스 있으면 예외발생한다. 즉 같은 타입 여러개이면 byType로 찾으면 애러난다.
////		Door door = (Door)ac.getBean("door");
//		
//		//세터 호출 , 안하고 xml에 해도됨. > @Autowired로 @Vlaue("")로 대체가능
////		car.setColor("red");
////		car.setOil(100);
////		car.setEngine(engine);
////		car.setDoors(new Door[]{ac.getBean("door", Door.class), (Door)ac.getBean("door")});
//		System.out.println("car = :" + car); 
//		//같은 기능을 하는 객체를 여러개 둘 필요가없다.> 기본이 singleton (주소값이 같다)
//		//가끔 매번 새로 생성해야 할 때가 있는데 > scope="prototype" 해주면된다.
//	}
//}
