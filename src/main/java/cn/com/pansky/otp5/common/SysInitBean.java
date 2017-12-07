package cn.com.pansky.otp5.common;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.context.ServletContextAware;

import cn.com.pansky.otp5.baseplatform.dao.po.Dictionary;
import cn.com.pansky.otp5.baseplatform.service.IDictionaryService;
import redis.clients.jedis.JedisCluster;

/**
 * 
 * @ClassName SysInitBean
 * @Description TODO(这里用一句话描述这个类的作用)
 * @author Administrator
 * @Date 2017年9月21日 下午9:31:00
 * @version 1.0.0
 */
//实现ServletContextAware，可以获得servletcontext  
//@Component注解了，直接在xml里配置这个bean就行了，系统自动调用  
//@Component 
public class SysInitBean implements ServletContextAware {  

    @Autowired  
    private IDictionaryService dictionaryService;  
      
    @Override  
    public void setServletContext(ServletContext sc) {  
        // 把项目名称放到application中  
        //String ctxPath = sc.getContextPath();  
        //sc.setAttribute("ctxPath",ctxPath);  
        
        /*System.out.println("AAAAAAAAAAAAAAAAAAAAAAAAAAAAA");
        System.out.println("AAAAAAAAAAAAAAAAAAAAAAAAAAAAA");
        System.out.println("AAAAAAAAAAAAAAAAAAAAAAAAAAAAA");
        System.out.println("AAAAAAAAAAAAAAAAAAAAAAAAAAAAA");
        System.out.println("AAAAAAAAAAAAAAAAAAAAAAAAAAAAA");
        System.out.println("AAAAAAAAAAAAAAAAAAAAAAAAAAAAA");
        System.out.println("AAAAAAAAAAAAAAAAAAAAAAAAAAAAA");*/
        
        /*List<Dictionary> dics= dictionaryService.getAllDictionaryByEnable("1");
        //初始化数据字典到application中  
        sc.setAttribute(ConstantUtil.DICNATIONARY,  DictionaryUtil.getDicByIsDicName(dics, "0"));
        
        
        List<Dictionary> types = DictionaryUtil.getDicByIsDicName(dics, "1");
        List<Dictionary> vals = DictionaryUtil.getDicByIsDicName(dics, "0");
        for(Dictionary type : types){
            List<Dictionary> type_dics = new ArrayList<Dictionary>();
            for(Dictionary val : vals){
                if(val.getType().equals(type.getType())){
                    type_dics.add(val);
                }
            }
            sc.setAttribute(type.getType(),  type_dics);
        }
        */
        List<Dictionary> allDics= dictionaryService.getAllDictionaryByEnable("1");
        DictionaryUtil.setDicToCache(sc, allDics);

//        RedisUtil.getRedis().set("name", "马冠军");
//        RedisUtil.getRedis().set("age", "28");
//        RedisUtil.getRedis().set("sex", "男");
    }  
    

    
    
}  