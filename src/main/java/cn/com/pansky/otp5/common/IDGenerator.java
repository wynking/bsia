package cn.com.pansky.otp5.common;

import java.util.UUID;     
  
/**
 * 
 * @ClassName UUIDGenerator
 * @Description 获取ID工具类
 * @author Administrator
 * @Date 2017年9月10日 上午12:06:58
 * @version 1.0.0
 */
public class IDGenerator {     
    public IDGenerator() {     
    }     
    
    public static String getUUID() {   
//        SnowflakeIdWorker idWorker = new SnowflakeIdWorker(0, 0);
//        return String.valueOf(idWorker.nextId());
        UUID uuid = UUID.randomUUID();     
        String str = uuid.toString();     
        // 去掉"-"符号     
        String temp = str.substring(0, 8) + str.substring(9, 13) + str.substring(14, 18) + str.substring(19, 23) + str.substring(24);     
        return temp;    
    }     

    
    public static long getID() {   
        SnowflakeIdWorker idWorker = new SnowflakeIdWorker(0, 0);
        return idWorker.nextId();
    }     
    //获得指定数量的UUID     
    public static String[] getUUID(int number) {     
        if (number < 1) {     
            return null;     
        }     
        String[] ss = new String[number];     
        for (int i = 0; i < number; i++) {     
            ss[i] = getUUID();     
        }     
        return ss;     
    }     
    
    public static void main(String[] args) {     
       /* String[] ss = getUUID(1);     
        for (int i = 0; i < ss.length; i++) {     
            System.out.println("ss["+i+"]====="+ss[i]);     
        } */    
        System.out.println(getUUID());     
    }     
}    