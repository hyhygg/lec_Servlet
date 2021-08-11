package spms.servlets;

import java.sql.Connection;
import java.sql.DriverManager;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;

@SuppressWarnings("serial")
public class AppInitServlet extends HttpServlet {
	/* 웹 어플리케이션 시작시
	 * DB 접속 객체를 만들어서
	 * ServletContext 영역에 공유한다
	 * */
	@Override
	public void init() throws ServletException {
		System.out.println("AppInitServlet 준비...");
		try {
			ServletContext sc = this.getServletContext();
			String driver = sc.getInitParameter("driver");
			String url = sc.getInitParameter("url");
			String id = sc.getInitParameter("username");
			String pwd = sc.getInitParameter("password");
			Class.forName(driver);
			Connection conn = 
					DriverManager.getConnection(url, id, pwd);
			/* ServletContext 영역에 conn 객체를 저장
			 * 이곳에 저장하면 웹 어플리케이션 가동 내내 
			 * 모든 서블릿/jsp에서 사용가능하다
			 */
			sc.setAttribute("conn", conn);
			
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	/* 웹 어플리케이션 종료시
	 * DB 접속객체가 연결되어있다면 종료한다
	 * */
	@Override
	public void destroy() {
		System.out.println("AppInitServlet 마무리...");
		super.destroy();
		ServletContext sc = this.getServletContext();
		Connection conn = (Connection)sc.getAttribute("conn");
		try {
			if(conn != null && conn.isClosed()==false)
				conn.close();
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
}






