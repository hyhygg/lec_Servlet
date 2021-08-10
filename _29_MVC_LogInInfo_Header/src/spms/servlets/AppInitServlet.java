package spms.servlets;

import java.sql.Connection;
import java.sql.DriverManager;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;

@SuppressWarnings("serial")
public class AppInitServlet extends HttpServlet {

	@Override
	public void init() throws ServletException {
		System.out.println("AppInitServlet 준비...");
		try {
			// 웹App의 수명과 함께 하는 공유 공간
			ServletContext sc = this.getServletContext();
			String driver = sc.getInitParameter("driver");
			String url = sc.getInitParameter("url");
			String id = sc.getInitParameter("username");
			String pwd = sc.getInitParameter("password");
			
			Class.forName(driver);
			Connection conn = 
					DriverManager.getConnection(url, id, pwd);
			/* 이곳에 저장하면 웹App 가동 내내
			 * 모든 서블릿/jsp에서 사용가능하다.
			 * */
			sc.setAttribute("conn", conn);
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	@Override
	public void destroy() {
		System.out.println("AppInitServlet 종료...");
		try {
			ServletContext sc = this.getServletContext();
			Connection conn = (Connection)sc.getAttribute("conn");
			if(conn!=null && conn.isClosed()==false)
				conn.close();
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
}






