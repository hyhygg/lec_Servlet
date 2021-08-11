package spms.servlets;

import java.io.IOException;
import java.sql.*;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import spms.vo.Member;

@WebServlet("/auth/login")
@SuppressWarnings("serial")
public class LogInServlet extends HttpServlet {

	/* 주소로 접근을 하면 
	 * 로그인 폼을 보여준다
	 * */
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		RequestDispatcher rd = 
				req.getRequestDispatcher("/auth/LogInForm.jsp");
		rd.forward(req, resp);
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		Connection conn= null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		String sqlLogIn = "SELECT mname,email " + "\r\n" +
						  "FROM members WHERE " + "\r\n" +
						  "email=? AND pwd=?";
		try {
			ServletContext sc = this.getServletContext();
			conn = (Connection)sc.getAttribute("conn");
			stmt = conn.prepareStatement(sqlLogIn);
			stmt.setString(1, req.getParameter("email"));
			stmt.setString(2, req.getParameter("password"));
			rs = stmt.executeQuery();
			// 결과가 존재한다면 '로그인 성공!'
			if(rs.next()) {
				Member member = new Member()
									.setEmail(rs.getString("email"))
									.setName(rs.getString("mname"));
				// 로그인 정보를 session 공유 공간에 저장한다
				// 세션 timeout전까지는 보관된다
				HttpSession session = req.getSession();
				session.setAttribute("member", member);
				
				/*
				현재 경로가 /auth/login이므로 
				../ auth 경로 위로 이동하고
				다시 거기서부터 /member/list로 진입
				*/
				resp.sendRedirect("../member/list");
			}
			// 로그인 실패
			else {
				RequestDispatcher rd = 
						req.getRequestDispatcher("/auth/LogInFail.jsp");
				rd.forward(req, resp);
			}
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			try {
				if(rs != null)
					rs.close();
			}catch(Exception e) {
				e.printStackTrace();
			}
			
			try {
				if(stmt != null)
					stmt.close();
			}catch(Exception e) {
				e.printStackTrace();
			}
		}
	}
}



















