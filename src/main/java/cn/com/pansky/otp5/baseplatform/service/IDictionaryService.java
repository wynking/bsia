package cn.com.pansky.otp5.baseplatform.service;

import java.util.List;
import java.util.Map;

import cn.com.pansky.otp5.baseplatform.dao.po.Dictionary;

public interface IDictionaryService extends IBaseService<Dictionary>{

    /**
     * 
     * @Description 根据状态获取字典信息
     * @param enable
     * @return
     */
    public List<Dictionary> getAllDictionaryByEnable(String enable);
    
    /**
     * 
     * @Description 根据上级节点获取直接下级节点
     * @param params
     * @return
     */
    public List<Dictionary> showDictionaryTree(Map<String,String> params);
    
    /**
     * 
     * @Description 初始化行政区划数据
     * @param dics
     */
    public void importRegion(List<Dictionary> dics);
    
    
    
}
