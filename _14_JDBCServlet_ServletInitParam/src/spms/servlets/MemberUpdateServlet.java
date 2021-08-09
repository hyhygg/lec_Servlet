package spms.servlets;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@SuppressWarnings("serial")
public class MemberUpdateServlet extends HttpServlet {
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String sNo = req.getParameter("no");
		System.out.println("no=" + sNo);

		/*this.getInitParameter() 메서드를 이용하면
		 * web.xml에 설정한 Servlet Init-Param 문자열을 얻을 수 있다*/
		String driver = this.getInitParameter("driver");
		String url = this.getInitParameter("url");
		String id = this.getInitParameter("username");
		String pwd = this.getInitParameter("password");
		
		System.out.println("driver=" + driver);
		System.out.println("url=" + url);
		System.out.println("username=" + id);
		System.out.println("password=" + pwd);
	}
}





