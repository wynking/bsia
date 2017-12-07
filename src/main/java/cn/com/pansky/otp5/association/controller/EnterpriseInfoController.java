package cn.com.pansky.otp5.association.controller;

import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
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

import cn.com.pansky.otp5.association.controller.vo.EnterpriseBasicVO;
import cn.com.pansky.otp5.association.service.IEnterpriseBasicService;
import cn.com.pansky.otp5.common.ConstantUtil;
import cn.com.pansky.otp5.common.DateUtil;
import cn.com.pansky.otp5.common.DictionaryUtil;
import cn.com.pansky.otp5.common.GridBean;
import cn.com.pansky.otp5.common.ResultBean;
import cn.com.pansky.otp5.common.UserUtil;
import cn.com.pansky.otp5.common.WebTools;

/**
 * 
 * @ClassName EnterpriseInfoController
 * @Description 企业信息管理
 * @author Administrator
 * @Date 2017年9月21日 上午10:53:00
 * @version 1.0.0
 */
@Controller
@RequestMapping("/enterprise/")
public class EnterpriseInfoController {
    
    @Resource
    private IEnterpriseBasicService enterpriseBasicService;

    private void setOtherParams(HttpServletRequest req, EnterpriseBasicVO vo) {
        Map<String,String> otherParams = new HashMap<String, String>();
        Map<String,String[]> params = req.getParameterMap();
//        List<Dictionary> dics = DictionaryUtil.getDicsByType(req, "");
        String[] aptitudes = vo.getAptitudes();
        if(aptitudes!=null){
            for(String code : aptitudes){
                String value = params.get("timeAptitudes_" + code)[0];
                otherParams.put("timeAptitudes_" + code, value);
            }
        }
        vo.setOtherParams(otherParams);
    }
	/**
	 * 跳转到
	 * @return
	 */
	@RequestMapping("list")
	public String index(HttpServletRequest req,HttpServletResponse resp){
	    
//	    List<Dictionary> levels = DictionaryUtil.getAllEnableDicByType(req, DictionaryTypeEnum.MUMBER_LEVEL.getKey());
//	    req.setAttribute("levels", levels);
	    
		return "association/enterpriseInfoList";
	}
	
	@ResponseBody
    @RequestMapping(value="/findByPage")
    public GridBean<EnterpriseBasicVO> findByPage(HttpServletRequest req,HttpServletResponse resp,EnterpriseBasicVO vo){
        Map params = WebTools.getParameterMap(req);
        params.put(ConstantUtil.DICNATIONARY, DictionaryUtil.getAllEnableDics(req));
        params.put("techs", vo.getTechs());
        params.put("industrys", vo.getIndustrys());
        params.put("lastYear", DateUtil.getYearNew(new Date())-1);
        //如果不是超级管理员角色,则只能查看自己所属得企业信息
        if(!"1".equals(UserUtil.getUser(req).getIsSuper())){
            params.put("owner", UserUtil.getUser(req).getId());
        }
        
        Page<EnterpriseBasicVO> page = (Page<EnterpriseBasicVO>) enterpriseBasicService.findByPage(params);
        
        GridBean<EnterpriseBasicVO> grid = new GridBean<EnterpriseBasicVO>();
        grid.setTotal(page.getTotal());
        grid.setRows(page);
//        System.out.println(JSONObject.toJSONString(page));
//        System.out.println(JSONObject.toJSONString(grid));
        return grid;
    }
	
	/**
	 * 
	 * @Description 跳转到企业新增页面
	 * @param vo
	 * @return
	 */
	@RequestMapping(value="toAdd")
    public String toAdd(EnterpriseBasicVO vo){
	        /*ResultBean rb = new ResultBean();
	        System.out.println("=================>>>>" + vo);
	        enterpriseBasicService.insert(vo);*/
	   return "association/enterpriseSingle";
	}
	

    @RequestMapping(value="toUpdate")
    public String toUpdate(HttpServletRequest req,HttpServletResponse resp){
            /*ResultBean rb = new ResultBean();
            System.out.println("=================>>>>" + vo);
            enterpriseBasicService.insert(vo);*/
        String eId = req.getParameter("eId");
        String isDetail = req.getParameter("isDetail");

        System.out.println("eId=====toUpdate============>>>>" + eId);
        System.out.println("isDetail=====toUpdate============>>>>" + isDetail);
        req.setAttribute("eId", eId);
        req.setAttribute("isDetail", isDetail);
        return "association/enterpriseSingle";
    }
    
	

    @RequestMapping(value="toBasic")
    public String toBasic(HttpServletRequest req,HttpServletResponse resp){
            /*ResultBean rb = new ResultBean();
            System.out.println("=================>>>>" + vo);
            enterpriseBasicService.insert(vo);*/
        /*String eId = req.getParameter("eId");
        System.out.println("eId=====toBasic============>>>>" + eId);
        req.setAttribute("eId", eId);*/
       return "association/basic_info";
    }
    

    
    
	/**
	 * 
	 * @Description (TODO这里用一句话描述这个方法的作用)
	 * @param vo
	 * @return
	 */
	@ResponseBody
    @RequestMapping(value="add")
    public ResultBean add(HttpServletRequest req,EnterpriseBasicVO vo){
        ResultBean rb = new ResultBean();
        try{
            boolean flag = enterpriseBasicService.checkCode(vo);
            if(flag){
                String id = vo.getId();
                setOtherParams(req, vo);
                if(StringUtils.isEmpty(id)){
                    vo.setOwner(UserUtil.getUser(req).getId());
                    enterpriseBasicService.insert(vo);
                }else{
                    enterpriseBasicService.updateByPrimaryKey(vo);
                }
                rb.setData(vo);
                rb.isSuccess();
            }else{
                rb.setMsg("统一社会信用代码已存在，请更改！");
                rb.isFail();
            }
            
            
        }catch(Exception e){
            e.printStackTrace();
            rb.isFail();
        }
        return rb;
    }
	
	/**
	 * 
	 * @Description 企业位置信息定位
	 * @param req
	 * @param vo
	 * @return
	 */
	@ResponseBody
    @RequestMapping(value="updatePosition")
    public ResultBean updatePosition(HttpServletRequest req,EnterpriseBasicVO vo){
        ResultBean rb = new ResultBean();
        try{
//            System.out.println("=================>>>>" + vo);
//          String id = vo.getId();
          enterpriseBasicService.updatePositionByPrimaryKey(vo);
          //rb.setData(vo);
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
	 * @param req
	 * @param resp
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value="selectByPrimaryKey")
	public ResultBean selectByPrimaryKey(HttpServletRequest req,HttpServletResponse resp){
	    ResultBean rb = new ResultBean();
	    try{
	        String id = req.getParameter("id");
	        EnterpriseBasicVO vo = enterpriseBasicService.selectByPrimaryKey(id);
//	        System.out.println("vo=========selectByPrimaryKey==========>>>" + vo);
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
	 * @param id
	 * @return
	 */
	@ResponseBody
    @RequestMapping(value="deleteEnterpriseById")
    public ResultBean deleteEnterpriseById(String id){
        ResultBean rb = new ResultBean();
        try{
            enterpriseBasicService.deleteByPrimaryKey(id);
            rb.isSuccess();
        }catch(Exception e){
            e.printStackTrace();
            rb.isFail();
        }
        return rb;
    }

}
