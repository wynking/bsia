package cn.com.pansky.otp5.common;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import cn.com.pansky.otp5.baseplatform.controller.vo.UserVO;

/**
 * 
 * @ClassName UserUtil
 * @Description TODO(这里用一句话描述这个类的作用)
 * @author Administrator
 * @Date 2017年10月17日 上午11:08:09
 * @version 1.0.0
 */
public final class UserUtil {

    public static void setUser(HttpServletRequest request,UserVO vo) {
        request.getSession().setAttribute(ConstantUtil.USER, vo);
    }
    
    public static UserVO getUser(HttpServletRequest request) {
        return (UserVO)request.getSession().getAttribute(ConstantUtil.USER);
    }
    
    
    
    
    
    
}
