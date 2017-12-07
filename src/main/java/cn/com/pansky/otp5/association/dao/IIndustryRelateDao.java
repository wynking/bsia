package cn.com.pansky.otp5.association.dao;

import java.util.List;

import org.mybatis.spring.annotation.MapperScan;

import cn.com.pansky.otp5.association.dao.po.AptitudeRelate;
import cn.com.pansky.otp5.association.dao.po.IndustryRelate;
import cn.com.pansky.otp5.association.dao.po.TechRelate;
import cn.com.pansky.otp5.baseplatform.dao.IBaseDao;

@MapperScan
public interface IIndustryRelateDao extends IBaseDao<IndustryRelate> {
    
    List<IndustryRelate> selectByEID(String eId);
    
    /**
     * 
     * @Description 根据企业ID删除技术领域关联
     * @param eId
     */
    void deleteByEID(String eId);

    void updateEnableByEId(String eId,String enable);
    
    List<IndustryRelate> selectAll();
}