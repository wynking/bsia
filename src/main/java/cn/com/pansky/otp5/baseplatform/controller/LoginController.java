package cn.com.pansky.otp5.baseplatform.controller;

import java.util.Enumeration;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.com.pansky.otp5.baseplatform.controller.vo.UserVO;
import cn.com.pansky.otp5.baseplatform.service.IUserService;
import cn.com.pansky.otp5.common.ConstantUtil;
import cn.com.pansky.otp5.common.ResultBean;

/**
 * @author wyn
 */
@Controller
@RequestMapping("/login/")
public class LoginController {
    
    @Resource  
    private IUserService userService;

    /**
     * @Description (TODO这里用一句话描述这个方法的作用)
     * @param req
     * @param resp
     * @return
     */
    @RequestMapping("toLogin")
    public String toLogin(HttpServletRequest req, HttpServletResponse resp) {

        return "login";
    }

    /**
     * @Description 用户输入用户名和密码登录
     * @return
     */
    @RequestMapping("/login")
    @ResponseBody
    public ResultBean index(HttpServletRequest req, HttpServletResponse resp) {
        ResultBean rb = new ResultBean();
        Enumeration enu = req.getParameterNames();
        while (enu.hasMoreElements()) {
            String paraName = (String) enu.nextElement();
            System.out.println(paraName + ": " + req.getParameter(paraName));
        }
        String loginName = req.getParameter("loginName");
        String password = req.getParameter("password");
        
        UserVO user = userService.getUserByLoginNameAndPwd(loginName, password);
        if(!StringUtils.isEmpty(user)){
            req.getSession().setAttribute(ConstantUtil.USER, user);
            rb.setFlag(1);
        }else{
            rb.setMsg("用户名密码不匹配！");
            rb.isFail();
        }
        return rb;
    }
    
    /**
     * 用户退出登录，系统注销
     * @Description (TODO这里用一句话描述这个方法的作用)
     * @param req
     * @param resp
     * @return
     */
    @RequestMapping("logout")
    public String logout(HttpServletRequest req, HttpServletResponse resp) {
        req.getSession().removeAttribute(ConstantUtil.USER);
       //req.getSession().removeAttribute(ConstantUtil.DICNATIONARY);
        req.getSession().removeAttribute(ConstantUtil.USERID);
        return "login";
    }

}
