package lesson01.servlets;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.GenericServlet;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebServlet;

@SuppressWarnings("serial")
@WebServlet("/calc")
public class CalculatorServlet extends GenericServlet {

	@Override
	public void service(ServletRequest req, ServletResponse res) throws ServletException, IOException {
		// 1. 브라우저가 보낸 변수를 추출
		String op = req.getParameter("op");
		String sV1 = req.getParameter("v1");
		String sV2 = req.getParameter("v2");
		
		// 2. 문자열 숫자 -> 정수로 형변환
		int v1 = Integer.parseInt(sV1);
		int v2 = Integer.parseInt(sV2);
		int result = 0;
		
		// 3. 브라우저한테 utf-8로 전송할 것을 알려줌
		res.setContentType("text/html; charset=UTF-8");
		
		// 4. 출력을 위해 브라우저와 연결된 객체를 추출한다
		PrintWriter out = res.getWriter();
		
		// 5. 비즈니스 로직 처리(계산기 기능)
		switch(op) {
		case "+": result = v1+v2; break;
		case "-": result = v1-v2; break;
		case "*": result = v1*v2; break;
		case "/":
			if(v2==0) {
				out.println("0으로 나눌 수 없습니다");
				return;
			}
			result = v1/v2; 
			break;
		}
		out.println(v1+" " + op + " " + v2 + " = " + result);
	}

}








