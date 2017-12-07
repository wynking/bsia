package cn.com.pansky.otp5.baseplatform.service;

import java.util.List;

import cn.com.pansky.otp5.baseplatform.controller.vo.OrganizationVO;

public interface IOrganizationService extends IBaseService<OrganizationVO>{

    /**
     * 
     * @Description 根据上级ID获取下级组织
     * @param pId
     * @return
     */
    List<OrganizationVO> selectDatasByPId(String pId);
    
    /**
     * 
     * @Description 获取全部可用组织
     * @return
     */
    List<OrganizationVO> selectAll();
    
	
}
