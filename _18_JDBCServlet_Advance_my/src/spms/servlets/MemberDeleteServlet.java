package spms.servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.*;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@SuppressWarnings("serial")
@WebServlet("/member/delete")
public class MemberDeleteServlet extends HttpServlet {
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) 
			throws ServletException, IOException {
//		String sNo = req.getParameter("no");
		Connection conn = null;
		Statement stmt = null;
//		System.out.println("no=" + sNo);

		/*this.getServletContext()를 이용해서
		 * 웹어플리케이션 전체가 공유하는 Context 영역을
		 * 접근할 수 있다.*/
		ServletContext sc = this.getServletContext();
		String driver = sc.getInitParameter("driver");
		String url = sc.getInitParameter("url");
		String id = sc.getInitParameter("username");
		String pwd = sc.getInitParameter("password");
		

		
		try {
			// mySql driver 객체 로딩
			Class.forName(driver);
			conn = DriverManager.getConnection(url, id, pwd);
			stmt = conn.createStatement();
			stmt.executeUpdate(
					"DELETE FROM members WHERE mno=" + 
					req.getParameter("no"));
			
			resp.sendRedirect("list");
		}catch(Exception e) {
			throw new ServletException(e);

		}finally {

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
	
	
			
		





