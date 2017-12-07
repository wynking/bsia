package cn.com.pansky.otp5.baseplatform.aop;
import java.util.Date;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.time.DateFormatUtils;
import org.apache.commons.lang.time.DateUtils;
import org.aspectj.lang.JoinPoint;
import org.springframework.beans.BeanWrapper;
import org.springframework.beans.BeanWrapperImpl;

import cn.com.pansky.otp5.common.ConstantUtil;
import cn.com.pansky.otp5.common.SystemContext;


/**
 * 
 * @ClassName DaoAop
 * @Description 对DAO插入和更新方法的拦截，并设置公共字段的信息
 * @author wyn
 * @Date 2017年10月18日 下午9:05:53
 * @version 1.0.0
 */
public class DaoAop {

    private static final String CREATIONUSER     = "creationUser";
    private static final String LASTMODIFYUSER    = "lastModifyUser";
    private static final String CREATIONTIME   = "creationTime";
    private static final String LASTMODIFYTIME = "lastModifyTime";

    public void beforeInsert(JoinPoint jp) {
        Object[] args = jp.getArgs();
        if (args != null && args.length > 0) {
            Object argument = args[0];
            BeanWrapper beanWrapper = new BeanWrapperImpl(argument);
            Date date = new Date();
            // 设置创建时间和修改时间
            if (beanWrapper.isWritableProperty(CREATIONTIME)) {
                Object val = beanWrapper.getPropertyValue(CREATIONTIME);
                System.out.println(CREATIONTIME + "============================>>>>>>>" + val);
                if(val == null || StringUtils.isEmpty(val.toString())){
                    beanWrapper.setPropertyValue(CREATIONTIME, date);
                }
            }
            if (beanWrapper.isWritableProperty(LASTMODIFYTIME)) {
                Object val = beanWrapper.getPropertyValue(LASTMODIFYTIME);
                if(val == null || StringUtils.isEmpty(val.toString())){
                    beanWrapper.setPropertyValue(LASTMODIFYTIME, date);
                }
            }
            // 设置创建人和修改人
            if (beanWrapper.isWritableProperty(CREATIONUSER)) {
                Object val = beanWrapper.getPropertyValue(CREATIONUSER);
                System.out.println(CREATIONUSER + "============================>>>>>>>" + val);
                if(val == null || StringUtils.isEmpty(val.toString())){
                    beanWrapper.setPropertyValue(CREATIONUSER, SystemContext.getUserId());
                }
            }
            if (beanWrapper.isWritableProperty(LASTMODIFYUSER)) {
                Object val = beanWrapper.getPropertyValue(LASTMODIFYUSER);
                if(val == null || StringUtils.isEmpty(val.toString())){
                    beanWrapper.setPropertyValue(LASTMODIFYUSER, SystemContext.getUserId());
                }
            }
        }
    }

    @SuppressWarnings({ "rawtypes", "unchecked" })
    public void beforeUpdate(JoinPoint jp) {

        Object[] args = jp.getArgs();
        // myBatis只能传递一个参数
        if (args != null && args.length > 0) {
            Object argument = args[0];
            BeanWrapper beanWrapper = new BeanWrapperImpl(argument);
            Date date = new Date();
            // 设置修改时间
            if (beanWrapper.isWritableProperty(LASTMODIFYTIME)) {
                Object val = beanWrapper.getPropertyValue(LASTMODIFYTIME);
                if(val == null || StringUtils.isEmpty(val.toString())){
                    beanWrapper.setPropertyValue(LASTMODIFYTIME, date);
                }
            }
            // 设置修改人
            if (beanWrapper.isWritableProperty(LASTMODIFYUSER)) {
                Object val = beanWrapper.getPropertyValue(LASTMODIFYUSER);
                if(val == null || StringUtils.isEmpty(val.toString())){
                    beanWrapper.setPropertyValue(LASTMODIFYUSER, SystemContext.getUserId());
                }
            }
        }
    }
}