package cn.com.pansky.otp5.baseplatform.dao;

import java.util.List;
import java.util.Map;

import cn.com.pansky.otp5.baseplatform.dao.po.Dictionary;

public interface IDictionaryDao extends IBaseDao<Dictionary> {

    /**
     * 
     * @Description (TODO这里用一句话描述这个方法的作用)
     * @param enable
     * @return
     */
    public List<Dictionary> getAllDictionaryByEnable(String enable);
    
    /**
     * 
     * @Description 根据上级获取直接下级节点
     * @param params
     * @return
     */
    public List<Dictionary> showDictionaryTree(Map<String,String> params);
    
    /**
     *
     * @Description 根据码值获取字典信息
     * @param code
     * @return
     */
    public Dictionary selectByCodeAndType(Map<String,Object> params);
}
