package cn.com.pansky.otp5.association.controller;

import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.Page;

import cn.com.pansky.otp5.association.controller.vo.ProductionVO;
import cn.com.pansky.otp5.association.service.IProductionService;
import cn.com.pansky.otp5.baseplatform.enums.EnableTypeEnum;
import cn.com.pansky.otp5.common.DictionaryUtil;
import cn.com.pansky.otp5.common.GridBean;
import cn.com.pansky.otp5.common.ResultBean;
import cn.com.pansky.otp5.common.WebTools;

/**
 * 
 * @ClassName ProductionController
 * @Description TODO(这里用一句话描述这个类的作用)
 * @author Administrator
 * @Date 2017年9月28日 下午11:02:54
 * @version 1.0.0
 */
@Controller
@RequestMapping("/production/")
public class ProductionController {
    
    @Resource
    private IProductionService productionService;

    @RequestMapping(value="toProduction")
    public String toProduction(HttpServletRequest req,HttpServletResponse resp){
       return "association/production";
    }
	
	@ResponseBody
    @RequestMapping(value="/findByPage")
    public GridBean<ProductionVO> findByPage(HttpServletRequest req,HttpServletResponse resp){
        Map params = WebTools.getParameterMap(req);
        System.out.println("params=====================>>>>" + params);
        params.put("dics", DictionaryUtil.getAllEnableDics(req));
        
        Page<ProductionVO> page = (Page<ProductionVO>) productionService.findByPage(params);
        
        GridBean<ProductionVO> grid = new GridBean<ProductionVO>();
        grid.setTotal(page.getTotal());
        grid.setRows(page);

//        System.out.println(JSONObject.toJSONString(page));
        
//        System.out.println(JSONObject.toJSONString(grid));
        
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
    public ResultBean add(ProductionVO vo){
        ResultBean rb = new ResultBean();
        try{
            System.out.println("ProductionVO=================>>>>" + vo);
            vo.setEnable("1");
            productionService.insert(vo);
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
            ProductionVO vo = productionService.selectByPrimaryKey(id);
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
    public ResultBean updateByPrimaryKey(ProductionVO vo){
        ResultBean rb = new ResultBean();
        try{
            productionService.updateByPrimaryKey(vo);
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
    @RequestMapping(value="deleteProductionById")
    public ResultBean deleteProductionById(String id){
        ResultBean rb = new ResultBean();
        try{
            ProductionVO vo = productionService.selectByPrimaryKey(id);
            vo.setEnable(EnableTypeEnum.SC.getKey());
            productionService.updateByPrimaryKey(vo);
            rb.isSuccess();
        }catch(Exception e){
            e.printStackTrace();
            rb.isFail();
        }
        return rb;
    } 
	
}
