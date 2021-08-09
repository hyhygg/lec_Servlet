package spms.servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.*;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@SuppressWarnings("serial")
public class MemberUpdateServlet extends HttpServlet {
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String sNo = req.getParameter("no");
//		System.out.println("no=" + sNo);

		/*this.getInitParameter() 메서드를 이용하면
		 * web.xml에 설정한 Servlet Init-Param 문자열을 얻을 수 있다*/
		String driver = this.getInitParameter("driver");
		String url = this.getInitParameter("url");
		String id = this.getInitParameter("username");
		String pwd = this.getInitParameter("password");
		
//		System.out.println("driver=" + driver);
//		System.out.println("url=" + url);
//		System.out.println("username=" + id);
//		System.out.println("password=" + pwd);
		
		// 1. 관련 변수를 선언
		Connection conn = null;
		Statement stmt = null;
		ResultSet rs = null;
		String sqlSelect = 
				"SELECT mno,email,mname,cre_date" + "\r\n" +
				"FROM members WHERE mno=" + sNo;
		
		try {
			// mySql driver 객체 로딩
			Class.forName(driver);
			conn = DriverManager.getConnection(url, id, pwd);
			stmt = conn.createStatement();
			rs = stmt.executeQuery(sqlSelect);
			/*
			rs내부에는 데이터의 행을 가리키는 cursor가 있어서
			처음에는 1번째 행 이전을 가리키고 있다.
			rs.next()를 호출하면 커서는 다음 행으로 이동한다.
			*/
			resp.setContentType("text/html;charset=UTF-8");
			PrintWriter out = resp.getWriter();
			if(rs.next()) {
				out.println("<html><head><title>회원 정보</title></head>");
				out.println("<body><h1>회원 정보</h1>");
				out.println("<form action='update' method='post'>");
				out.println("번호 : <input type='text' name='no' value='"
						+ sNo + "' readonly><br/>");
				out.println("이름 : <input type='text' name='name'" 
						+ " value='" + rs.getString("mname") + "'><br/>");
				out.println("이메일 : <input type='text' name='email'" 
						+ " value='" + rs.getString("email") + "'><br/>");
				out.println("가입일 : " + rs.getDate("cre_date") + "<br/>");
				out.println("<input type='submit' value='저장'>");
				out.println("<input type='button' value='취소'"
						+ " onclick='location.href=\"list\"'>");
				out.println("</form>");
				out.println("</body></html>");
			}else {
				out.println(sNo + "번으로 조회된 데이터가 없습니다");
			}
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





