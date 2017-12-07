/*package cn.com.pansky.otp5.baseplatform.controller;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.com.pansky.otp5.redis.RedisClusterCache;

@Controller
@RequestMapping(value = "redisCluster")
public class RedisClusterController {

    @Resource
    private RedisClusterCache redisClusterCache;
    
    public RedisClusterController() {
        System.err.println("初始化=========================>>>RedisClusterController");
    }
 
    @RequestMapping(value = "clusterSave",method = {RequestMethod.GET,RequestMethod.POST})
    @ResponseBody
    public Object clusterSave(){
        redisClusterCache.put("cluster","save cluster");
        Token token = new Token();
        token.setExpires_in(1000);
        token.setAccess_token("hello world");
        redisClusterCache.put("token",token);
        return "ok";
    }
 
    @RequestMapping(value = "getKey",method = RequestMethod.GET)
    @ResponseBody
    public Object getCluster(String key){
        Object val = redisClusterCache.get(key);
        return val;
    }
}*/