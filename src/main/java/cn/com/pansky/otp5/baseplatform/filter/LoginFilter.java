package cn.com.pansky.otp5.baseplatform.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.util.StringUtils;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import cn.com.pansky.otp5.common.ConstantUtil;

/**
 * Servlet Filter implementation class LoginFilter
 */
public class LoginFilter implements Filter {

    private WebApplicationContext wac;

    /**
     * Default constructor. 
     */
    public LoginFilter() {
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see Filter#destroy()
	 */
	public void destroy() {
		// TODO Auto-generated method stub
	}

	/**
	 * @see Filter#doFilter(ServletRequest, ServletResponse, FilterChain)
	 */
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {

        HttpServletRequest req = (HttpServletRequest)request;
        HttpServletResponse resp = (HttpServletResponse)response;
	    String uri = req.getRequestURI();
	    
	    //System.out.println("uri============>>>" + uri);
	    if(pass(uri)){
	        chain.doFilter(request, response);
	    }else{
	        HttpSession session = req.getSession();
	        if(StringUtils.isEmpty(session.getAttribute(ConstantUtil.USER))){
	            //System.out.println("============>>>页面重定向");
	            //System.out.println("========DDD====>>>>" + req.getContextPath()); 
	            resp.sendRedirect(req.getContextPath()+"/login/toLogin");
	            return;
	        }else{
	            chain.doFilter(request, response);
	        }
	        
	    }
	    
	    
	}

	/**
	 * @see Filter#init(FilterConfig)
	 */
	public void init(FilterConfig fConfig) throws ServletException {
	    wac = WebApplicationContextUtils.getWebApplicationContext(
	            fConfig.getServletContext());
	}
	
	private boolean pass(String uri){
	    if(uri.contains("login/toLogin")
	            || uri.equals("/baseplatform/")
	            || uri.contains("/resources/")
                || uri.contains("/login/login")
                || uri.contains(".jsp")
	            ){
	        return true;
	    }
	    return false;
	}

}
