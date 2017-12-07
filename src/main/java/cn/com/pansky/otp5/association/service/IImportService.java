package cn.com.pansky.otp5.association.service;

import java.io.File;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import cn.com.pansky.otp5.association.dao.po.ContactInfo;
import cn.com.pansky.otp5.association.dao.po.EnterpriseBasic;
import cn.com.pansky.otp5.baseplatform.dao.po.BasePO;

/**
 * 
 * @ClassName IImportService
 * @Description TODO(这里用一句话描述这个类的作用)
 * @author Administrator
 * @Date 2017年11月19日 下午8:59:54
 * @version 1.0.0
 */
public interface IImportService {

//    List<TechRelate> selectByEID(String eId);
    
    void insertEnterpriseBasic(List<EnterpriseBasic> pos);
    
    void insertContactInfo(List<ContactInfo> pos);
    
    void insertData(List<BasePO> pos,String dataType,HttpServletRequest request);
    
    void importAll(File excelFile,HttpServletRequest request)throws Exception;
    
    
}
