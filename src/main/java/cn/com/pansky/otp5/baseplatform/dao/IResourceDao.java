package cn.com.pansky.otp5.baseplatform.dao;

import java.util.List;
import java.util.Map;

import org.mybatis.spring.annotation.MapperScan;

import cn.com.pansky.otp5.baseplatform.controller.vo.ResourceVO;
import cn.com.pansky.otp5.baseplatform.dao.po.Resource;

/**
 * 
 * @ClassName IResourceDao
 * @Description TODO(这里用一句话描述这个类的作用)
 * @author wyn
 * @Date 2017年9月11日 下午11:34:12
 * @version 1.0.0
 */
@MapperScan
public interface IResourceDao extends IBaseDao<Resource> {
    
    /**
     * 
     * @Description 根据上级ID获取下级资源
     * @param pId
     * @return
     */            
    List<Resource> selectResourcesByPId(String pId);

    /**
     * 
     * @Description 获取全部可用资源
     * @return
     */
    List<Resource> selectAll();
    
    
    
}