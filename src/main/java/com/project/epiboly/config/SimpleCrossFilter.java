package com.project.epiboly.config;

import org.springframework.stereotype.Component;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author Administrator
 * 跨域过滤器
 */
@Component
public class SimpleCrossFilter implements Filter {
	
    @Override  
    public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain)
            throws IOException, ServletException {
    	HttpServletRequest request = (HttpServletRequest) req;
    	request.setCharacterEncoding("UTF-8");
        HttpServletResponse response = (HttpServletResponse) res;
		response.setHeader("Access-Control-Allow-Credentials", "true");
        response.setHeader("Access-Control-Allow-Origin", request.getHeader("Origin"));
        response.setHeader("Access-Control-Allow-Methods", "POST, GET, PUT, DELETE, OPTIONS");  
        response.setHeader("Access-Control-Max-Age", "3600");  
        response.setHeader("Access-Control-Allow-Headers", "Origin, X-Requested-With, Content-Type, Accept, token");  
        chain.doFilter(req, res);  
    }

    /**
     * filterConfig FilterConfig
     */
	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
		
	}

	/**
	 * destroy
	 */
	@Override
	public void destroy() {
	
	}  
}