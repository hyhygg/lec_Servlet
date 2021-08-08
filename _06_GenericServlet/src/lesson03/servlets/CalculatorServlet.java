package lesson03.servlets;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.GenericServlet;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebServlet;

@SuppressWarnings("serial")
/* 애노테이션으로 등록/주소 매핑을 하면
 * 향후 다른 주소로 변경할 시 소스코드를 열어서 수정하여 컴파일되어야
 * 가능하다.
 * 서버를 다시 껐다 켜야 한다.
 * */
//@WebServlet("/calc")
public class CalculatorServlet extends GenericServlet {

	@Override
	public void service(ServletRequest req, ServletResponse res) throws ServletException, IOException {
			
		int a = Integer.parseInt(req.getParameter("a"));
		int b = Integer.parseInt(req.getParameter("b"));
		
		res.setContentType("text/plain");	// 평문전송할께
		res.setCharacterEncoding("UTF-8");
		
		PrintWriter out = res.getWriter();
		
		out.println("a="+a+","+"b="+b+"의 계산결과");
		out.println("a+b="+(a+b));
		out.println("a-b="+(a-b));
		out.println("a*b="+(a*b));
		out.println("a/b="+(a/b));
		out.println("a%b="+(a%b));
	}

}





