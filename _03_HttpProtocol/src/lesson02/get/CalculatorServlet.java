package lesson02.get;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Hashtable;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@SuppressWarnings("serial")
@WebServlet("/CalculatorServlet")
public class CalculatorServlet extends HttpServlet {
	
	private Hashtable<String, Operator> opTable = 
			new Hashtable<String, Operator>();
	
	public CalculatorServlet() {
		opTable.put("+", new PlusOperator());
		opTable.put("-", new MinusOperator());
		opTable.put("*", new MultipleOperator());
		opTable.put("/", new DivOperator());
	}

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		System.out.println("get 요청");
		process(req, resp);
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		System.out.println("post 요청");
		process(req, resp);
	}
	
	private void process(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// 1.  브라우저가 보낸 변수값들을 추출
		String sV1 = req.getParameter("v1");
		String sV2 = req.getParameter("v2");
		String op = req.getParameter("op");
		
		double v1 = Double.parseDouble(sV1);
		double v2 = Double.parseDouble(sV2);
		
		// 2. 브라우저한테 우리가 전송하는 데이터를 utf-8로 해석해
		resp.setContentType("text/html; charset=UTF-8");
		
		// 3. 우리 서블릿 객체(CalculatorServlet) -> tomcat ->
		//    apache -> 브라우저 -> calculator.html
		//    소켓 연결 스트림을 포함하는 통신 객체 추출
		PrintWriter out = resp.getWriter();
		
		// 4. 브라우저에 제목 전송
		out.println("<html><body>");
		out.println("<h1>계산 결과</h1>");
		out.println("결과 : ");
		
		// 5. op값에 따른 담당객체를 objTable로부터 얻는다
		Operator operator = opTable.get(op);
		
		// 6. 계산
		try {
			if(operator==null) {
				out.println("계산할 수 없는 연산자입니다!");
			}else {
				double result = operator.execute(v1, v2);
				out.println(String.format("%.3f", result));
			}
		}catch(Exception e) {
			out.println("연산 오류 발생!");
		}
		
		// 7. 브라우저에 마무리 태그 전송
		out.println("</body></html>");
	}
}










