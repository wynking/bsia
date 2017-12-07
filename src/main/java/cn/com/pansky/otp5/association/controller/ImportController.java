package cn.com.pansky.otp5.association.controller;

import java.io.File;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.io.FileUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import cn.com.pansky.otp5.association.dao.po.ContactInfo;
import cn.com.pansky.otp5.association.dao.po.EnterpriseBasic;
import cn.com.pansky.otp5.association.service.IImportService;
import cn.com.pansky.otp5.baseplatform.dao.po.BasePO;
import cn.com.pansky.otp5.baseplatform.dao.po.Dictionary;
import cn.com.pansky.otp5.baseplatform.service.IDictionaryService;
import cn.com.pansky.otp5.common.ResultBean;

/**
 * 
 * @ClassName AnalysisController
 * @Description TODO(这里用一句话描述这个类的作用)
 * @author Administrator
 * @Date 2017年11月15日 下午7:11:38
 * @version 1.0.0
 */
@Controller
@RequestMapping("/import/")
public class ImportController {
    
    @Resource  
    private IImportService importService;

    @RequestMapping(value="toImportData")
    public String toImportData(HttpServletRequest req,HttpServletResponse resp){
       return "association/importData";
    } 
    
    /**
     * 
     * @Description 导入企业基本信息数据
     * @param vo
     * @return
     */
    @ResponseBody
    @RequestMapping(value="importAll")
    public ResultBean importAll(HttpServletRequest request,@RequestParam("file")MultipartFile file){
        ResultBean rb = new ResultBean();
        try{
//            String dataType = request.getParameter("dataType");
//            System.out.println("dataType============>>>" + dataType);
            String path = "F:\\长天\\北京软件和信息服务业协会\\导入数据\\temp\\" + System.currentTimeMillis()+ file.getOriginalFilename();
            File excelFile = new File(path);
            FileUtils.copyInputStreamToFile(file.getInputStream(), excelFile);
            importService.importAll(excelFile, request);
            
            /*for(int i=0;i<10;i++){
                List<BasePO> pos = ExcelToDB.bulidBasePO(excelFile, dataType, request,i);
                importService.insertData(pos, dataType,request);
            }*/
            
           
            rb.isSuccess();
        }catch(Exception e){
            e.printStackTrace();
            rb.isFail();
        }
        return rb;
    }
    
    /**
     * 
     * @Description 导入企业基本信息数据
     * @param vo
     * @return
     */
    @ResponseBody
    @RequestMapping(value="importData")
    public ResultBean importData(HttpServletRequest request,@RequestParam("file")MultipartFile file){
        ResultBean rb = new ResultBean();
        try{
            String dataType = request.getParameter("dataType");
            System.out.println("dataType============>>>" + dataType);
            String path = "F:\\长天\\北京软件和信息服务业协会\\导入数据\\temp\\" + System.currentTimeMillis()+ file.getOriginalFilename();
            File excelFile = new File(path);
            FileUtils.copyInputStreamToFile(file.getInputStream(), excelFile);
           
            List<BasePO> pos = ExcelToDB.bulidBasePO(excelFile, dataType, request,0);
            importService.insertData(pos, dataType,request);
            
            rb.isSuccess();
        }catch(Exception e){
            e.printStackTrace();
            rb.isFail();
        }
        return rb;
    }
    
    /**
     * 
     * @Description 导入字典信息
     * @param request
     * @return
     */
    @ResponseBody
    @RequestMapping(value="importDictionary")
    public ResultBean importDictionary(HttpServletRequest request){
        ResultBean rb = new ResultBean();
        try{
            
            
            rb.isSuccess();
        }catch(Exception e){
            e.printStackTrace();
            rb.isFail();
        }
        return rb;
    }
    
    

    
    
}
