package spms.filters;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

public class CharacterEncodingFilter implements Filter {
	
	FilterConfig config;

	@Override
	public void destroy() {
	}

	@Override
	public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain)
			throws IOException, ServletException {
		String encoding = this.config.getInitParameter("encoding");
		req.setCharacterEncoding(encoding);
		
		//System.out.println("CharacterEncodingFilter - " + encoding);
		System.out.println("CharacterEncodingFilter : " +
					((HttpServletRequest)req).getRequestURI() +
					" : " + ((HttpServletRequest)req).getMethod());

		/* 다음 필터에  req와 resp를 전달한다
		 * 다음 필터가 없다면 서블릿의 service()를 호출한다
		 * */
		FilterChain nextFilter = chain;
		nextFilter.doFilter(req, resp);
	}

	@Override
	public void init(FilterConfig arg0) throws ServletException {
		config = arg0;
	}

}
