package cn.com.pansky.otp5.common;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

/**
 * 
 * @ClassName WebTools
 * @Description TODO(这里用一句话描述这个类的作用)
 * @author wyn
 * @Date 2017年9月12日 下午5:55:02
 * @version 1.0.0
 */
public class WebTools {

    /**
     * 
     * @Description (TODO这里用一句话描述这个方法的作用)
     * @param request
     * @return
     */
    @SuppressWarnings("unchecked")
    public static Map getParameterMap(HttpServletRequest request) {
        // 参数Map
        Map properties = request.getParameterMap();
        // 返回值Map
        Map returnMap = new HashMap<String,String>();
        Iterator entries = properties.entrySet().iterator();
        Map.Entry entry;
        String name = "";
        String value = "";
        while (entries.hasNext()) {
            entry = (Map.Entry) entries.next();
            name = (String) entry.getKey();
            Object valueObj = entry.getValue();
            if(null == valueObj){
                value = "";
            }else if(valueObj instanceof String[]){
                String[] values = (String[])valueObj;
                for(int i=0;i<values.length;i++){
                    value = values[i] + ",";
                }
                value = value.substring(0, value.length()-1);
            }else{
                value = valueObj.toString();
            }
            System.out.println(name + "=====页面参数 和 值=========>>>>" + value);
            returnMap.put(name, value);
        }
        
        
        return returnMap;
    }
}
