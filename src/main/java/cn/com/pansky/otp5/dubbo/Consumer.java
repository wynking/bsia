package cn.com.pansky.otp5.dubbo;

import org.springframework.context.support.ClassPathXmlApplicationContext;

public class Consumer {  
  
    public static void main(String[] args) throws Exception {  
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext(  
                new String[] { "applicationContext.xml" });  
        context.start();  
  
       /* IDemoService demoService = (DemoServiceImpl) context.getBean("demoService");  
        String hello = demoService.sayHello("hejingyuan");  
        System.out.println(hello);  */
  
       /* List list = demoService.getUsers();  
        if (list != null && list.size() > 0) {  
            for (int i = 0; i < list.size(); i++) {  
                System.out.println(list.get(i));  
            }  
        }  */
        System.in.read();  
    }  
  
}  