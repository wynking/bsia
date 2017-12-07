package cn.com.pansky.otp5.common;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;

import org.springframework.util.StringUtils;

import cn.com.pansky.otp5.baseplatform.dao.po.Dictionary;
import cn.com.pansky.otp5.baseplatform.service.IDictionaryService;
import cn.com.pansky.otp5.redis.RedisUtil;

/**
 * 
 * @ClassName DictionaryUtil
 * @Description TODO(这里用一句话描述这个类的作用)
 * @author Administrator
 * @Date 2017年9月22日 下午1:24:36
 * @version 1.0.0
 */
public class DictionaryUtil {

    /**
     * 
     * @Description (TODO这里用一句话描述这个方法的作用)
     * @param request
     * @return
     */
    @SuppressWarnings("unchecked")
    public static List<Dictionary> getAllEnableDics(HttpServletRequest request) {
        ServletContext sc = request.getServletContext();
        boolean redisSwith = Boolean.parseBoolean(PorpertiesConfigurer.getContextProperty("redisSwith").toString()) ;
        List<Dictionary> dics = null;
        if(redisSwith){
           dics = (List<Dictionary>) RedisUtil.get(ConstantUtil.DICNATIONARY);
        }else{
           dics = (List<Dictionary>) sc.getAttribute(ConstantUtil.DICNATIONARY);
        }
        return dics;
    }
    

    /**
     * 
     * @Description 获取指定类型的可用字典信息
     * @param request
     * @param dicType
     * @return
     */
    public static List<Dictionary> getAllEnableDicByType(HttpServletRequest request,String dicType) {
        ServletContext sc = request.getServletContext();
        boolean redisSwith = Boolean.parseBoolean(PorpertiesConfigurer.getContextProperty("redisSwith").toString()) ;
        List<Dictionary> dics = null;
        if(redisSwith){
           dics = (List<Dictionary>) RedisUtil.get(ConstantUtil.DICNATIONARY);
        }else{
           dics = (List<Dictionary>) sc.getAttribute(ConstantUtil.DICNATIONARY);
        }
//        List<Dictionary> dics = (List<Dictionary>) sc.getAttribute(ConstantUtil.DICNATIONARY);
        List<Dictionary> dics2 = new ArrayList<Dictionary>();
        for(Dictionary dic : dics){
            if(dic.getType().equals(dicType)){
                dics2.add(dic);
            }
        }
        return dics2;
    }
    
    /**
     * 
     * @Description 根据码表类型获取码值集合
     * @param dics
     * @param dicType
     * @return
     */
    public static List<Dictionary> getDicsByType(List<Dictionary> dics,String dicType) {
        List<Dictionary> dics2 = new ArrayList<Dictionary>();
        for(Dictionary dic : dics){
            if(dic.getType().equals(dicType)){
                dics2.add(dic);
            }
        }
        return dics2;
    } 
    

    /**
     * 
     * @Description 根据码表类型获取码值集合
     * @param dics
     * @param dicType
     * @return
     */
    public static List<Dictionary> getDicsByType(HttpServletRequest request, String dicType) {
        List<Dictionary> dics = getAllEnableDics(request);
        List<Dictionary> dics2 = new ArrayList<Dictionary>();
        for(Dictionary dic : dics){
            if(dic.getType().equals(dicType)){
                dics2.add(dic);
            }
        }
        return dics2;
    } 
    
    /**
     * 
     * @Description (TODO这里用一句话描述这个方法的作用)
     * @param code
     * @param dics
     * @return
     */
    public static String getNameByCode(String code,List<Dictionary> dics){
        for(Dictionary dic : dics){
            if(dic.getCode().equals(code)){
                return dic.getName();
            }
        }
        return "";
     }
    
    /**
     * 
     * @Description (TODO这里用一句话描述这个方法的作用)
     * @param code
     * @param request
     * @return
     */
    public static String getNameByCode(String code, HttpServletRequest request){
        List<Dictionary> dics = getAllEnableDics(request);
        for(Dictionary dic : dics){
            if(dic.getCode().equals(code)){
                return dic.getName();
            }
        }
        return "";
     }
    
    /**
     * 
     * @Description 根据code获取地址名称 如：河南省商丘市睢阳区
     * @param code
     * @param req
     * @return
     */
    public static String getRegionNameByCode(String code,HttpServletRequest req){
        String code_province = code.substring(0, 2)+"0000";
        String code_city = code.substring(0,4)+"00";
        
        List<Dictionary> regions = DictionaryUtil.getDicsByType(DictionaryUtil.getAllEnableDics(req), ConstantUtil.DIC_REGION);
        
        String name_province = DictionaryUtil.getNameByCode(code_province, regions);
        String name_city = DictionaryUtil.getNameByCode(code_city, regions);
        String name_area = DictionaryUtil.getNameByCode(code, regions);
        if(code_province.equals("110000")){
            return name_city + name_area;
        }
        //System.out.println(name_province + name_city + name_area);
        return name_province + name_city + name_area;
    }
    
    public static String getRegionCodeByName(String name,HttpServletRequest req){
        List<Dictionary> regions = DictionaryUtil.getDicsByType(DictionaryUtil.getAllEnableDics(req), ConstantUtil.DIC_REGION);
        int index = name.indexOf("市");
        if(index==0){
            return "";
        }else{
            String areaName = name.substring(name.indexOf("市")+1);
            
            for(Dictionary dic : regions){
                if(dic.getName().equals(areaName)){
                    return dic.getCode();
                }
            }
            return "";
        }
        
        
      /*  String code_province = code.substring(0, 2)+"0000";
        String code_city = code.substring(0,4)+"00";
        
        List<Dictionary> regions = DictionaryUtil.getDicsByType(DictionaryUtil.getAllEnableDics(req), ConstantUtil.DIC_REGION);
        
        String name_province = DictionaryUtil.getNameByCode(code_province, regions);
        String name_city = DictionaryUtil.getNameByCode(code_city, regions);
        String name_area = DictionaryUtil.getNameByCode(code, regions);
        if(code_province.equals("110000")){
            return name_city + name_area;
        }
        //System.out.println(name_province + name_city + name_area);
        return name_province + name_city + name_area;*/
    }
    
    
    /**
     * 获取码表信息类型（0:值;1:类型）获取码表信息
     * @Description (TODO这里用一句话描述这个方法的作用)
     * @param dics
     * @param isDicName
     * @return
     */
    public static List<Dictionary> getDicByIsDicName(List<Dictionary> dics,String isDicName){
        List<Dictionary> vals = new ArrayList<Dictionary>();
        for(Dictionary dic : dics){
            if(!StringUtils.isEmpty(dic.getIsDicName()) && dic.getIsDicName().equals(isDicName)){
                vals.add(dic);
            }
        }
        return vals;
    }
    
    /**
     * 
     * @Description 把字典信息放入ServletContext环境中
     * @param sc
     * @param allDics
     */
    public static void setDicToCache(ServletContext sc, List<Dictionary> allDics){
        
        boolean redisSwith = Boolean.parseBoolean(PorpertiesConfigurer.getContextProperty("redisSwith").toString()) ;
        
        if(!redisSwith){
            sc.setAttribute(ConstantUtil.DICNATIONARY,  DictionaryUtil.getDicByIsDicName(allDics, "0"));
        }else{ 
            RedisUtil.set(ConstantUtil.DICNATIONARY, DictionaryUtil.getDicByIsDicName(allDics, "0"));
        }
        //初始化数据字典到application中  
        //sc.setAttribute(ConstantUtil.DICNATIONARY,  DictionaryUtil.getDicByIsDicName(allDics, "0"));
        
        //RedisUtil.set(ConstantUtil.DICNATIONARY, DictionaryUtil.getDicByIsDicName(allDics, "0"));
        List<Dictionary> types = DictionaryUtil.getDicByIsDicName(allDics, "1");
        List<Dictionary> vals = DictionaryUtil.getDicByIsDicName(allDics, "0");
        for(Dictionary type : types){
            List<Dictionary> type_dics = new ArrayList<Dictionary>();
            for(Dictionary val : vals){
                if(val.getType().equals(type.getType())){
                    type_dics.add(val);
                }
            }
//            System.out.println("type=====================>>>" + type.getType());
            
            if(!redisSwith){
                sc.setAttribute(type.getType(),  type_dics);
            }else{ 
                RedisUtil.set(type.getType(), type_dics);
            }
            
            //RedisUtil.set(type.getType(), type_dics);
        }
        //List<Dictionary> sss = (List<Dictionary>)RedisUtil.get(ConstantUtil.DICNATIONARY);
        //System.out.println("SSSSSSSSSSSSSSSSSSSSS====>>>" + sss.size());
    }
    

    /*public static void clearDicFromCache(ServletContext sc){
        sc.removeAttribute(ConstantUtil.DICNATIONARY);
        sc.removeAttribute(ConstantUtil.DICNATIONARY);
        sc.removeAttribute(ConstantUtil.DICNATIONARY);
    }*/
    
    public static String codeToUp(String code){
        String sub2 = code.substring(4,6);
        String mdl2 = code.substring(2,4);
        String pre2 = code.substring(0,2);
//        System.out.println("================>>>" + sub2 + mdl2 + pre2);
        return pre2 + mdl2.replace("00", "") + sub2.replace("00", "");
    }
    
    /**
     * 
     * @Description 根据码表类型和码表name获取码表code
     * @param request
     * @param name
     * @param type
     * @return
     */
    public static String getCodeByNameType(HttpServletRequest request, String name,String type){
        List<Dictionary> dics = getDicsByType(request, type);
        for(Dictionary dic : dics){
            if(name.equals(dic.getName())){
                return dic.getCode();
            }
        }
        return "";
    }
    

    public static String getNameByCodeType(HttpServletRequest request, String code,String type){
        if(org.apache.commons.lang.StringUtils.isNotEmpty(code)){
            List<Dictionary> dics = getDicsByType(request, type);
            for(Dictionary dic : dics){
                if(code.equals(dic.getCode())){
                    return dic.getName();
                }
            }
        }
        return "";
    }
    
    public static void main(String[] args){
        String name = "北京市东城区";
        System.out.println(name.substring(name.indexOf("市")+1));
    }
    
}
