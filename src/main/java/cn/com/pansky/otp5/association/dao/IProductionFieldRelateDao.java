package cn.com.pansky.otp5.association.dao;

import java.util.List;

import org.mybatis.spring.annotation.MapperScan;

import cn.com.pansky.otp5.association.dao.po.Production;
import cn.com.pansky.otp5.association.dao.po.ProductionFieldRelate;
import cn.com.pansky.otp5.baseplatform.dao.IBaseDao;

@MapperScan
public interface IProductionFieldRelateDao extends IBaseDao<ProductionFieldRelate> {

    List<ProductionFieldRelate> selectByProducttionID(String eId);
    
    /**
     * 
     * @Description 根据产品ID删除产品服务领域关联
     * @param eId
     */
    void deleteByProducttionID(String eId);

    void updateEnableByEId(String eId,String enable);

    List<ProductionFieldRelate> selectAll();
}