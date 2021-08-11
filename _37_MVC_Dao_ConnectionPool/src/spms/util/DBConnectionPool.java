package spms.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;

public class DBConnectionPool {

	String url;
	String username;
	String password;
	// 사용하고 반납받은 Connection 객체를 저장하는 리스트
	ArrayList<Connection> connList = new ArrayList<Connection>();
	
	public DBConnectionPool(String driver, String url,
			String username, String password,
			int poolSize) throws Exception{
		this.url = url;
		this.username = username;
		this.password = password;
		
		Class.forName(driver);
		
		// 미리 poolSize만큼 생성해서 저장해놓는다.
		for(int i=0;i<poolSize;i++) {
			System.out.println((i+1) + "Connection Pool 생성");
			connList.add(DriverManager.getConnection(url, username, password));
		}
	}
	
	public Connection getConnection() throws SQLException {
	
		// 여유분이 있다면
		if(connList.size() > 0) {
			Connection conn = connList.remove(0); // 가장 앞에 있는 객체 꺼냄
			
			/* DBMS는 일정시간 아무 요청이 없으면
			 * timeout에 의해 연결이 종료될 수 있다.
			 * 
			 * 그러므로 아래는 conn객체가 DB와 연결되어있는지 확인하는 것
			 * */
			if(conn.isValid(10))
				return conn;
		}
			
		// 새로운 연결 객체를 생성해서 반환한다
		return DriverManager.getConnection(url, username, password);
	}
	
	public void returnConnection(Connection conn) throws SQLException {
		if(conn != null && conn.isClosed()==false)
			connList.add(conn);
	}
	
	// 종료시 모든  Connection 연결 닫기
	public void closeAll() {
		for(Connection conn : connList) {
			try {
				conn.close();
			}catch(Exception e) {
				e.printStackTrace();
			}
		}
	}
}









