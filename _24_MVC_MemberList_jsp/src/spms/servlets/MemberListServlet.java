package spms.servlets;

import java.io.IOException;
import java.io.PrintWriter;
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
		
		ServletContext sc = this.getServletContext();
		String driver = sc.getInitParameter("driver");
		String url = sc.getInitParameter("url");
		String id = sc.getInitParameter("username");
		String pwd = sc.getInitParameter("password");
		
		try {
			// 1) MySQL 제어 객체를 로딩
			Class.forName(driver);
			// 2) MySQL과 연결
			conn = DriverManager.getConnection(url, id, pwd);
			
			stmt = conn.createStatement();
			rs = stmt.executeQuery(sqlSelect);
			
			resp.setContentType("text/html; charset=UTF-8"); // 먼저 호출
			
			/* MySQL 서버로부터 테이블 데이터를 가져와  vo객체를 생성하고
			 jsp에 객체를 공유한다.
			 jsp로 요청을 전달한다.
			*/
			
			// 1. jsp에 공유할 객체를 선언
			// members 테이블의 모든 정보를 컬렉션에 저장해서 공유한다
			ArrayList<Member> members = new ArrayList<Member>();
			
			/*
			while(rs.next()) {
				Member member = new Member();
				member.setNo(rs.getInt("mno"));
				member.setName(rs.getString("mname"));
				member.setEmail(rs.getString("email"));
				member.setCreatedDate(rs.getDate("cre_date"));
				members.add(member);
			}
			*/
			
			// 2. member VO객체를 생성한 후 
			// db로부터의 결과값을 꺼내서 담고
			// ArrayList객체에 vo를 저장한다
			while(rs.next()) {
				members.add(new Member()
						.setNo(rs.getInt("mno"))
						.setName(rs.getString("mname"))
						.setEmail(rs.getString("email"))
						.setCreatedDate(rs.getDate("cre_date")));
			}
			
			// 3. jsp에 전달하기 위해 request의 공유 공간에 저장한다
			req.setAttribute("members", members);
			
			// 4. jsp로 request를 전달한다
			RequestDispatcher rd = req.getRequestDispatcher
										("/member/MemberList.jsp");
			rd.include(req, resp);
//			rd.forward(req, resp);
			/* jsp로 전달하는 2가지 방식
			 * 1) forward : 제어권을 아예 넘겨 준다
			 *              (네가 알아서 처리해라)
			 *              다시 요청이 돌아오지 않는다
			 * 2) include : 실행할 동안 제어권을 줬다가
			 *              처리가 끝나면 다시 넘겨 받는다
			 *              마지막 확인은 내가 처리한다
			 * */
			PrintWriter out = resp.getWriter();
			out.println("<p>MemberListServlet-End</p>");
		}catch(Exception e) {
			throw new ServletException(e);
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






