package com.fastcampus.ch3;

import org.springframework.test.context.ContextConfiguration;
//??임포트 안떠서 수동으로 입력해줌.
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import static org.junit.Assert.assertTrue;

import java.sql.Connection;

import javax.sql.DataSource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.GenericXmlApplicationContext;


//Workbench로 db연결 성공했는지 테스트 방법임. > 인강에서 intelliJ로 테스트하는법도 보여줌.

//@2개해서 ac (ApplicationContext) 을 생성해서 재사용하기 때문에, 성능적으로도 이점이 있다.
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"file:src/main/webapp/WEB-INF/spring/**/root-context.xml"}) //XML 설정파일 위치지정
public class DBConnectionTest2Test {
	
	@Autowired
	DataSource ds;  //컨테이너로부터 자동으로 주입 받는다.
	
	@Test
	public void springJdbcConnectionTest() throws Exception{
		
//	   ApplicationContext ac = new GenericXmlApplicationContext("file:src/main/webapp/WEB-INF/spring/**/root-context.xml");
//       DataSource ds = ac.getBean(DataSource.class);

       Connection conn = ds.getConnection(); // 데이터베이스의 연결을 얻는다.

       System.out.println("conn = " + conn);
       assertTrue(conn!=null); //assert 여러 종류 중 assertTrue는 조건식이 참이면 통과.
	}//main
}
