package cn.com.pansky.otp5.common;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

public class SystemContextFilter implements Filter {

    @Override
    public void destroy() {
        
    }

    @Override
    public void doFilter(ServletRequest req, ServletResponse resp,
            FilterChain chain) throws IOException, ServletException {
//        int offset = 0;
        int pageNumber = 1;
        try{
//            offset = Integer.parseInt(req.getParameter("offset"));
            pageNumber = Integer.parseInt(req.getParameter("pageNumber"));
        } catch(NumberFormatException e) {
//            e.printStackTrace();
        }
        int pageSize = 5;
        try{
            pageSize = Integer.parseInt(req.getParameter("pageSize"));
        } catch(NumberFormatException e) {
//            e.printStackTrace();
        }
        String userId = "";
        try{
            userId = UserUtil.getUser((HttpServletRequest)req).getId();
        } catch(Exception e) {
//            e.printStackTrace();
        }
        String _format = req.getParameter("format");
        try{
//          SystemContext.setPageOffset(offset);
            SystemContext.setCurrent(pageNumber);;
            SystemContext.setPageSize(pageSize);
            SystemContext.setUserId(userId);
            SystemContext.setFormat(_format);
            chain.doFilter(req, resp);
        } finally {
            SystemContext.removePageOffset();
            SystemContext.removePageSize();
            SystemContext.removeUserId();
        }
    }

    @Override
    public void init(FilterConfig arg0) throws ServletException {
        // TODO Auto-generated method stub

    }

}