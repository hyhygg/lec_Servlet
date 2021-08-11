package spms.listeners;

import java.sql.Connection;
import java.sql.DriverManager;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import spms.dao.MemberDao;
import spms.util.DBConnectionPool;

public class ContextLoaderListener implements ServletContextListener {

	DBConnectionPool connPool;
	
	@Override
	public void contextDestroyed(ServletContextEvent sce) {
		try {
			System.out.println("contextDestroyed 호출 - WebApp 종료");
			connPool.closeAll();
		}catch(Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public void contextInitialized(ServletContextEvent sce) {

		try {
			System.out.println("contextInitialized 호출 - WebApp 시작");
			ServletContext sc = sce.getServletContext();
			String driver = sc.getInitParameter("driver");
			String url = sc.getInitParameter("url");
			String id = sc.getInitParameter("username");
			String pwd = sc.getInitParameter("password");
			Class.forName(driver);
			
			connPool = new DBConnectionPool(
					driver, url, id, pwd, 5);
			
			MemberDao memberDao = new MemberDao();
			memberDao.setDBConnectionPool(connPool);
			
			// 모든 서블릿이 사용할 수 있도록  memberDao를 공유한다
			sc.setAttribute("memberDao", memberDao);
		}catch(Exception e) {
			e.printStackTrace();
		}

	}

}
