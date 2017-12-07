package cn.com.pansky.otp5.association.controller;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.text.ParseException;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFDataFormat;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.github.pagehelper.StringUtil;

import cn.com.pansky.otp5.association.controller.vo.EnterpriseBasicVO;
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
import cn.com.pansky.otp5.association.service.IEnterpriseBasicService;
import cn.com.pansky.otp5.common.ConstantUtil;
import cn.com.pansky.otp5.common.DateUtil;
import cn.com.pansky.otp5.common.DictionaryUtil;
import cn.com.pansky.otp5.common.ResultBean;
import cn.com.pansky.otp5.common.WebTools;

/**
 * @ClassName ExportController
 * @Description TODO(这里用一句话描述这个类的作用)
 * @author Administrator
 * @Date 2017年11月23日 上午11:19:25
 * @version 1.0.0
 */
@Controller
@RequestMapping("/export/")
public class ExportController {

    @Resource
    private IEnterpriseBasicService enterpriseBasicService;

    @Resource
    private IEnterpriseBasicDao enterpriseBasicDao;

    @Resource
    private IContactInfoDao contactInfoDao;

    @Resource
    private IAnnualReportDao annualReportDao;

    @Resource
    private IShareholderDao shareholderDao;

    @Resource
    private IAptitudeRelateDao aptitudeRelateDao;

    @Resource
    private ITechRelateDao techRelateDao;

    @Resource
    private IIndustryRelateDao industryRelateDao;

    @Resource
    private IKnowledgeRightDao knowledgeRightDao;
    
    @Resource
    private IProductionDao productionDao;
    
    @Resource
    private IProductionFieldRelateDao productionFieldRelateDao;
    
    

    @RequestMapping(value = "toExportData")
    public String toExportData(HttpServletRequest req, HttpServletResponse resp) {
        return "association/exportData";
    }
    
    
    
    private void buildExcel(HSSFSheet sheet, int i, HttpServletRequest request) throws ParseException{
        List<EnterpriseBasic> basics = getAllBasicInfo();
        if(i==0){
            buildHeader(sheet);
            int j = 1;
            for (EnterpriseBasic po : basics) { 
                buildBody(sheet, j, po, request);
                j++;
            }
        }else if(i==1){
            buildHeaderAptitude(sheet);
            int j = 1;
            List<AptitudeRelate> pos = aptitudeRelateDao.selectAll();
            for (AptitudeRelate po : pos) { 
                buildBodyAptitude(sheet, j, po, request, basics);
                j++;
            }
        }else if(i==2){
            buildHeaderTech(sheet);
            int j = 1;
            List<TechRelate> pos = techRelateDao.selectAll();
            for (TechRelate po : pos) { 
                buildBodyTech(sheet, j, po, request, basics);
                j++;
            }
        }else if(i==3){
            buildHeaderIndustry(sheet);
            int j = 1;
            List<IndustryRelate> pos = industryRelateDao.selectAll();
            for (IndustryRelate po : pos) { 
                buildBodyIndustry(sheet, j, po, request, basics);
                j++;
            }
        }else if(i==4){
            buildHeaderContact(sheet);
            int j = 1;
            List<ContactInfo> pos = contactInfoDao.selectAll();
            for (ContactInfo po : pos) { 
                buildBodyContact(sheet, j, po, request, basics);
                j++;
            }
        }else if(i==5){
            buildHeaderAnnual(sheet);
            int j = 1;
            List<AnnualReport> pos = annualReportDao.selectAll();
            for (AnnualReport po : pos) { 
                buildBodyAnnual(sheet, j, po, request, basics);
                j++;
            }
        }else if(i==6){
            buildHeaderShareholder(sheet);
            int j = 1;
            List<Shareholder> pos = shareholderDao.selectAll();
            for (Shareholder po : pos) { 
                buildBodyShareholder(sheet, j, po, request, basics);
                j++;
            }
        }else if(i==7){
            buildHeaderKnowledgeRight(sheet);
            int j = 1;
            List<KnowledgeRight> pos = knowledgeRightDao.selectAll();
            for (KnowledgeRight po : pos) { 
                buildBodyKnowledgeRight(sheet, j, po, request, basics);
                j++;
            }
        }else if(i==8){
            String format = request.getParameter("format");
            buildHeaderProduction(sheet,format);
            int j = 1;
            List<Production> pos = productionDao.selectAll();
            for (Production po : pos) { 
                buildBodyProduction(sheet, j, po, request, basics);
                j++;
            }
        }else if(i==9){
            buildHeaderProductionField(sheet);
            int j = 1;
            List<ProductionFieldRelate> pos = productionFieldRelateDao.selectAll();
            for (ProductionFieldRelate po : pos) { 
                buildBodyProductionField(sheet, j, po, request, basics);
                j++;
            }
        }
    }

    private void buildExcelSearchBasicList(HSSFSheet sheet, HttpServletRequest request, Map params) throws ParseException{
        //List<EnterpriseBasic> basics = getAllBasicInfo();
        List<EnterpriseBasic> basics = enterpriseBasicDao.findByParams(params);
        
        buildHeaderSearchBasicList(sheet);
        int j = 1;
        for (EnterpriseBasic po : basics) { 
            buildBodySearchBasicList(sheet, j, po, request);
            j++;
        }
    }
    

    private void buildHeaderSearchBasicList(HSSFSheet sheet) {
        // 创建HSSFRow对象
        HSSFRow row = sheet.createRow(0);
        HSSFCell cell0 = row.createCell(0);
        cell0.setCellValue("企业名称");
        HSSFCell cell1 = row.createCell(1);
        cell1.setCellValue("统一社会信用码");
        HSSFCell cell2 = row.createCell(2);
        cell2.setCellValue("是否上市");
        HSSFCell cell3 = row.createCell(3);
        cell3.setCellValue("入会时间");
        HSSFCell cell4 = row.createCell(4);
        cell4.setCellValue("信息来源");
        HSSFCell cell5 = row.createCell(5);
        cell5.setCellValue("是否会员");
        HSSFCell cell6 = row.createCell(6);
        cell6.setCellValue("会员等级");
        HSSFCell cell7 = row.createCell(7);
        cell7.setCellValue("技术领域");
        HSSFCell cell8 = row.createCell(8);
        cell8.setCellValue("行业领域");
        HSSFCell cell9 = row.createCell(9);
        cell9.setCellValue("通讯地址");
        HSSFCell cell10 = row.createCell(10);
        cell10.setCellValue("注册资金（元）");
        HSSFCell cell11 = row.createCell(11);
        cell11.setCellValue("年收入总额（元）");
        HSSFCell cell12 = row.createCell(12);
        cell12.setCellValue("研发费用总额（元）");
        HSSFCell cell13 = row.createCell(13);
        cell13.setCellValue("当年（月平均）职工总数");
    }


    private void buildHeader(HSSFSheet sheet) {
        // 创建HSSFRow对象
        HSSFRow row = sheet.createRow(0);
        HSSFCell cell0 = row.createCell(0);
        cell0.setCellValue("ID");
        HSSFCell cell1 = row.createCell(1);
        cell1.setCellValue("企业名称【必填】");
        HSSFCell cell2 = row.createCell(2);
        cell2.setCellValue("企业网址【非必填】");
        HSSFCell cell3 = row.createCell(3);
        cell3.setCellValue("统一社会信用码【必填】");
        HSSFCell cell4 = row.createCell(4);
        cell4.setCellValue("是否上市【必填 : 是/否 二选一】");
        HSSFCell cell5 = row.createCell(5);
        cell5.setCellValue("是否高新【必填 : 是/否 二选一】");
        HSSFCell cell6 = row.createCell(6);
        cell6.setCellValue("高新通过时间【非必填】");
        HSSFCell cell7 = row.createCell(7);
        cell7.setCellValue("是否会员【必填 : 是/否 二选一】");
        HSSFCell cell8 = row.createCell(8);
        cell8.setCellValue("初次入会时间【非必填: 格式:2017-09-22】");
        HSSFCell cell9 = row.createCell(9);
        cell9.setCellValue("会员等级【非必填: 字典信息】");
        HSSFCell cell10 = row.createCell(10);
        cell10.setCellValue("注册资金【非必填,格式(正整数):12345】");
        HSSFCell cell11 = row.createCell(11);
        cell11.setCellValue("员工规模【非必填,格式(正整数):12345】");
        HSSFCell cell12 = row.createCell(12);
        cell12.setCellValue("企业地址(省市县)【非必填，填写格式: 北京市房山区】");
        HSSFCell cell13 = row.createCell(13);
        cell13.setCellValue("企业详细地址【非必填】");
        HSSFCell cell14 = row.createCell(14);
        cell14.setCellValue("邮政编码【非必填】");
        HSSFCell cell15 = row.createCell(15);
        cell15.setCellValue("企业注册地址(省市县)【非必填，填写格式: 北京市房山区】");
        HSSFCell cell16 = row.createCell(16);
        cell16.setCellValue("企业详细注册地址【非必填】");
        HSSFCell cell17 = row.createCell(17);
        cell17.setCellValue("状态(是否删除)【必填 : 正常/删除 二选一】【必填 : 正常/删除 二选一】");
        HSSFCell cell18 = row.createCell(18);
        cell18.setCellValue("当年（月平均）职工总数【非必填,格式(正整数):12345】");
        HSSFCell cell19 = row.createCell(19);
        cell19.setCellValue("研发人员总数【非必填,格式(正整数):12345】");
        HSSFCell cell20 = row.createCell(20);
        cell20.setCellValue("管理人员总数【非必填,格式(正整数):12345】");
        HSSFCell cell21 = row.createCell(21);
        cell21.setCellValue("大专及本科人数【非必填,格式(正整数):12345】");
        HSSFCell cell22 = row.createCell(22);
        cell22.setCellValue("硕士学历人数【非必填,格式(正整数):12345】");
        HSSFCell cell23 = row.createCell(23);
        cell23.setCellValue("博士学历人数【非必填,格式(正整数):12345】");
        HSSFCell cell24 = row.createCell(24);
        cell24.setCellValue("创建人【非必填】");
        HSSFCell cell25 = row.createCell(25);
        cell25.setCellValue("创建时间【非必填】");
        HSSFCell cell26 = row.createCell(26);
        cell26.setCellValue("最后修改人【非必填】");
        HSSFCell cell27 = row.createCell(27);
        cell27.setCellValue("最后修改时间【非必填】");
        HSSFCell cell28 = row.createCell(28);
        cell28.setCellValue("信息来源【非必填: 字典信息】");
        HSSFCell cell29 = row.createCell(29);
        cell29.setCellValue("简介【非必填】");
        HSSFCell cell30 = row.createCell(30);
        cell30.setCellValue("经度【非必填】");
        HSSFCell cell31 = row.createCell(31);
        cell31.setCellValue("维度【非必填】");
        HSSFCell cell32 = row.createCell(32);
        cell32.setCellValue("企业信息所属人标识【必填】");
        HSSFCell cell33 = row.createCell(33);
        cell33.setCellValue("负责人姓名");
        HSSFCell cell34 = row.createCell(34);
        cell34.setCellValue("负责人职务");
        HSSFCell cell35 = row.createCell(35);
        cell35.setCellValue("负责人手机");
        HSSFCell cell36 = row.createCell(36);
        cell36.setCellValue("负责人固话");
        HSSFCell cell37 = row.createCell(37);
        cell37.setCellValue("负责人邮箱");
        HSSFCell cell38 = row.createCell(38);
        cell38.setCellValue("联系人姓名");
        HSSFCell cell39 = row.createCell(39);
        cell39.setCellValue("联系人职务");
        HSSFCell cell40 = row.createCell(40);
        cell40.setCellValue("联系人手机");
        HSSFCell cell41 = row.createCell(41);
        cell41.setCellValue("联系人固话");
        HSSFCell cell42 = row.createCell(42);
        cell42.setCellValue("联系人邮箱");
    }

   
    private void buildHeaderAptitude(HSSFSheet sheet) {
        // 创建HSSFRow对象
        HSSFRow row = sheet.createRow(0);
        HSSFCell cell0 = row.createCell(0);
        cell0.setCellValue("ID");
        HSSFCell cell1 = row.createCell(1);
        cell1.setCellValue("统一社会信用码【必填】");
        HSSFCell cell2 = row.createCell(2);
        cell2.setCellValue("企业资质【必填: 字典信息】");
        HSSFCell cell3 = row.createCell(3);
        cell3.setCellValue("通过时间【必填: 格式:2017-11-09】");

    }

    private void buildHeaderTech(HSSFSheet sheet) {
        // 创建HSSFRow对象
        HSSFRow row = sheet.createRow(0);
        HSSFCell cell0 = row.createCell(0);
        cell0.setCellValue("ID");
        HSSFCell cell1 = row.createCell(1);
        cell1.setCellValue("统一社会信用码【必填】");
        HSSFCell cell2 = row.createCell(2);
        cell2.setCellValue("技术领域【必填: 字典信息】");
    }
    

    private void buildHeaderIndustry(HSSFSheet sheet) {
        // 创建HSSFRow对象
        HSSFRow row = sheet.createRow(0);
        HSSFCell cell0 = row.createCell(0);
        cell0.setCellValue("ID");
        HSSFCell cell1 = row.createCell(1);
        cell1.setCellValue("统一社会信用码【必填】");
        HSSFCell cell2 = row.createCell(2);
        cell2.setCellValue("行业领域【必填: 字典信息】");
    }
    

    private void buildHeaderContact(HSSFSheet sheet) {
        // 创建HSSFRow对象
        HSSFRow row = sheet.createRow(0);
        HSSFCell cell0 = row.createCell(0);
        cell0.setCellValue("ID");
        HSSFCell cell1 = row.createCell(1);
        cell1.setCellValue("统一社会信用码【必填】");
        HSSFCell cell2 = row.createCell(2);
        cell2.setCellValue("姓名【必填】");
        HSSFCell cell3 = row.createCell(3);
        cell3.setCellValue("职务【非必填: 字典信息】");
        HSSFCell cell4 = row.createCell(4);
        cell4.setCellValue("固话1【非必填】【非必填,格式: 010-87654321】");
        HSSFCell cell5 = row.createCell(5);
        cell5.setCellValue("固话2【非必填】【非必填,格式: 010-87654321】");
        HSSFCell cell6 = row.createCell(6);
        cell6.setCellValue("手机【非必填】【非必填,格式: 13512345678】");
        HSSFCell cell7 = row.createCell(7);
        cell7.setCellValue("邮箱【非必填】【非必填,格式: wyn409@126.com】");
        HSSFCell cell8 = row.createCell(8);
        cell8.setCellValue("状态(是否删除)【必填 : 正常/删除 二选一】");
        HSSFCell cell9 = row.createCell(9);
        cell9.setCellValue("创建人【非必填】");
        HSSFCell cell10 = row.createCell(10);
        cell10.setCellValue("创建时间【非必填】");
        HSSFCell cell11 = row.createCell(11);
        cell11.setCellValue("最后修改人【非必填】");
        HSSFCell cell12 = row.createCell(12);
        cell12.setCellValue("最后修改时间【非必填】");
    }



    private void buildHeaderAnnual(HSSFSheet sheet) {
        // 创建HSSFRow对象
        HSSFRow row = sheet.createRow(0);
        HSSFCell cell0 = row.createCell(0);
        cell0.setCellValue("ID");
        HSSFCell cell1 = row.createCell(1);
        cell1.setCellValue("统一社会信用码【必填】");
        HSSFCell cell2 = row.createCell(2);
        cell2.setCellValue("收入总额【非必填,格式(正数,最多两位小数): 123.34】");
        HSSFCell cell3 = row.createCell(3);
        cell3.setCellValue("资产总额【非必填,格式(正数,最多两位小数): 123.34】");
        HSSFCell cell4 = row.createCell(4);
        cell4.setCellValue("利润总额【非必填,格式(正数,最多两位小数): 123.34】");
        HSSFCell cell5 = row.createCell(5);
        cell5.setCellValue("净利润【非必填,格式(正数,最多两位小数): 123.34】");
        HSSFCell cell6 = row.createCell(6);
        cell6.setCellValue("研发费用总额【非必填,格式(正数,最多两位小数): 123.34】");
        HSSFCell cell7 = row.createCell(7);
        cell7.setCellValue("研发费用占收入比例【非必填,格式(正数,最多两位小数): 123.34】");
        HSSFCell cell8 = row.createCell(8);
        cell8.setCellValue("年度【必填,格式 : 2017】");
        HSSFCell cell9 = row.createCell(9);
        cell9.setCellValue("状态(是否删除)【必填 : 正常/删除 二选一】");
        HSSFCell cell10 = row.createCell(10);
        cell10.setCellValue("创建人【非必填】");
        HSSFCell cell11 = row.createCell(11);
        cell11.setCellValue("创建时间【非必填】");
        HSSFCell cell12 = row.createCell(12);
        cell12.setCellValue("最后修改人【非必填】");
        HSSFCell cell13 = row.createCell(13);
        cell13.setCellValue("最后修改时间【非必填】");
    }
    



    private void buildHeaderShareholder(HSSFSheet sheet) {
        // 创建HSSFRow对象
        HSSFRow row = sheet.createRow(0);
        HSSFCell cell0 = row.createCell(0);
        cell0.setCellValue("ID");
        HSSFCell cell1 = row.createCell(1);
        cell1.setCellValue("统一社会信用码【必填 】");
        HSSFCell cell2 = row.createCell(2);
        cell2.setCellValue("名称【必填 】");
        HSSFCell cell3 = row.createCell(3);
        cell3.setCellValue("持股数量【非必填 】");
        HSSFCell cell4 = row.createCell(4);
        cell4.setCellValue("持股比例【非必填 】");
        HSSFCell cell5 = row.createCell(5);
        cell5.setCellValue("状态(是否删除)【必填 : 正常/删除 二选一】");
        HSSFCell cell6 = row.createCell(6);
        cell6.setCellValue("创建人【非必填】");
        HSSFCell cell7 = row.createCell(7);
        cell7.setCellValue("创建时间【非必填】");
        HSSFCell cell8 = row.createCell(8);
        cell8.setCellValue("最后修改人【非必填】");
        HSSFCell cell9 = row.createCell(9);
        cell9.setCellValue("最后修改时间【非必填】");
    }


    private void buildHeaderKnowledgeRight(HSSFSheet sheet) {
        // 创建HSSFRow对象
        HSSFRow row = sheet.createRow(0);
        HSSFCell cell0 = row.createCell(0);
        cell0.setCellValue("ID");
        HSSFCell cell1 = row.createCell(1);
        cell1.setCellValue("统一社会信用码【必填 】");
        HSSFCell cell2 = row.createCell(2);
        cell2.setCellValue("名称【必填 】");
        HSSFCell cell3 = row.createCell(3);
        cell3.setCellValue("受理号/登记号/专利号【必填 】");
        HSSFCell cell4 = row.createCell(4);
        cell4.setCellValue("通过时间【必填 】");
        HSSFCell cell5 = row.createCell(5);
        cell5.setCellValue("状态(是否删除)【必填 : 正常/删除 二选一】");
        HSSFCell cell6 = row.createCell(6);
        cell6.setCellValue("知识产权类型【必填 :字典信息】");
        HSSFCell cell7 = row.createCell(7);
        cell7.setCellValue("创建人【非必填】");
        HSSFCell cell8 = row.createCell(8);
        cell8.setCellValue("创建时间【非必填】");
        HSSFCell cell9 = row.createCell(9);
        cell9.setCellValue("最后修改人【非必填】");
        HSSFCell cell10 = row.createCell(10);
        cell10.setCellValue("最后修改时间【非必填】");
    }
    
    
    
    private void buildHeaderProduction(HSSFSheet sheet,String format) {
        // 创建HSSFRow对象
        HSSFRow row = sheet.createRow(0);
        HSSFCell cell0 = row.createCell(0);
        cell0.setCellValue("ID");
        HSSFCell cell1 = row.createCell(1);
        cell1.setCellValue("统一社会信用码【必填 】");
        HSSFCell cell2 = row.createCell(2);
        cell2.setCellValue("产品名称【必填 】");
        HSSFCell cell3 = row.createCell(3);
        cell3.setCellValue("销售收入【非必填 】");
        HSSFCell cell4 = row.createCell(4);
        cell4.setCellValue("状态(是否删除)【必填 : 正常/删除 二选一】");
        if("1".equals(format)){
            HSSFCell cell5 = row.createCell(5);
            cell5.setCellValue("服务领域【非必填:字典信息[同行业领域]，格式:领域A,领域B 】");
            HSSFCell cell6 = row.createCell(6);
            cell6.setCellValue("创建人【非必填】");
            HSSFCell cell7 = row.createCell(7);
            cell7.setCellValue("创建时间【非必填】");
            HSSFCell cell8 = row.createCell(8);
            cell8.setCellValue("最后修改人【非必填】");
            HSSFCell cell9 = row.createCell(9);
            cell9.setCellValue("最后修改时间【非必填】");
        }else{
            HSSFCell cell5 = row.createCell(5);
            cell5.setCellValue("创建人【非必填】");
            HSSFCell cell6 = row.createCell(6);
            cell6.setCellValue("创建时间【非必填】");
            HSSFCell cell7 = row.createCell(7);
            cell7.setCellValue("最后修改人【非必填】");
            HSSFCell cell8 = row.createCell(8);
            cell8.setCellValue("最后修改时间【非必填】");
        }
    }
    

    
    
    private void buildHeaderProductionField(HSSFSheet sheet) {
        // 创建HSSFRow对象
        HSSFRow row = sheet.createRow(0);
        HSSFCell cell0 = row.createCell(0);
        cell0.setCellValue("ID");
        HSSFCell cell1 = row.createCell(1);
        cell1.setCellValue("产品ID");
        HSSFCell cell2 = row.createCell(2);
        cell2.setCellValue("服务领域");
    }
    
    private void buildBodyAll(HSSFSheet sheet, int i, EnterpriseBasic vo, HttpServletRequest request)
            throws ParseException {
        if(i==0){
            buildBody(sheet, i, vo, request);
        }else if(i==1){
            buildHeaderAptitude(sheet);
        }else if(i==2){
            buildHeaderTech(sheet);
        }else if(i==3){
            buildHeaderIndustry(sheet);
        }else if(i==4){
            buildHeaderContact(sheet);
        }else if(i==5){
            buildHeaderAnnual(sheet);
        }else if(i==6){
            buildHeaderShareholder(sheet);
        }else if(i==7){
            buildHeaderKnowledgeRight(sheet);
        }else if(i==8){
            String format = request.getParameter("format");
            buildHeaderProduction(sheet,format);
        }else if(i==9){
            buildHeaderProductionField(sheet);
        }
    }
    
    
    private void buildBodySearchBasicList(HSSFSheet sheet, int i, EnterpriseBasic po, HttpServletRequest request)
            throws ParseException {
        HSSFRow row = sheet.createRow(i);
        HSSFCell cell0 = row.createCell(0);
        cell0.setCellValue(po.getName());
        HSSFCell cell1 = row.createCell(1);
        cell1.setCellValue(po.getCode());
        HSSFCell cell2 = row.createCell(2);
        if ("0".equals(po.getIsPublic())) {
            cell2.setCellValue("否");
        } else if ("1".equals(po.getIsPublic())) {
            cell2.setCellValue("是");
        } else {
            cell2.setCellValue(po.getIsPublic());
        }
        HSSFCell cell3 = row.createCell(3);
        if(po.getFirstTime()!=null){
            cell3.setCellValue(DateUtil.parseDateToStr(po.getFirstTime()));
        }
        HSSFCell cell4 = row.createCell(4);
        if(StringUtil.isNotEmpty(po.getInfoSources())){
            cell4.setCellValue(DictionaryUtil.getNameByCodeType(request, po.getInfoSources(), ConstantUtil.DIC_INFO_SOURCES));
        }
         HSSFCell cell5 = row.createCell(5);
        if ("0".equals(po.getIsMember())) {
            cell5.setCellValue("否");
        } else if ("1".equals(po.getIsMember())) {
            cell5.setCellValue("是");
        } else {
            cell5.setCellValue(po.getIsMember());
        }
        HSSFCell cell6 = row.createCell(6);
        if(StringUtil.isNotEmpty(po.getHyLevel())){
            cell6.setCellValue(DictionaryUtil.getNameByCodeType(request, po.getHyLevel(), ConstantUtil.DIC_HY_LEVEL));
        }
        HSSFCell cell7 = row.createCell(7);
        cell7.setCellValue(po.getTechNames());
        HSSFCell cell8 = row.createCell(8);
        cell8.setCellValue(po.getIndustryNames());
        HSSFCell cell9 = row.createCell(9);
        if(StringUtil.isNotEmpty(po.getAddress())){
            cell9.setCellValue(DictionaryUtil.getRegionNameByCode(po.getAddress(), request) + po.getAddressDetail());
        }else{
            if(StringUtils.isNotBlank(po.getAddressDetail())){
                cell9.setCellValue(po.getAddressDetail());
            }
        }
        HSSFCell cell10 = row.createCell(10);
        if (po.getRegisteredCapital() != null) {
            cell10.setCellValue(po.getRegisteredCapital());
        }
        HSSFCell cell11 = row.createCell(11);
        if (po.getTotalIncome() != null) {
            cell11.setCellValue(po.getTotalIncome());
        }
        HSSFCell cell12 = row.createCell(12);
        if (po.getDevelopExpenses() != null) {
            cell12.setCellValue(po.getDevelopExpenses());
        }
        HSSFCell cell13 = row.createCell(13);
        if (po.getAvgNum() != null) {
            cell13.setCellValue(po.getAvgNum());
        }
    }

    

    
    private void buildBody(HSSFSheet sheet, int i, EnterpriseBasic vo, HttpServletRequest request)
            throws ParseException {
        
        HSSFRow row = sheet.createRow(i);

        HSSFCell cell0 = row.createCell(0);
        cell0.setCellValue(vo.getId());
        HSSFCell cell1 = row.createCell(1);
        cell1.setCellValue(vo.getName());
        HSSFCell cell2 = row.createCell(2);
        cell2.setCellValue(vo.getUrl());
        HSSFCell cell3 = row.createCell(3);
        cell3.setCellValue(vo.getCode());
        HSSFCell cell4 = row.createCell(4);
        if ("0".equals(vo.getIsPublic())) {
            cell4.setCellValue("否");
        } else if ("1".equals(vo.getIsPublic())) {
            cell4.setCellValue("是");
        } else {
            cell4.setCellValue(vo.getIsPublic());
        }
        HSSFCell cell5 = row.createCell(5);
        if ("0".equals(vo.getIsHigh())) {
            cell5.setCellValue("否");
        } else if ("1".equals(vo.getIsHigh())) {
            cell5.setCellValue("是");
        } else {
            cell5.setCellValue(vo.getIsHigh());
        }
        HSSFCell cell6 = row.createCell(6);
        if (vo.getPassHighDate() != null) {
            cell6.setCellValue(DateUtil.parseDateToStr(vo.getPassHighDate()));
        }
        HSSFCell cell7 = row.createCell(7);
        if ("0".equals(vo.getIsMember())) {
            cell7.setCellValue("否");
        } else if ("1".equals(vo.getIsMember())) {
            cell7.setCellValue("是");
        } else {
            cell7.setCellValue(vo.getIsMember());
        }
        HSSFCell cell8 = row.createCell(8);
        if (vo.getFirstTime() != null) {
            cell8.setCellValue(DateUtil.parseDateToStr(vo.getFirstTime()));
        }
        HSSFCell cell9 = row.createCell(9);
        cell9.setCellValue(DictionaryUtil.getNameByCode(vo.getHyLevel(), request));
        HSSFCell cell10 = row.createCell(10);
        if (vo.getRegisteredCapital() != null) {
            cell10.setCellValue(vo.getRegisteredCapital());
        }
        HSSFCell cell11 = row.createCell(11);
        if (vo.getEmployeeNum() != null) {
            cell11.setCellValue(vo.getEmployeeNum());
        }
        HSSFCell cell12 = row.createCell(12);
        if (StringUtils.isNotEmpty(vo.getAddress())) {
            cell12.setCellValue(DictionaryUtil.getRegionNameByCode(vo.getAddress(), request));
        }
        HSSFCell cell13 = row.createCell(13);
        cell13.setCellValue(vo.getAddressDetail());
        HSSFCell cell14 = row.createCell(14);
        cell14.setCellValue(vo.getPostalCode());
        HSSFCell cell15 = row.createCell(15);
        // cell15.setCellValue(vo.getRegisterAddress());
        if (StringUtils.isNotEmpty(vo.getRegisterAddress())) {
            cell15.setCellValue(DictionaryUtil.getRegionNameByCode(vo.getRegisterAddress(), request));
        }
        HSSFCell cell16 = row.createCell(16);
        cell16.setCellValue(vo.getRegisterAddressDetail());
        HSSFCell cell17 = row.createCell(17);
        if ("1".equals(vo.getEnable())) {
            cell17.setCellValue("正常");
        } else if ("2".equals(vo.getEnable())) {
            cell17.setCellValue("删除");
        } else {
            cell17.setCellValue(vo.getEnable());
        }
        // cell17.setCellValue(vo.getEnable());

        HSSFCell cell18 = row.createCell(18);
        if (vo.getAvgNum() != null) {
            cell18.setCellValue(vo.getAvgNum());
        }
        HSSFCell cell19 = row.createCell(19);
        if (vo.getYfNum() != null) {
            cell19.setCellValue(vo.getYfNum());
        }
        HSSFCell cell20 = row.createCell(20);
        if (vo.getYfNum() != null) {
            cell20.setCellValue(vo.getGlNum());
        }
        HSSFCell cell21 = row.createCell(21);
        if (vo.getZbNum() != null) {
            cell21.setCellValue(vo.getZbNum());
        }
        HSSFCell cell22 = row.createCell(22);
        if (vo.getSsNum() != null) {
            cell22.setCellValue(vo.getSsNum());
        }
        HSSFCell cell23 = row.createCell(23);
        if (vo.getBsNum() != null) {
            cell23.setCellValue(vo.getBsNum());
        }
        HSSFCell cell24 = row.createCell(24);
        cell24.setCellValue(vo.getCreationUser());
        HSSFCell cell25 = row.createCell(25);
        if (vo.getCreationTime() != null) {
//            System.err.println("基础信息========>>>>>" + vo.getCreationTime());
            cell25.setCellValue(DateUtil.parseDateToStr(vo.getCreationTime(), DateUtil.FORMAT_2));
        }
        HSSFCell cell26 = row.createCell(26);
        cell26.setCellValue(vo.getLastModifyUser());
        HSSFCell cell27 = row.createCell(27);
        if (vo.getLastModifyTime() != null) {
            cell27.setCellValue(DateUtil.parseDateToStr(vo.getLastModifyTime(), DateUtil.FORMAT_2));
        }
        HSSFCell cell28 = row.createCell(28);
        cell28.setCellValue(DictionaryUtil.getNameByCode(vo.getInfoSources(), request));
        HSSFCell cell29 = row.createCell(29);
        cell29.setCellValue(vo.getRemark());
        HSSFCell cell30 = row.createCell(30);
        cell30.setCellValue(vo.getLongitude());
        HSSFCell cell31 = row.createCell(31);
        cell31.setCellValue(vo.getDimension());
        HSSFCell cell32 = row.createCell(32);
        cell32.setCellValue(vo.getOwner());
        HSSFCell cell33 = row.createCell(33);
      /*  HSSFCellStyle cellStyle = sheet.getWorkbook().createCellStyle(); 
        HSSFDataFormat format = sheet.getWorkbook().createDataFormat(); 
        cellStyle.setDataFormat(format.getFormat("@")); 
        cell33.setCellStyle(cellStyle);*/
        cell33.setCellValue(vo.getFzrName());
        HSSFCell cell34 = row.createCell(34);
        cell34.setCellValue(vo.getFzrJob());
        HSSFCell cell35 = row.createCell(35);
        cell35.setCellValue(vo.getFzrPhone());
        HSSFCell cell36 = row.createCell(36);
        cell36.setCellValue(vo.getFzrTel());
        HSSFCell cell37 = row.createCell(37);
        cell37.setCellValue(vo.getFzrEmail());
        HSSFCell cell38 = row.createCell(38);
        cell38.setCellValue(vo.getLxrName());
        HSSFCell cell39 = row.createCell(39);
        cell39.setCellValue(vo.getLxrJob());
        HSSFCell cell40 = row.createCell(40);
        cell40.setCellValue(vo.getLxrPhone());
        HSSFCell cell41 = row.createCell(41);
        cell41.setCellValue(vo.getLxrTel());
        HSSFCell cell42 = row.createCell(42);
        cell42.setCellValue(vo.getLxrEmail());
        
        /*
         * HSSFCell cell29 = row.createCell(29); cell29.setCellValue(vo.getAptitude_names()); HSSFCell cell30 =
         * row.createCell(30); cell30.setCellValue(vo.getAptitude_passTimes());
         */
    }

    


    private void buildBodyAptitude(HSSFSheet sheet, int i, AptitudeRelate po, HttpServletRequest request,
            List<EnterpriseBasic> basics) throws ParseException {
        HSSFRow row = sheet.createRow(i);
        HSSFCell cell0 = row.createCell(0);
        cell0.setCellValue(po.getId());
        HSSFCell cell1 = row.createCell(1);
        cell1.setCellValue(getCodeByEId(basics, po.geteId()));
        HSSFCell cell2 = row.createCell(2);
        cell2.setCellValue(DictionaryUtil.getNameByCodeType(request, po.getCode(), ConstantUtil.DIC_INFO_APTITUDE));
        HSSFCell cell3 = row.createCell(3);
        if (po.getPassTime() != null) {
            cell3.setCellValue(DateUtil.parseDateToStr(po.getPassTime()));
        }
    }

    private void buildBodyTech(HSSFSheet sheet, int i, TechRelate po, HttpServletRequest request,
            List<EnterpriseBasic> basics) throws ParseException {
        HSSFRow row = sheet.createRow(i);
        HSSFCell cell0 = row.createCell(0);
        cell0.setCellValue(po.getId());
        HSSFCell cell1 = row.createCell(1);
        cell1.setCellValue(getCodeByEId(basics, po.geteId()));
        HSSFCell cell2 = row.createCell(2);
        cell2.setCellValue(DictionaryUtil.getNameByCodeType(request, po.getCode(), ConstantUtil.DIC_INFO_TECH));
    }
    
    

    private void buildBodyIndustry(HSSFSheet sheet, int i, IndustryRelate po, HttpServletRequest request,
            List<EnterpriseBasic> basics) throws ParseException {
        HSSFRow row = sheet.createRow(i);
        HSSFCell cell0 = row.createCell(0);
        cell0.setCellValue(po.getId());
        HSSFCell cell1 = row.createCell(1);
        cell1.setCellValue(getCodeByEId(basics, po.geteId()));
        HSSFCell cell2 = row.createCell(2);
        cell2.setCellValue(DictionaryUtil.getNameByCodeType(request, po.getCode(), ConstantUtil.DIC_INFO_INDUSTRY));
    }


    private void buildBodyContact(HSSFSheet sheet, int i, ContactInfo po, HttpServletRequest request,
            List<EnterpriseBasic> basics) throws ParseException {
        // 创建HSSFRow对象
        HSSFRow row = sheet.createRow(i);
        HSSFCell cell0 = row.createCell(0);
        cell0.setCellValue(po.getId());
        HSSFCell cell1 = row.createCell(1);
        cell1.setCellValue(getCodeByEId(basics, po.geteId()));
        HSSFCell cell2 = row.createCell(2);
        cell2.setCellValue(po.getName());
        HSSFCell cell3 = row.createCell(3);
        if(StringUtils.isNotEmpty(po.getJob())){
            cell3.setCellValue(DictionaryUtil.getNameByCodeType(request, po.getJob(), ConstantUtil.DIC_INFO_CONTACTJOB));
        }
        HSSFCell cell4 = row.createCell(4);
        cell4.setCellValue(po.getTel_1());
        HSSFCell cell5 = row.createCell(5);
        cell5.setCellValue(po.getTel_2());
        HSSFCell cell6 = row.createCell(6);
        cell6.setCellValue(po.getPhone());
        HSSFCell cell7 = row.createCell(7);
        cell7.setCellValue(po.getEmail());
        HSSFCell cell8 = row.createCell(8);
        if ("1".equals(po.getEnable())) {
            cell8.setCellValue("正常");
        } else if ("2".equals(po.getEnable())) {
            cell8.setCellValue("删除");
        } else {
            cell8.setCellValue(po.getEnable());
        }
        HSSFCell cell9 = row.createCell(9);
        cell9.setCellValue(po.getCreationUser());
        HSSFCell cell10 = row.createCell(10);
        if (po.getCreationTime() != null) {
            cell10.setCellValue(DateUtil.parseDateToStr(po.getCreationTime(), DateUtil.FORMAT_2));
        }
        HSSFCell cell11 = row.createCell(11);
        cell11.setCellValue(po.getLastModifyUser());
        HSSFCell cell12 = row.createCell(12);
        if (po.getLastModifyTime() != null) {
            cell12.setCellValue(DateUtil.parseDateToStr(po.getLastModifyTime(), DateUtil.FORMAT_2));
        }
    }



    private void buildBodyAnnual(HSSFSheet sheet, int i, AnnualReport po, HttpServletRequest request,
            List<EnterpriseBasic> basics) throws ParseException {
        // 创建HSSFRow对象
        HSSFRow row = sheet.createRow(i);
        HSSFCell cell0 = row.createCell(0);
        cell0.setCellValue(po.getId());
        HSSFCell cell1 = row.createCell(1);
        cell1.setCellValue(getCodeByEId(basics, po.geteId()));
        HSSFCell cell2 = row.createCell(2);
        if(po.getTotalIncome()!=null){
            cell2.setCellValue(po.getTotalIncome());
        }
        HSSFCell cell3 = row.createCell(3);
        if(po.getTotalAssets()!=null){
            cell3.setCellValue(po.getTotalAssets());
        }
        HSSFCell cell4 = row.createCell(4);
        if(po.getTotalProfit()!=null){
            cell4.setCellValue(po.getTotalProfit());
        }
        HSSFCell cell5 = row.createCell(5);
        if(po.getNetProfit()!=null){
            cell5.setCellValue(po.getNetProfit());
        }
        HSSFCell cell6 = row.createCell(6);
        if(po.getDevelopExpenses()!=null){
            cell6.setCellValue(po.getDevelopExpenses());
        }
        HSSFCell cell7 = row.createCell(7);
        if(po.getScale()!=null){
            cell7.setCellValue(po.getScale());
        }
        HSSFCell cell8 = row.createCell(8);
        if(po.getYear()!=null){
            cell8.setCellValue(po.getYear());
        }
        HSSFCell cell9 = row.createCell(9);
        if ("1".equals(po.getEnable())) {
            cell9.setCellValue("正常");
        } else if ("2".equals(po.getEnable())) {
            cell9.setCellValue("删除");
        } else {
            cell9.setCellValue(po.getEnable());

        }
        HSSFCell cell10 = row.createCell(10);
        cell10.setCellValue(po.getCreationUser());
        HSSFCell cell11 = row.createCell(11);
        if (po.getCreationTime() != null) {
            cell11.setCellValue(DateUtil.parseDateToStr(po.getCreationTime(), DateUtil.FORMAT_2));
        }
        HSSFCell cell12 = row.createCell(12);
        cell12.setCellValue(po.getLastModifyUser());
        HSSFCell cell13 = row.createCell(13);
        if (po.getLastModifyTime() != null) {
            cell13.setCellValue(DateUtil.parseDateToStr(po.getLastModifyTime(), DateUtil.FORMAT_2));
        }
    }



    private void buildBodyShareholder(HSSFSheet sheet, int i, Shareholder po, HttpServletRequest request,
            List<EnterpriseBasic> basics) throws ParseException {
        // 创建HSSFRow对象
        HSSFRow row = sheet.createRow(i);
        HSSFCell cell0 = row.createCell(0);
        cell0.setCellValue(po.getId());
        HSSFCell cell1 = row.createCell(1);
        cell1.setCellValue(getCodeByEId(basics, po.geteId()));
        HSSFCell cell2 = row.createCell(2);
        cell2.setCellValue(po.getName());
        HSSFCell cell3 = row.createCell(3);
        if(po.getQuantity()!=null){
            cell3.setCellValue(po.getQuantity());
        }
        HSSFCell cell4 = row.createCell(4);
        if(po.getRatio()!=null){
            cell4.setCellValue(po.getRatio());
        }
        HSSFCell cell5 = row.createCell(5);
        if ("1".equals(po.getEnable())) {
            cell5.setCellValue("正常");
        } else if ("2".equals(po.getEnable())) {
            cell5.setCellValue("删除");
        } else {
            cell5.setCellValue(po.getEnable());
        }
        HSSFCell cell6 = row.createCell(6);
        cell6.setCellValue(po.getCreationUser());
        HSSFCell cell7 = row.createCell(7);
        if (po.getCreationTime() != null) {
            cell7.setCellValue(DateUtil.parseDateToStr(po.getCreationTime(), DateUtil.FORMAT_2));
        }
        HSSFCell cell8 = row.createCell(8);
        cell8.setCellValue(po.getLastModifyUser());
        HSSFCell cell9 = row.createCell(9);
        if (po.getLastModifyTime() != null) {
            cell9.setCellValue(DateUtil.parseDateToStr(po.getLastModifyTime(), DateUtil.FORMAT_2));
        }
    }


    private void buildBodyKnowledgeRight(HSSFSheet sheet, int i, KnowledgeRight po, HttpServletRequest request,
            List<EnterpriseBasic> basics) throws ParseException {
        // 创建HSSFRow对象
        HSSFRow row = sheet.createRow(i);
        HSSFCell cell0 = row.createCell(0);
        cell0.setCellValue(po.getId());
        HSSFCell cell1 = row.createCell(1);
        cell1.setCellValue(getCodeByEId(basics, po.geteId()));
        HSSFCell cell2 = row.createCell(2);
        cell2.setCellValue(po.getName());
        HSSFCell cell3 = row.createCell(3);
        cell3.setCellValue(po.getCode());
        HSSFCell cell4 = row.createCell(4);
        if(po.getPassTime()!=null){
            cell4.setCellValue(DateUtil.parseDateToStr(po.getPassTime()));
        }
        HSSFCell cell5 = row.createCell(5);
        if ("1".equals(po.getEnable())) {
            cell5.setCellValue("正常");
        } else if ("2".equals(po.getEnable())) {
            cell5.setCellValue("删除");
        } else {
            cell5.setCellValue(po.getEnable());
        }
        HSSFCell cell6 = row.createCell(6);
        if(StringUtils.isNotEmpty(po.getType())){
            cell6.setCellValue(DictionaryUtil.getNameByCodeType(request, po.getType(), ConstantUtil.DIC_INFO_KNOWLEDGETYPE));
        }
        HSSFCell cell7 = row.createCell(7);
        cell7.setCellValue(po.getCreationUser());
        HSSFCell cell8 = row.createCell(8);
        if (po.getCreationTime() != null) {
            cell8.setCellValue(DateUtil.parseDateToStr(po.getCreationTime(), DateUtil.FORMAT_2));
        }
        HSSFCell cell9 = row.createCell(9);
        cell9.setCellValue(po.getLastModifyUser());
        HSSFCell cell10 = row.createCell(10);
        if (po.getLastModifyTime() != null) {
            cell10.setCellValue(DateUtil.parseDateToStr(po.getLastModifyTime(), DateUtil.FORMAT_2));
        }
    }
    
    
    private void buildBodyProduction(HSSFSheet sheet, int i, Production po, HttpServletRequest request,
            List<EnterpriseBasic> basics) throws ParseException {
        HSSFRow row = sheet.createRow(i);
        HSSFCell cell0 = row.createCell(0);
        cell0.setCellValue(po.getId());
        HSSFCell cell1 = row.createCell(1);
        cell1.setCellValue(getCodeByEId(basics, po.geteId()));
        HSSFCell cell2 = row.createCell(2);
        cell2.setCellValue(po.getName());
        HSSFCell cell3 = row.createCell(3);
        cell3.setCellValue(po.getIncome());
        /**/
        HSSFCell cell4 = row.createCell(4);
        if ("1".equals(po.getEnable())) {
            cell4.setCellValue("正常");
        } else if ("2".equals(po.getEnable())) {
            cell4.setCellValue("删除");
        } else {
            cell4.setCellValue(po.getEnable());
        }
        String format = request.getParameter("format");
        if("1".equals(format)){
            HSSFCell cell5 = row.createCell(5);
            cell5.setCellValue(po.getFwlyNames());

            HSSFCell cell6 = row.createCell(6);
            cell6.setCellValue(po.getCreationUser());
            HSSFCell cell7 = row.createCell(7);
            if (po.getCreationTime() != null) {
                cell7.setCellValue(DateUtil.parseDateToStr(po.getCreationTime(), DateUtil.FORMAT_2));
            }
            HSSFCell cell8 = row.createCell(8);
            cell8.setCellValue(po.getLastModifyUser());
            HSSFCell cell9 = row.createCell(9);
            if (po.getLastModifyTime() != null) {
                cell9.setCellValue(DateUtil.parseDateToStr(po.getLastModifyTime(), DateUtil.FORMAT_2));
            }
        }else{

            HSSFCell cell5 = row.createCell(5);
            cell5.setCellValue(po.getCreationUser());
            HSSFCell cell6 = row.createCell(6);
            if (po.getCreationTime() != null) {
                cell6.setCellValue(DateUtil.parseDateToStr(po.getCreationTime(), DateUtil.FORMAT_2));
            }
            HSSFCell cell7 = row.createCell(7);
            cell7.setCellValue(po.getLastModifyUser());
            HSSFCell cell8 = row.createCell(8);
            if (po.getLastModifyTime() != null) {
                cell8.setCellValue(DateUtil.parseDateToStr(po.getLastModifyTime(), DateUtil.FORMAT_2));
            }
        }
    }
    

    
    private void buildBodyProductionField(HSSFSheet sheet, int i, ProductionFieldRelate po, HttpServletRequest request,
            List<EnterpriseBasic> basics) throws ParseException {
        HSSFRow row = sheet.createRow(i);
        HSSFCell cell0 = row.createCell(0);
        cell0.setCellValue(po.getId());
        HSSFCell cell1 = row.createCell(1);
        cell1.setCellValue(po.getProductionId());
        HSSFCell cell2 = row.createCell(2);
        if(StringUtils.isNotEmpty(po.getFwlyCode())){
            cell2.setCellValue(DictionaryUtil.getNameByCodeType(request, po.getFwlyCode(), ConstantUtil.DIC_INFO_INDUSTRY));
        }
    }

    

    private List<EnterpriseBasic> getAllBasicInfo() {
        // Map params = new HashMap();
        // params.put(ConstantUtil.DICNATIONARY, DictionaryUtil.getAllEnableDics(request));
        List<EnterpriseBasic> pos = enterpriseBasicDao.findAllEnterprise();
        return pos;
    }

    private String getCodeByEId(List<EnterpriseBasic> pos, String eid) {
        for (EnterpriseBasic po : pos) {
            if (eid.equals(po.getId())) {
                return po.getCode();
            }
        }
        return "";
    }

    /**
     * @Description 导入企业全部信息(基本信息、联系人信息、股东信息、产品信息等等)
     * @param request
     * @return
     */
   /* @ResponseBody
    @RequestMapping(value = "exportBasicInfo")
    public ResultBean exportBasicInfo(HttpServletRequest request) {
        ResultBean rb = new ResultBean();
        FileOutputStream output = null;
        try {
            List<EnterpriseBasic> vos = getAllBasicInfo();
            // 创建HSSFWorkbook对象
            HSSFWorkbook wb = new HSSFWorkbook();
            // 创建HSSFSheet对象
            HSSFSheet sheet = wb.createSheet("企业基础信息");
            buildHeader(sheet);
            int i = 1;
            for (EnterpriseBasic vo : vos) {
                
                 * List<AptitudeRelate> aptitudes = aptitudeRelateDao.selectByEID(vo.getId()); List<String> names = new
                 * ArrayList<String>(); List<String> passTimes = new ArrayList<String>(); for(AptitudeRelate po :
                 * aptitudes){ names.add(DictionaryUtil.getNameByCode(po.getCode(), request));
                 * if(po.getPassTime()!=null){ passTimes.add(DateUtil.parseDateToStr(po.getPassTime())); } }
                 * vo.setAptitude_names(names.toString()); vo.setAptitude_passTimes(passTimes.toString());
                 
                buildBody(sheet, i, vo, request);
                i++;
            }

            // 输出Excel文件
            output = new FileOutputStream("F:\\长天\\北京软件和信息服务业协会\\导出数据\\企业基本信息.xls");
            wb.write(output);
            output.flush();

            rb.isSuccess();
        } catch (Exception e) {
            e.printStackTrace();
            rb.isFail();
        } finally {
            if (output != null) {
                try {
                    output.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return rb;
    }*/
    
    private String getSheetName(int i){
        String sheetName = "";
        switch (i){
        case 0:
            sheetName = "企业基础信息";
            break;
        case 1:
            sheetName = "企业资质信息";
            break;
        case 2:
            sheetName = "企业技术领域信息";
            break;
        case 3:
            sheetName = "企业行业领域信息";
            break;
        case 4:
            sheetName = "企业联系人信息";
            break;
        case 5:
            sheetName = "企业年报信息";
            break;
        case 6:
            sheetName = "企业股东信息";
            break;
        case 7:
            sheetName = "企业知识产权信息";
            break;
        case 8:
            sheetName = "企业产品信息";
            break;
        case 9:
            sheetName = "企业产品服务领域信息";
            break;
        default: 
            sheetName = "未命名";
            break; 
        }
        return sheetName;
    }


    private String getSheetNameFormat(int i){
        String sheetName = "";
        switch (i){
        case 0:
            sheetName = "企业基础信息";
            break;
        case 1:
            sheetName = "企业联系人信息";
            break;
        case 2:
            sheetName = "企业年报信息";
            break;
        case 3:
            sheetName = "企业股东信息";
            break;
        case 4:
            sheetName = "企业知识产权信息";
            break;
        case 5:
            sheetName = "企业产品信息";
            break;
        default: 
            sheetName = "未命名";
            break; 
        }
        return sheetName;
    }

    
    private void dbToExcelAll(HttpServletRequest request,HttpServletResponse response)
            throws ParseException, FileNotFoundException, IOException {
        try {
            // 创建HSSFWorkbook对象
            HSSFWorkbook wb = new HSSFWorkbook();
            String format = request.getParameter("format");
            int len = 10;
            if("1".equals(format)){
                len = 9;
            }
            for(int i=0;i<len;i++){
                HSSFSheet sheet = wb.createSheet(getSheetName(i));
                buildExcel(sheet,i,request);
            }
            
            
            String fileName = null;
            if("1".equals(format)){
                fileName=new String(("企业信息格式.xls").getBytes("UTF-8"),"ISO8859-1");
            }else{
                fileName=new String(("企业信息.xls").getBytes("UTF-8"),"ISO8859-1");
            }
            
            response.setHeader("Content-Disposition", "attachment;filename="+fileName);
            response.setContentType(request.getServletContext().getMimeType(fileName));
            
            ServletOutputStream output = response.getOutputStream();
            wb.write(output);
            output.flush();
            output.close();
        } catch (Exception e) {
            e.printStackTrace();
        } 
    }
    
    
    private void dbToExcelSearchBasicList(HttpServletRequest request,HttpServletResponse response,Map params)
            throws ParseException, FileNotFoundException, IOException {
        try {
            // 创建HSSFWorkbook对象
            HSSFWorkbook wb = new HSSFWorkbook();
            HSSFSheet sheet = wb.createSheet("企业信息");
            buildExcelSearchBasicList(sheet,request,params);
            String fileName = fileName=new String(("企业信息查询结果.xls").getBytes("UTF-8"),"ISO8859-1");
            
            response.setHeader("Content-Disposition", "attachment;filename="+fileName);
            response.setContentType(request.getServletContext().getMimeType(fileName));
            
            ServletOutputStream output = response.getOutputStream();
            wb.write(output);
            output.flush();
            output.close();
        } catch (Exception e) {
            e.printStackTrace();
        } 
    }


    private void dbToExcelBasic(HttpServletRequest request,HttpServletResponse response)
            throws ParseException, FileNotFoundException, IOException {
        try {

            List<EnterpriseBasic> basics = getAllBasicInfo();
            // 创建HSSFWorkbook对象
            HSSFWorkbook wb = new HSSFWorkbook();
            // 创建HSSFSheet对象
            HSSFSheet sheet = wb.createSheet("企业基础信息");
            buildHeader(sheet);
            int i = 1;
            for (EnterpriseBasic po : basics) { 
                buildBody(sheet, i, po, request);
                i++;
            }
            // 输出Excel文件
            String fileName=new String(("企业基础信息.xls").getBytes("UTF-8"),"ISO8859-1");
            response.setHeader("Content-Disposition", "attachment;filename="+fileName);
            response.setContentType(request.getServletContext().getMimeType(fileName));
            
            ServletOutputStream output = response.getOutputStream();
            wb.write(output);
            output.flush();
            output.close();
        } catch (Exception e) {
            e.printStackTrace();
        } 
    }
    

    private void dbToExcelAptitude(HttpServletRequest request,HttpServletResponse response)
            throws ParseException, FileNotFoundException, IOException {
        try {

            List<AptitudeRelate> pos = aptitudeRelateDao.selectAll();
            List<EnterpriseBasic> basics = getAllBasicInfo();
            // 创建HSSFWorkbook对象
            HSSFWorkbook wb = new HSSFWorkbook();
            // 创建HSSFSheet对象
            HSSFSheet sheet = wb.createSheet("企业资质信息");
            buildHeaderAptitude(sheet);
            int i = 1;
            for (AptitudeRelate po : pos) {
                buildBodyAptitude(sheet, i, po, request, basics);
                i++;
            }
            // 输出Excel文件
            String fileName=new String(("企业资质信息.xls").getBytes("UTF-8"),"ISO8859-1");
            response.setHeader("Content-Disposition", "attachment;filename="+fileName);
            response.setContentType(request.getServletContext().getMimeType(fileName));
            
            ServletOutputStream output = response.getOutputStream();
            wb.write(output);
            output.flush();
            output.close();
        } catch (Exception e) {
            e.printStackTrace();
        } 
    }
    
    private void dbToExcelTech(HttpServletRequest request,HttpServletResponse response) throws ParseException, FileNotFoundException, IOException {
        try {
            List<TechRelate> pos = techRelateDao.selectAll();
            List<EnterpriseBasic> basics = getAllBasicInfo();
            // 创建HSSFWorkbook对象
            HSSFWorkbook wb = new HSSFWorkbook();
            // 创建HSSFSheet对象
            HSSFSheet sheet = wb.createSheet("企业技术领域信息");
            buildHeaderTech(sheet);
            int i = 1;
            for (TechRelate po : pos) {
                buildBodyTech(sheet, i, po, request, basics);
                i++;
            }
            
            // 输出Excel文件
            String fileName=new String(("企业技术领域信息.xls").getBytes("UTF-8"),"ISO8859-1");
            response.setHeader("Content-Disposition", "attachment;filename="+fileName);
            response.setContentType(request.getServletContext().getMimeType(fileName));
            
            ServletOutputStream output = response.getOutputStream();
            wb.write(output);
            output.flush();
            output.close();

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
           /* if (output != null) {
                try {
                    output.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }*/
        }

    }
    
    


    private void dbToExcelIndustry(HttpServletRequest request,HttpServletResponse response) throws ParseException, FileNotFoundException, IOException {
        try {
            List<IndustryRelate> pos = industryRelateDao.selectAll();
            List<EnterpriseBasic> basics = getAllBasicInfo();
            // 创建HSSFWorkbook对象
            HSSFWorkbook wb = new HSSFWorkbook();
            // 创建HSSFSheet对象
            HSSFSheet sheet = wb.createSheet("企业行业领域信息");
            buildHeaderIndustry(sheet);
            int i = 1;
            for (IndustryRelate po : pos) {
                buildBodyIndustry(sheet, i, po, request, basics);
                i++;
            }
            
            // 输出Excel文件
            String fileName=new String(("企业行业领域信息.xls").getBytes("UTF-8"),"ISO8859-1");
            response.setHeader("Content-Disposition", "attachment;filename="+fileName);
            response.setContentType(request.getServletContext().getMimeType(fileName));
            
            ServletOutputStream output = response.getOutputStream();
            wb.write(output);
            output.flush();
            output.close();

        } catch (Exception e) {
            e.printStackTrace();
        } 

    }


    private void dbToExcelContact(HttpServletRequest request,HttpServletResponse response)
            throws ParseException, FileNotFoundException, IOException {
        try {

            List<ContactInfo> pos = contactInfoDao.selectAll();
            List<EnterpriseBasic> basics = getAllBasicInfo();
            // 创建HSSFWorkbook对象
            HSSFWorkbook wb = new HSSFWorkbook();
            // 创建HSSFSheet对象
            HSSFSheet sheet = wb.createSheet("企业联系人信息");
            buildHeaderContact(sheet);
            int i = 1;
            for (ContactInfo po : pos) {
                buildBodyContact(sheet, i, po, request, basics);
                i++;
            }
            // 输出Excel文件
            String fileName=new String(("企业联系人信息.xls").getBytes("UTF-8"),"ISO8859-1");
            response.setHeader("Content-Disposition", "attachment;filename="+fileName);
            response.setContentType(request.getServletContext().getMimeType(fileName));
            
            ServletOutputStream output = response.getOutputStream();
            wb.write(output);
            output.flush();
            output.close();
        } catch (Exception e) {
            e.printStackTrace();
        } 
    }

    private void dbToExcelAnnual(HttpServletRequest request,HttpServletResponse response)
            throws ParseException, FileNotFoundException, IOException {
        try {

            List<AnnualReport> pos = annualReportDao.selectAll();
            List<EnterpriseBasic> basics = getAllBasicInfo();
            // 创建HSSFWorkbook对象
            HSSFWorkbook wb = new HSSFWorkbook();
            // 创建HSSFSheet对象
            HSSFSheet sheet = wb.createSheet("企业年报信息");
            buildHeaderAnnual(sheet);
            int i = 1;
            for (AnnualReport po : pos) {
                buildBodyAnnual(sheet, i, po, request, basics);
                i++;
            }
            // 输出Excel文件
            String fileName=new String(("企业年报信息.xls").getBytes("UTF-8"),"ISO8859-1");
            response.setHeader("Content-Disposition", "attachment;filename="+fileName);
            response.setContentType(request.getServletContext().getMimeType(fileName));
            
            ServletOutputStream output = response.getOutputStream();
            wb.write(output);
            output.flush();
            output.close();
        } catch (Exception e) {
            e.printStackTrace();
        } 
    }



    private void dbToExcelShareholder(HttpServletRequest request,HttpServletResponse response)
            throws ParseException, FileNotFoundException, IOException {
        try {

            List<Shareholder> pos = shareholderDao.selectAll();
            List<EnterpriseBasic> basics = getAllBasicInfo();
            // 创建HSSFWorkbook对象
            HSSFWorkbook wb = new HSSFWorkbook();
            // 创建HSSFSheet对象
            HSSFSheet sheet = wb.createSheet("企业股东信息");
            buildHeaderShareholder(sheet);
            int i = 1;
            for (Shareholder po : pos) {
                buildBodyShareholder(sheet, i, po, request, basics);
                i++;
            }
            // 输出Excel文件
            String fileName=new String(("企业股东信息.xls").getBytes("UTF-8"),"ISO8859-1");
            response.setHeader("Content-Disposition", "attachment;filename="+fileName);
            response.setContentType(request.getServletContext().getMimeType(fileName));
            
            ServletOutputStream output = response.getOutputStream();
            wb.write(output);
            output.flush();
            output.close();
        } catch (Exception e) {
            e.printStackTrace();
        } 
    }




    private void dbToExcelKnowledgeRight(HttpServletRequest request,HttpServletResponse response)
            throws ParseException, FileNotFoundException, IOException {
        try {

            List<KnowledgeRight> pos = knowledgeRightDao.selectAll();
            List<EnterpriseBasic> basics = getAllBasicInfo();
            // 创建HSSFWorkbook对象
            HSSFWorkbook wb = new HSSFWorkbook();
            // 创建HSSFSheet对象
            HSSFSheet sheet = wb.createSheet("企业知识产权信息");
            buildHeaderKnowledgeRight(sheet);
            int i = 1;
            for (KnowledgeRight po : pos) {
                buildBodyKnowledgeRight(sheet, i, po, request, basics);
                i++;
            }
            // 输出Excel文件
            String fileName=new String(("企业知识产权信息.xls").getBytes("UTF-8"),"ISO8859-1");
            response.setHeader("Content-Disposition", "attachment;filename="+fileName);
            response.setContentType(request.getServletContext().getMimeType(fileName));
            
            ServletOutputStream output = response.getOutputStream();
            wb.write(output);
            output.flush();
            output.close();
        } catch (Exception e) {
            e.printStackTrace();
        } 
    }
    
    

    private void dbToExcelProduction(HttpServletRequest request,HttpServletResponse response) throws ParseException, FileNotFoundException, IOException {
        try {
            List<Production> pos = productionDao.selectAll();
            List<EnterpriseBasic> basics = getAllBasicInfo();
            // 创建HSSFWorkbook对象
            HSSFWorkbook wb = new HSSFWorkbook();
            // 创建HSSFSheet对象
            HSSFSheet sheet = wb.createSheet("企业产品领域信息");
            String format = request.getParameter("format");
            buildHeaderProduction(sheet,format);
            int i = 1;
            for (Production po : pos) {
                buildBodyProduction(sheet, i, po, request, basics);
                i++;
            }
            
            // 输出Excel文件
            String fileName=new String(("企业产品信息.xls").getBytes("UTF-8"),"ISO8859-1");
            response.setHeader("Content-Disposition", "attachment;filename="+fileName);
            response.setContentType(request.getServletContext().getMimeType(fileName));
            
            ServletOutputStream output = response.getOutputStream();
            wb.write(output);
            output.flush();
            output.close();

        } catch (Exception e) {
            e.printStackTrace();
        } 

    }
    

    

    private void dbToExcelProductionField(HttpServletRequest request,HttpServletResponse response) throws ParseException, FileNotFoundException, IOException {
        try {
            List<ProductionFieldRelate> pos = productionFieldRelateDao.selectAll();
            List<EnterpriseBasic> basics = getAllBasicInfo();
            // 创建HSSFWorkbook对象
            HSSFWorkbook wb = new HSSFWorkbook();
            // 创建HSSFSheet对象
            HSSFSheet sheet = wb.createSheet("企业产品领域信息");
            buildHeaderProductionField(sheet);
            int i = 1;
            for (ProductionFieldRelate po : pos) {
                buildBodyProductionField(sheet, i, po, request, basics);
                i++;
            }
            
            // 输出Excel文件
            String fileName=new String(("企业产品领域信息.xls").getBytes("UTF-8"),"ISO8859-1");
            response.setHeader("Content-Disposition", "attachment;filename="+fileName);
            response.setContentType(request.getServletContext().getMimeType(fileName));
            
            ServletOutputStream output = response.getOutputStream();
            wb.write(output);
            output.flush();
            output.close();

        } catch (Exception e) {
            e.printStackTrace();
        } 

    }
    
    
    
    

    /**
     * @Description
     * @param request
     * @return
     */
    @RequestMapping(value = "exportData")
    public void exportData(HttpServletRequest request,HttpServletResponse response) {
        ResultBean rb = new ResultBean();
        try {
            String dataType = request.getParameter("dataType");
            if ("all".equals(dataType)) {
                dbToExcelAll(request,response);
            } else if ("enterpriseBasic".equals(dataType)) {
                dbToExcelBasic(request,response);
            }  else if ("aptitude".equals(dataType)) {
                dbToExcelAptitude(request,response);
            } else if ("tech".equals(dataType)) {
                dbToExcelTech(request,response);
            }else if ("industry".equals(dataType)) {
                dbToExcelIndustry(request,response);
            }else if ("contact".equals(dataType)) {
                dbToExcelContact(request,response);
            }else if ("annual".equals(dataType)) {
                dbToExcelAnnual(request,response);
            }else if ("shareholder".equals(dataType)) {
                dbToExcelShareholder(request,response);
            }else if ("knowledgeRight".equals(dataType)) {
                dbToExcelKnowledgeRight(request,response);
            }else if ("production".equals(dataType)) {
                dbToExcelProduction(request,response);
            }else if ("productionField".equals(dataType)) {
                dbToExcelProductionField(request,response);
            }
            
            rb.isSuccess();
        } catch (Exception e) {
            e.printStackTrace();
            rb.isFail();
        }
    }


    @RequestMapping(value = "printData")
    public void printData(HttpServletRequest request,HttpServletResponse response) {
        ResultBean rb = new ResultBean();
        try {
            dbToExcelAll(request,response);
            
            rb.isSuccess();
        } catch (Exception e) {
            e.printStackTrace();
            rb.isFail();
        }
    }

    @RequestMapping(value = "exportSearchBasicList")
    public void exportSearchBasicList(HttpServletRequest request,HttpServletResponse response,EnterpriseBasicVO vo) {
        ResultBean rb = new ResultBean();
        Map params = WebTools.getParameterMap(request);
        params.put(ConstantUtil.DICNATIONARY, DictionaryUtil.getAllEnableDics(request));
        params.put("techs", vo.getTechs());
        params.put("industrys", vo.getIndustrys());
        params.put("lastYear", DateUtil.getYearNew(new Date())-1);
        
        try {
            dbToExcelSearchBasicList(request,response,params);
            rb.isSuccess();
        } catch (Exception e) {
            e.printStackTrace();
            rb.isFail();
        }
    }


}
