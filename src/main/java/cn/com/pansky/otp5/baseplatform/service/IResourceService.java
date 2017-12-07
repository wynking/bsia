package cn.com.pansky.otp5.baseplatform.service;

import java.util.List;

import cn.com.pansky.otp5.baseplatform.controller.vo.ResourceVO;

public interface IResourceService extends IBaseService<ResourceVO>{

    /**
     * 
     * @Description 根据上级ID获取下级资源
     * @param pId
     * @return
     */
    List<ResourceVO> selectResourcesByPId(String pId);
    
    /**
     * 
     * @Description 获取全部可用资源
     * @return
     */
    List<ResourceVO> selectAll();
    
	
}
