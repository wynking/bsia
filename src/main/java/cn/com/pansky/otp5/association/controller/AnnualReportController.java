package cn.com.pansky.otp5.association.controller;

import java.lang.reflect.InvocationTargetException;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.beanutils.BeanUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.Page;

import cn.com.pansky.otp5.association.controller.vo.AnnualReportVO;
import cn.com.pansky.otp5.association.dao.po.AnnualReport;
import cn.com.pansky.otp5.association.service.IAnnualReportService;
import cn.com.pansky.otp5.baseplatform.enums.EnableTypeEnum;
import cn.com.pansky.otp5.common.DictionaryUtil;
import cn.com.pansky.otp5.common.GridBean;
import cn.com.pansky.otp5.common.ResultBean;
import cn.com.pansky.otp5.common.WebTools;

/**
 * 
 * @ClassName AnnualReportController
 * @Description TODO(这里用一句话描述这个类的作用)
 * @author Administrator
 * @Date 2017年9月27日 上午10:26:13
 * @version 1.0.0
 */
@Controller
@RequestMapping("/annualReport/")
public class AnnualReportController {
    
    @Resource
    private IAnnualReportService annualReportService;

    @RequestMapping(value="toAnnualReport")
    public String toAnnualReport(HttpServletRequest req,HttpServletResponse resp){
       return "association/annualReport";
    }
	
	@ResponseBody
    @RequestMapping(value="/findByPage")
    public GridBean<AnnualReportVO> findByPage(HttpServletRequest req,HttpServletResponse resp){
        Map params = WebTools.getParameterMap(req);
        System.out.println("params=====================>>>>" + params);
        params.put("dics", DictionaryUtil.getAllEnableDics(req));
        
        Page<AnnualReportVO> page = (Page<AnnualReportVO>) annualReportService.findByPage(params);
        
        GridBean<AnnualReportVO> grid = new GridBean<AnnualReportVO>();
        grid.setTotal(page.getTotal());
        grid.setRows(page);

        System.out.println(JSONObject.toJSONString(page));
        
        System.out.println(JSONObject.toJSONString(grid));
        
        return grid;
    }
	
	/**
	 * 
	 * @Description (TODO这里用一句话描述这个方法的作用)
	 * @param vo
	 * @return
	 */
	@ResponseBody
    @RequestMapping(value="add")
    public ResultBean add(AnnualReportVO vo){
        ResultBean rb = new ResultBean();
        try{
            System.out.println("AnnualReportVO=================>>>>" + vo);
            vo.setEnable("1");
            annualReportService.insert(vo);
            rb.isSuccess();
        }catch(Exception e){
            e.printStackTrace();
            rb.isFail();
        }
        return rb;
    }
	
	/**
	 * 
	 * @Description (TODO这里用一句话描述这个方法的作用)
	 * @param id
	 * @return
	 */
	@ResponseBody
    @RequestMapping(value="selectByPrimaryKey")
    public ResultBean selectByPrimaryKey(String id){
        ResultBean rb = new ResultBean();
        try{
            AnnualReportVO vo = annualReportService.selectByPrimaryKey(id);
            rb.setData(vo);
            rb.isSuccess();
        }catch(Exception e){
            e.printStackTrace();
            rb.isFail();
        }
        return rb;
    }
	
	/**
	 * 
	 * @Description (TODO这里用一句话描述这个方法的作用)
	 * @param vo
	 * @return
	 */
	@ResponseBody
    @RequestMapping(value="updateByPrimaryKey")
    public ResultBean updateByPrimaryKey(AnnualReportVO vo){
        ResultBean rb = new ResultBean();
        try{
            annualReportService.updateByPrimaryKey(vo);
            rb.isSuccess();
        }catch(Exception e){
            e.printStackTrace();
            rb.isFail();
        }
        return rb;
    }
	
	
	/**
	 *  
	 * @Description (TODO这里用一句话描述这个方法的作用)
	 * @param vo
	 * @return
	 */
	@ResponseBody
    @RequestMapping(value="deleteAnnualReportById")
    public ResultBean deleteAnnualReportById(String id){
        ResultBean rb = new ResultBean();
        try{
            AnnualReportVO vo = annualReportService.selectByPrimaryKey(id);
            vo.setEnable(EnableTypeEnum.SC.getKey());
            annualReportService.updateByPrimaryKey(vo);
            rb.isSuccess();
        }catch(Exception e){
            e.printStackTrace();
            rb.isFail();
        }
        return rb;
    } 
	
	
	public static void main(String[] args) {
	    
	    AnnualReportVO vo =  new  AnnualReportVO();
	    AnnualReport po = new AnnualReport();
	    po.setTotalIncome(111d);
	    po.setTotalAssets(222d);
	    
	    //BeanUtils.copyProperties(po, vo);
	    //PropertyUtils.copyProperties(vo, po);
	    try {
            BeanUtils.copyProperties(vo, po);
        } catch (IllegalAccessException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        System.out.println("================>>>" + po);
        System.out.println("================>>>" + vo);
	    
	    
	}
}
