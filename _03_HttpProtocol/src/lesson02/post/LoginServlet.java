package lesson02.post;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@SuppressWarnings("serial")
@WebServlet("/LoginServlet")
public class LoginServlet extends HttpServlet {

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// 1. 브라우저에서 보내오는 정보를 utf-8로 해석(한글때문에)
		//    브라우저 -> 서버
		req.setCharacterEncoding("UTF-8");
		
		// 2. 브라우저의 변수를 추출
		String id = req.getParameter("id");
		String password = req.getParameter("password");
		
		// 3. 브라우저에 전송하는 데이터를 utf-8로 해석해달라
		//   서버 -> 브라우저
		resp.setContentType("text/html;charset=UTF-8"); 
		
		// 4. 통신 객체를 얻음
		PrintWriter out = resp.getWriter();
		
		// 5. 결과를 전송한다
		out.println("<html><body>");
		if(password.equals("1111")) {
			out.println("<h1>로그인 결과</h1>");
			out.println("<strong>" + id + "</strong>님을 환영합니다");
		}else {
			out.println("암호가 틀렸습니다!");
		}
		out.println("</body></html>");
	}


}










