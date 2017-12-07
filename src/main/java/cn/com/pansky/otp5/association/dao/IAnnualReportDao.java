package cn.com.pansky.otp5.association.dao;

import java.util.List;

import org.mybatis.spring.annotation.MapperScan;

import cn.com.pansky.otp5.association.dao.po.AnnualReport;
import cn.com.pansky.otp5.association.dao.po.AptitudeRelate;
import cn.com.pansky.otp5.baseplatform.dao.IBaseDao;

@MapperScan
public interface IAnnualReportDao extends IBaseDao<AnnualReport> {
    /**
     * 
     * @Description 根据企业id删除年报信息
     * @param eId
     */
    void updateEnableByEId(String eId,String enable);
    
    List<AnnualReport> selectAll();
    
}