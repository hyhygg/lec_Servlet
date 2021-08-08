package lesson03.servlets;

import java.io.IOException;

import javax.servlet.Servlet;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

public class HelloServlet implements Servlet {
	
	ServletConfig config;
	
	@Override
	public void init(ServletConfig arg0) throws ServletException {
		System.out.println("init() 호출됨");
		this.config = arg0;
	}

	@Override
	public void service(ServletRequest arg0, ServletResponse arg1) throws ServletException, IOException {
		System.out.println("service() 호출됨");
	}
	
	@Override
	public void destroy() {
		System.out.println("destroy() 호출됨");
	}

	@Override
	public ServletConfig getServletConfig() {
		System.out.println("getServletConfig()");
		return this.config;
	}

	@Override
	public String getServletInfo() {
		System.out.println("getServletInfo()");
		return "version=1.0;author=albert;copyright=2021";
	}



}
