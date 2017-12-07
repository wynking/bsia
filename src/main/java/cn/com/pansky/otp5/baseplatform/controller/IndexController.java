package cn.com.pansky.otp5.baseplatform.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 
 * @author wyn
 *
 */
@Controller
public class IndexController {

    /**
     * 跳转到
     * @return
     */
    @RequestMapping("/index")
    public String index(){

        /*System.out.println("name=======>>>" + RedisUtil.getRedis().get("name"));
        System.out.println("age=======>>>" + RedisUtil.getRedis().get("age"));
        System.out.println("sex=======>>>" + RedisUtil.getRedis().get("sex"));
        */
        
        /*IDemoService demoService = (IDemoService) SpringContextHolder.getBean("demoService");
        demoService.sayHello("wyn");*/
        
        

//        return "index";
        return "index_my";
    }
    
    /**
     * 跳转到
     * @return
     */
    @RequestMapping("/bootstrapTable")
    public String test(){
        return "bootstrapTable";
    }
	
}
