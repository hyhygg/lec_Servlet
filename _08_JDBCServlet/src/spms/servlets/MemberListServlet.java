package spms.servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.*;

import javax.servlet.GenericServlet;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebServlet;

@SuppressWarnings("serial")
@WebServlet("/member/list")
public class MemberListServlet extends GenericServlet {

	@Override
	public void service(ServletRequest req, ServletResponse res) throws ServletException, IOException {
		
		Connection conn = null;		// MySQL 서버 연결
		Statement stmt = null;		// sql 문 실행
		ResultSet rs = null;		// SELECT문 실행 결과
		
		//mysql접속 정보
//		String mySqlUrl = "jdbc:mysql://localhost:3307/studydb?serverTimezone=UTC";
		String mySqlUrl = "jdbc:mysql://localhost/studydb?serverTimezone=UTC";
		String id = "root";
		String pwd = "1111";
		String sqlSelect = "SELECT mno,mname,email,cre_date " + "\r\n" +
						   "FROM members " + "\r\n" +
						   "ORDER BY mno";
		
		try {
			// 1) MySQL 드라이버 객체를 로딩
			DriverManager.registerDriver(new com.mysql.cj.jdbc.Driver());
			// 2) MySQL 서버와 연결
			conn = DriverManager.getConnection(mySqlUrl, id, pwd);
			// 3) sql문 실행하기 위한 객체 생성
			stmt = conn.createStatement();
			// 4) sql문 실행(서버 전송 후 결과값 받아오기)
			rs = stmt.executeQuery(sqlSelect);
			// 5) 결과를 브라우저에 전송
			res.setContentType("text/html;charset=UTF-8");
			PrintWriter out = res.getWriter();
			out.println("<html><head><title>회원목록</title></head>");
			out.println("<body><h1>회원 목록</h1>");
			/* rs.next()의 역할
			 * 1) 결과값이 true일 때 다음 데이터가 존재한다
			 * 2) 내부적으로 최초에 cursor는 첫번째 데이터 이전을 가리키고 있다.
			 *    rs.next()를 호출할 때 마다 다음 행으로 커서를 옮긴다
			 * */
			while(rs.next()) {
				/*
				out.println(rs.getInt("mno") + ", " +
						rs.getString("mname") + ", " +
						rs.getString("email") + ", " + 
						rs.getDate("cre_date") + "<br/>");
				*/
				out.println(rs.getInt(1) + ", " +
							rs.getString(2) + ", " +
							rs.getString(3) + ", " + 
							rs.getDate(4) + "<br/>");
			}
			out.println("</body></html>");
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			try {
				if(rs!=null) 
					rs.close();
			}catch(Exception e) {
				e.printStackTrace();
			}
			try {
				if(stmt!=null) 
					stmt.close();
			}catch(Exception e) {
				e.printStackTrace();
			}
			try {
				if(conn!=null) 
					conn.close();
			}catch(Exception e) {
				e.printStackTrace();
			}
		}		
	}

}






