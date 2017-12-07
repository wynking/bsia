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

import cn.com.pansky.otp5.association.controller.vo.KnowledgeRightVO;
import cn.com.pansky.otp5.association.service.IKnowledgeRightService;
import cn.com.pansky.otp5.baseplatform.enums.EnableTypeEnum;
import cn.com.pansky.otp5.common.ConstantUtil;
import cn.com.pansky.otp5.common.DictionaryUtil;
import cn.com.pansky.otp5.common.GridBean;
import cn.com.pansky.otp5.common.ResultBean;
import cn.com.pansky.otp5.common.WebTools;

/**
 * 
 * @ClassName KnowledgeRightController
 * @Description TODO(这里用一句话描述这个类的作用)
 * @author Administrator
 * @Date 2017年9月27日 下午6:47:48
 * @version 1.0.0
 */
@Controller
@RequestMapping("/knowledgeRight/")
public class KnowledgeRightController {
    
    @Resource
    private IKnowledgeRightService knowledgeRightService;
    
    @RequestMapping(value="toKnowledgeRight")
    public String toKnowledgeRight(HttpServletRequest req,HttpServletResponse resp){
       return "association/knowledgeRight";
    }
	
	@ResponseBody
    @RequestMapping(value="/findByPage")
    public GridBean<KnowledgeRightVO> findByPage(HttpServletRequest req,HttpServletResponse resp){
        Map params = WebTools.getParameterMap(req);
//        System.out.println("params=====================>>>>" + params);
        params.put("dics", DictionaryUtil.getAllEnableDics(req));
        params.put(ConstantUtil.REQUEST, req);
        
        Page<KnowledgeRightVO> page = (Page<KnowledgeRightVO>) knowledgeRightService.findByPage(params);
        
        GridBean<KnowledgeRightVO> grid = new GridBean<KnowledgeRightVO>();
        grid.setTotal(page.getTotal());
        grid.setRows(page);
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
    public ResultBean add(KnowledgeRightVO vo){
        ResultBean rb = new ResultBean();
        try{
            System.out.println("KnowledgeRightVO=================>>>>" + vo);
            vo.setEnable("1");
            knowledgeRightService.insert(vo);
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
            KnowledgeRightVO vo = knowledgeRightService.selectByPrimaryKey(id);
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
    public ResultBean updateByPrimaryKey(KnowledgeRightVO vo){
        ResultBean rb = new ResultBean();
        try{
            knowledgeRightService.updateByPrimaryKey(vo);
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
    @RequestMapping(value="deleteKnowledgeRightById")
    public ResultBean deleteKnowledgeRightById(String id){
        ResultBean rb = new ResultBean();
        try{
            KnowledgeRightVO vo = knowledgeRightService.selectByPrimaryKey(id);
            vo.setEnable(EnableTypeEnum.SC.getKey());
            knowledgeRightService.updateByPrimaryKey(vo);
            rb.isSuccess();
        }catch(Exception e){
            e.printStackTrace();
            rb.isFail();
        }
        return rb;
    } 
	
}
