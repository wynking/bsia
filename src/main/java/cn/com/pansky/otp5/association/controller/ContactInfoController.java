package cn.com.pansky.otp5.association.controller;

import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.Page;

import cn.com.pansky.otp5.association.controller.vo.ContactInfoVO;
import cn.com.pansky.otp5.association.controller.vo.EnterpriseBasicVO;
import cn.com.pansky.otp5.association.service.IContactInfoService;
import cn.com.pansky.otp5.baseplatform.enums.EnableTypeEnum;
import cn.com.pansky.otp5.common.DictionaryUtil;
import cn.com.pansky.otp5.common.GridBean;
import cn.com.pansky.otp5.common.ResultBean;
import cn.com.pansky.otp5.common.WebTools;

/**
 * 
 * @ClassName ContactInfoController
 * @Description TODO(这里用一句话描述这个类的作用)
 * @author Administrator
 * @Date 2017年9月25日 下午7:11:38
 * @version 1.0.0
 */
@Controller
@RequestMapping("/contact/")
public class ContactInfoController {
    
    @Resource
    private IContactInfoService contactInfoService;

    @RequestMapping(value="toContact")
    public String toContact(HttpServletRequest req,HttpServletResponse resp){
            /*ResultBean rb = new ResultBean();
            System.out.println("=================>>>>" + vo);
            enterpriseBasicService.insert(vo);*/
        /*String eId = req.getParameter("eId");
        System.out.println("eId=====toBasic============>>>>" + eId);
        req.setAttribute("eId", eId);*/
       return "association/contact_info";
    }
	
	@ResponseBody
    @RequestMapping(value="/findByPage")
    public GridBean<ContactInfoVO> findByPage(HttpServletRequest req,HttpServletResponse resp){
        Map params = WebTools.getParameterMap(req);
        System.out.println("params=====================>>>>" + params);
        params.put("dics", DictionaryUtil.getAllEnableDics(req));
        
        Page<ContactInfoVO> page = (Page<ContactInfoVO>) contactInfoService.findByPage(params);
        
        GridBean<ContactInfoVO> grid = new GridBean<ContactInfoVO>();
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
    public ResultBean add(ContactInfoVO vo){
        ResultBean rb = new ResultBean();
        try{
            System.out.println("ContactInfoVO=================>>>>" + vo);
            vo.setEnable("1");
            contactInfoService.insert(vo);
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
            ContactInfoVO vo = contactInfoService.selectByPrimaryKey(id);
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
    public ResultBean updateByPrimaryKey(ContactInfoVO vo){
        ResultBean rb = new ResultBean();
        try{
            contactInfoService.updateByPrimaryKey(vo);
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
    @RequestMapping(value="deleteContactById")
    public ResultBean deleteContactById(String id){
        ResultBean rb = new ResultBean();
        try{
            ContactInfoVO vo = contactInfoService.selectByPrimaryKey(id);
            vo.setEnable(EnableTypeEnum.SC.getKey());
            contactInfoService.updateByPrimaryKey(vo);
            rb.isSuccess();
        }catch(Exception e){
            e.printStackTrace();
            rb.isFail();
        }
        return rb;
    } 
	
}
