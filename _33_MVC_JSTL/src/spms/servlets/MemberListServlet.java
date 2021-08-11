package spms.servlets;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import spms.vo.Member;

@SuppressWarnings("serial")
@WebServlet("/member/list")
public class MemberListServlet extends HttpServlet{
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		Connection conn = null;	// MySQL 연결담당
		Statement stmt = null;	// SQL문 담당
		ResultSet rs = null;	// SELECT문의 결과 담당
		
		final String sqlSelect = "SELECT mno,mname,email,cre_date" + "\r\n" +
								"FROM members" + "\r\n" +
								"ORDER BY mno ASC";
		
		/*ServletContext 영역에 공유한 conn 객체를 가져와서 사용하겠다*/
		ServletContext sc = this.getServletContext();
		conn = (Connection)sc.getAttribute("conn");
		
		try {			
			stmt = conn.createStatement();
			rs = stmt.executeQuery(sqlSelect);
			
			resp.setContentType("text/html; charset=UTF-8"); // 먼저 호출
			
			/* members테이블의 전체 인원을 저장하기 위해
			 * ArrayList를 사용한다
			 * */
			ArrayList<Member> members = new ArrayList<Member>();
			
			// DB로부터 members테이블의 정보를 가져와서 member객체에 담고
			// member객체를 ArrayList에 차례로 저장한다.
			while(rs.next()) {
				members.add(new Member()
							.setNo(rs.getInt("mno"))
							.setName(rs.getString("mname"))
							.setEmail(rs.getString("email"))
							.setCreatedDate(rs.getDate("cre_date")));
			}
			
			// jsp에 전달하기 위해 request의 공유 공간에 저장한다
			req.setAttribute("members", members);
			
			// jsp로 request를 전달한다
			RequestDispatcher rd = 
					req.getRequestDispatcher("/member/MemberList.jsp");
			rd.include(req, resp);
			/* jsp로 전달(위임)하는 방식 2가지
			 * 1) forward : 제어권을 아예 넘겨준다(네가 알아서 처리해라)
			 * 2) include : 실행할 동안 제어권을 줬다가 처리가 끝나면 다시 넘겨 받는다
			 *              (마지막 확인은 내가 처리한다)
			 * */
			
		}catch(Exception e) {
			//throw new ServletException(e);
			
			req.setAttribute("error", e);
			RequestDispatcher rd = req.getRequestDispatcher("/Error.jsp");
			rd.forward(req, resp);
			
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
		}
	}
}






