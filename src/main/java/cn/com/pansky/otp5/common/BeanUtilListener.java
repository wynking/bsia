package cn.com.pansky.otp5.common;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

public class BeanUtilListener implements  ServletContextListener{

	@Override
	public void contextDestroyed(ServletContextEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void contextInitialized(ServletContextEvent arg0) {
		//注册beancopy类型转换(java.util.Date)
		ConvertUtilsWapper.register();
		
		
	}

}
