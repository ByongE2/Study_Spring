package com.fastcampus.ch3;

import java.util.Arrays;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.GenericXmlApplicationContext;

class Car{
	String color;
	int oil;
	Engine engine;
	Door[] doors;
	
	
	public Car(String color, int oil, Engine engine, Door[] doors) {
		super();
		this.color = color;
		this.oil = oil;
		this.engine = engine;
		this.doors = doors;
	}
	public String getColor() {
		return color;
	}
	public void setColor(String color) {
		this.color = color;
	}
	public int getOil() {
		return oil;
	}
	public void setOil(int oil) {
		this.oil = oil;
	}
	public Engine getEngine() {
		return engine;
	}
	public void setEngine(Engine engine) {
		this.engine = engine;
	}
	public Door[] getDoors() {
		return doors;
	}
	public void setDoors(Door[] doors) {
		this.doors = doors;
	}
	@Override
	public String toString() {
		return "Car [color=" + color + ", oil=" + oil + ", engine=" + engine + ", doors=" + Arrays.toString(doors)
				+ "]";
	}
		
}//Car
class Engine{}
class Door{}
 
public class SpringDITest {
	public static void main(String[] args) {
		
		ApplicationContext ac = new GenericXmlApplicationContext("config.xml");

		//byName
		Car car = (Car)ac.getBean("car");
//		Car car = ac.getBean("car", Car.class);
		//byType
		Car car2 = (Car)ac.getBean(Car.class);  // 왜 에러나지? 같은객체라고? 영상에선 되는데? engine은 2까지 되는데 Car는 왜 안되냐?;;
											 	// Engine과 Door를 Car의 자손으로 해놨어서 에러 났었다. 상위클래스 Car의 참조변수가 2개라서 그런가바. 뭘따라가야하는지.
		
		Engine engine = (Engine)ac.getBean("engine");
		Door door = (Door)ac.getBean("door");
		
		//세터 호출 , 안하고 xml에 해도됨.
//		car.setColor("red");
//		car.setOil(100);
//		car.setEngine(engine);
//		car.setDoor(new Door[]{ac.getBean("door", Door.class), (Door)ac.getBean("door")});
		System.out.println("car = :" + car); 
		//같은 기능을 하는 객체를 여러개 둘 필요가없다.> 기본이 singleton (주소값이 같다)
		//가끔 매번 새로 생성해야 할 때가 있는데 > scope="prototype" 해주면된다.
	}
}
