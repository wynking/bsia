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

import cn.com.pansky.otp5.association.controller.vo.ShareholderVO;
import cn.com.pansky.otp5.association.service.IShareholderService;
import cn.com.pansky.otp5.baseplatform.enums.EnableTypeEnum;
import cn.com.pansky.otp5.common.DictionaryUtil;
import cn.com.pansky.otp5.common.GridBean;
import cn.com.pansky.otp5.common.ResultBean;
import cn.com.pansky.otp5.common.WebTools;

/**
 * 
 * @ClassName ShareholderController
 * @Description TODO(这里用一句话描述这个类的作用)
 * @author Administrator
 * @Date 2017年9月27日 下午6:47:48
 * @version 1.0.0
 */
@Controller
@RequestMapping("/shareholder/")
public class ShareholderController {
    
    @Resource
    private IShareholderService shareholderService;

    @RequestMapping(value="toShareholder")
    public String toShareholder(HttpServletRequest req,HttpServletResponse resp){
       return "association/shareholder";
    }
	
	@ResponseBody
    @RequestMapping(value="/findByPage")
    public GridBean<ShareholderVO> findByPage(HttpServletRequest req,HttpServletResponse resp){
        Map params = WebTools.getParameterMap(req);
        System.out.println("params=====================>>>>" + params);
        params.put("dics", DictionaryUtil.getAllEnableDics(req));
        
        Page<ShareholderVO> page = (Page<ShareholderVO>) shareholderService.findByPage(params);
        
        GridBean<ShareholderVO> grid = new GridBean<ShareholderVO>();
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
    public ResultBean add(ShareholderVO vo){
        ResultBean rb = new ResultBean();
        try{
            System.out.println("ShareholderVO=================>>>>" + vo);
            vo.setEnable("1");
            shareholderService.insert(vo);
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
            ShareholderVO vo = shareholderService.selectByPrimaryKey(id);
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
    public ResultBean updateByPrimaryKey(ShareholderVO vo){
        ResultBean rb = new ResultBean();
        try{
            shareholderService.updateByPrimaryKey(vo);
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
    @RequestMapping(value="deleteShareholderById")
    public ResultBean deleteShareholderById(String id){
        ResultBean rb = new ResultBean();
        try{
            ShareholderVO vo = shareholderService.selectByPrimaryKey(id);
            vo.setEnable(EnableTypeEnum.SC.getKey());
            shareholderService.updateByPrimaryKey(vo);
            rb.isSuccess();
        }catch(Exception e){
            e.printStackTrace();
            rb.isFail();
        }
        return rb;
    } 
	
}
