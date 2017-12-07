package cn.com.pansky.otp5.association.service.impl;

import java.io.File;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;

import cn.com.pansky.otp5.association.controller.ExcelToDB;
import cn.com.pansky.otp5.association.dao.IAnnualReportDao;
import cn.com.pansky.otp5.association.dao.IAptitudeRelateDao;
import cn.com.pansky.otp5.association.dao.IContactInfoDao;
import cn.com.pansky.otp5.association.dao.IEnterpriseBasicDao;
import cn.com.pansky.otp5.association.dao.IIndustryRelateDao;
import cn.com.pansky.otp5.association.dao.IKnowledgeRightDao;
import cn.com.pansky.otp5.association.dao.IProductionDao;
import cn.com.pansky.otp5.association.dao.IProductionFieldRelateDao;
import cn.com.pansky.otp5.association.dao.IShareholderDao;
import cn.com.pansky.otp5.association.dao.ITechRelateDao;
import cn.com.pansky.otp5.association.dao.po.AnnualReport;
import cn.com.pansky.otp5.association.dao.po.AptitudeRelate;
import cn.com.pansky.otp5.association.dao.po.ContactInfo;
import cn.com.pansky.otp5.association.dao.po.EnterpriseBasic;
import cn.com.pansky.otp5.association.dao.po.IndustryRelate;
import cn.com.pansky.otp5.association.dao.po.KnowledgeRight;
import cn.com.pansky.otp5.association.dao.po.Production;
import cn.com.pansky.otp5.association.dao.po.ProductionFieldRelate;
import cn.com.pansky.otp5.association.dao.po.Shareholder;
import cn.com.pansky.otp5.association.dao.po.TechRelate;
import cn.com.pansky.otp5.association.service.IImportService;
import cn.com.pansky.otp5.baseplatform.dao.IDictionaryDao;
import cn.com.pansky.otp5.baseplatform.dao.po.BasePO;
import cn.com.pansky.otp5.baseplatform.dao.po.Dictionary;
import cn.com.pansky.otp5.common.ConstantUtil;
import cn.com.pansky.otp5.common.DictionaryUtil;
import cn.com.pansky.otp5.common.IDGenerator;
import cn.com.pansky.otp5.common.UUIDGenerator;

@Service("importService")
public class ImportServiceImpl implements IImportService {
    

    public static Logger logger = Logger.getLogger(ImportServiceImpl.class);

    @Resource
    private IEnterpriseBasicDao enterpriseBasicDao;

    @Resource
    private IAptitudeRelateDao aptitudeRelateDao;

    @Resource
    private ITechRelateDao techRelateDao;
    

    @Resource
    private IIndustryRelateDao industryRelateDao;
    

    @Resource
    private IContactInfoDao contactInfoDao;

    @Resource
    private IAnnualReportDao annualReportDao;

    @Resource  
    private IDictionaryDao dictionaryDao;

    @Resource  
    private IShareholderDao shareholderDao;

    @Resource  
    private IKnowledgeRightDao knowledgeRightDao;

    @Resource  
    private IProductionDao productionDao;
    
    @Resource  
    private IProductionFieldRelateDao productionFieldRelateDao;
    
    

    @Override
    public void insertEnterpriseBasic(List<EnterpriseBasic> pos) {
        for (EnterpriseBasic po : pos) {
            enterpriseBasicDao.insert(po);
        }
    }

    @Override
    public void insertContactInfo(List<ContactInfo> pos) {
        for (ContactInfo po : pos) {
            contactInfoDao.insert(po);
        }
    }
    
    
    private String getDataType(int i){
        String dataType = "";
        if(i==0){
            dataType = "enterpriseBasic";
        }else if(i==1){
            dataType = "aptitude";
        }else if(i==2){
            dataType = "tech";
        }else if(i==3){
            dataType = "industry";
        }else if(i==4){
            dataType = "contact";
        }else if(i==5){
            dataType = "annualReport";
        }else if(i==6){
            dataType = "shareholder";
        }else if(i==7){
            dataType = "knowledgeRight";
        }else if(i==8){
            dataType = "production";
        }else if(i==9){
            dataType = "productionField";
        }
        return dataType;
    }

    @Override
    public void importAll(File excelFile,HttpServletRequest request) throws Exception {
        String format = request.getParameter("format");
        int len = 10;
        if("1".equals(format)){
            len = 9;
        }
        for(int i=0;i<len;i++){
            String dataType = getDataType(i);
            List<BasePO> pos = ExcelToDB.bulidBasePO(excelFile, dataType, request,i);
            insertData(pos, dataType,request);
        }
    }
    

    @Override
    public void insertData(List<BasePO> pos, String dataType,HttpServletRequest request) {
        if ("dictionary".equals(dataType)) {
            for (BasePO po : pos) {
                dictionaryDao.insert((Dictionary) po);
            }
        } else if ("enterpriseBasic".equals(dataType)) {
            int i = 1;
            for (BasePO po : pos) {
                i++;
                try{
                    //int m = 1/0;
                    enterpriseBasicDao.insert((EnterpriseBasic) po);
                }catch(Exception e){
                    e.printStackTrace();
                    EnterpriseBasic item = (EnterpriseBasic) po;
                    logger.error("==insertData==基础信息==>" + i + item.getCode() + "==" + item.getName() );
                }
            }
        } else if ("aptitude".equals(dataType)) {
            int i = 1;
            for (BasePO po : pos) {
                i++;
                try{
                    //int m = 1/0;
                    aptitudeRelateDao.insert((AptitudeRelate) po);
                }catch(Exception e){
                    e.printStackTrace();
                    AptitudeRelate item = (AptitudeRelate) po;
                    logger.error("==insertData==资质信息==>" + i + item.geteId() + "==" + item.getCode() );
                }
               
            }
        } else if ("tech".equals(dataType)) {
            int i = 1;
            for (BasePO po : pos) {
                i++;
                try{
                    //int m = 1/0;
                    techRelateDao.insert((TechRelate) po);
                }catch(Exception e){
//                    e.printStackTrace();
                    TechRelate item = (TechRelate) po;
                    logger.error("==insertData==技术领域信息==>" + i + item.geteId() + "==" + item.getCode() );
                }
            }
        } else if ("industry".equals(dataType)) {
            int i = 1;
            for (BasePO po : pos) { 
                i++;
                try{
                    //int m = 1/0;
                    industryRelateDao.insert((IndustryRelate) po); 
                }catch(Exception e){
//                    e.printStackTrace();
                    IndustryRelate item = (IndustryRelate) po;
                    logger.error("==insertData==行业领域信息==>" + i + item.geteId() + "==" + item.getCode() );
                }
            }
        } else if ("contact".equals(dataType)) {
            int i = 1;
            for (BasePO po : pos) {
                i++;
                try{
                    //int m = 1/0;
                    contactInfoDao.insert((ContactInfo) po);
                }catch(Exception e){
                    e.printStackTrace();
                    ContactInfo item = (ContactInfo) po;
                    logger.error("==insertData==联系人信息==>" + i + item.geteId() + "==" + item.getName() );
                }
            }
        
        } else if ("annualReport".equals(dataType)) {
            int i = 1;
            for (BasePO po : pos) {
                i++;
                try{
                    //int m = 1/0;
                    annualReportDao.insert((AnnualReport) po);
                }catch(Exception e){
                    e.printStackTrace();
                    AnnualReport item = (AnnualReport) po;
                    logger.error("==insertData==年报信息==>" + i + item.geteId() + "==" + item.getYear());
                }
            }
        }else if ("shareholder".equals(dataType)) {
            int i = 1;
            for (BasePO po : pos) {
                i++;
                try{
                    //int m = 1/0;
                    shareholderDao.insert((Shareholder) po);
                }catch(Exception e){
                    e.printStackTrace();
                    Shareholder item = (Shareholder) po;
                    logger.error("==insertData==股东信息==>" + i + item.geteId() + "==" + item.getName() );
                }
            }
        }else if ("knowledgeRight".equals(dataType)) {
            int i = 1;
            for (BasePO po : pos) {
                i++;
                try{
                    //int m = 1/0;
                    knowledgeRightDao.insert((KnowledgeRight) po);
                }catch(Exception e){
                    e.printStackTrace();
                    KnowledgeRight item = (KnowledgeRight) po;
                    logger.error("==insertData==知识产权信息==>" + i + item.geteId() + "==" + item.getName());
                }
            }
        }else if ("production".equals(dataType)) {

            int i = 1;
            for (BasePO po : pos) {
                i++;
                try{
                    //int m = 1/0;
                    Production p =  (Production)po;
                    productionDao.insert(p);
                    String format = request.getParameter("format");
                    if("1".equals(format)){
                        String fwlyNames = p.getFwlyNames();
                        if(StringUtils.isNotEmpty(fwlyNames)){
                            String[] fwlys = fwlyNames.split(",");
                            for(String fwly : fwlys){
                                ProductionFieldRelate pl = new ProductionFieldRelate();
                                pl.setId(IDGenerator.getUUID());
                                pl.setProductionId(p.getId());
                                pl.setFwlyCode( DictionaryUtil.getCodeByNameType(request, fwly, ConstantUtil.DIC_INFO_INDUSTRY));
                                productionFieldRelateDao.insert(pl);
                            }
                        }
                    }
                }catch(Exception e){
                    e.printStackTrace();
                    Production item = (Production) po;
                    logger.error("==insertData==产品信息==>" + i + item.geteId() + "==" + item.getName() );
                }
            }
        }else if ("productionField".equals(dataType)) {

            int i = 1;
            for (BasePO po : pos) {
                i++;
                try{
                    //int m = 1/0;
                    productionFieldRelateDao.insert((ProductionFieldRelate) po);
                }catch(Exception e){
                    e.printStackTrace();
                    ProductionFieldRelate item = (ProductionFieldRelate) po;
                    logger.error("==insertData==产品领域信息==>" + i + item.getProductionId() + "==" + item.getFwlyCode() );
                }
            }
        }
    }


}
