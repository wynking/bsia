package cn.com.pansky.otp5.baseplatform.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletWebRequest;

import cn.com.pansky.otp5.common.GridBean;
import cn.com.pansky.otp5.common.ResultBean;

/**
 * 
 * @ClassName BaseController
 * @Description controller基类，所有的controller都需要集成此类
 * @author wyn
 * @Date 2017年9月26日 下午5:03:04
 * @version 1.0.0
 */
@Controller
public abstract class BaseController<T> {
    
    @Autowired
    protected HttpServletRequest request;
    
    /*protected HttpServletResponse response;
    public BaseController(){
        System.err.println("初始化===================>>>>BaseController");
        ServletWebRequest servletContainer = (ServletWebRequest)RequestContextHolder.getRequestAttributes();
        response = servletContainer.getResponse();
    }*/
    
    public abstract ResultBean add(T vo);
    
    public abstract GridBean<T> findByPage(HttpServletRequest req,HttpServletResponse resp);
        
    public abstract ResultBean selectByPrimaryKey(String id);
    
    public abstract ResultBean updateByPrimaryKey(T vo);
    
    public abstract ResultBean deleteByPrimaryKey(String id);
    
}
