package cn.com.pansky.otp5.association.controller;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.com.pansky.otp5.association.controller.vo.AnalysisVO;
import cn.com.pansky.otp5.association.controller.vo.EnterpriseBasicVO;
import cn.com.pansky.otp5.association.dao.IKnowledgeRightDao;
import cn.com.pansky.otp5.association.dao.po.EnterpriseBasic;
import cn.com.pansky.otp5.association.dao.po.KnowledgeRight;
import cn.com.pansky.otp5.association.service.IEnterpriseBasicService;
import cn.com.pansky.otp5.baseplatform.enums.EnableTypeEnum;
import cn.com.pansky.otp5.common.ConstantUtil;
import cn.com.pansky.otp5.common.DateUtil;
import cn.com.pansky.otp5.common.DictionaryUtil;
import cn.com.pansky.otp5.common.ResultBean;
import cn.com.pansky.otp5.common.UserUtil;
import cn.com.pansky.otp5.common.WebTools;

/**
 * 
 * @ClassName AnalysisController
 * @Description TODO(这里用一句话描述这个类的作用)
 * @author Administrator
 * @Date 2017年11月15日 下午7:11:38
 * @version 1.0.0
 */
@Controller
@RequestMapping("/analysis/")
public class AnalysisController {

    @Resource  
    private IEnterpriseBasicService enterpriseBasicService;
    @Resource  
    private IKnowledgeRightDao knowledgeRightDao;

    @RequestMapping(value="toAnalysis")
    public String toAnalysis(HttpServletRequest req,HttpServletResponse resp){
       return "association/analysis";
    } 
    @RequestMapping(value="toMap")
    public String toMap(HttpServletRequest req,HttpServletResponse resp){
        return "association/map_analysis";
    }
    

    @RequestMapping(value="toPublic")
    public String toPublic(HttpServletRequest req,HttpServletResponse resp){
       return "association/analysis_public";
    }
    @RequestMapping(value="toHigh")
    public String toHigh(HttpServletRequest req,HttpServletResponse resp){
       return "association/analysis_high";
    }

    @RequestMapping(value="toMember")
    public String toMember(HttpServletRequest req,HttpServletResponse resp){
       return "association/analysis_member";
    }

    @RequestMapping(value="toKnowledge")
    public String toKnowledge(HttpServletRequest req,HttpServletResponse resp){
       return "association/analysis_knowledge";
    }
    
    @RequestMapping(value="toCustom")
    public String toCustom(HttpServletRequest req,HttpServletResponse resp){
       return "association/analysis_custom";
    }
    
    /**
     * 
     * @Description (TODO这里用一句话描述这个方法的作用)
     * @param vo
     * @return
     */
    @ResponseBody
    @RequestMapping(value="analysisPublic")
    public ResultBean analysisPublic(HttpServletRequest request){
        ResultBean rb = new ResultBean();
        try{
            Map<String, List<EnterpriseBasic>> map = enterpriseBasicService.findPublicEnterprise(EnableTypeEnum.QY.getKey());
            Set<String> keys = map.keySet();
            
            List<String> xAxis = new ArrayList<String>(); 
            List<String> yAxis = new ArrayList<String>(); 
            
            for(String key : keys){
                if(!StringUtils.isEmpty(key)){
                    xAxis.add(DictionaryUtil.getNameByCode(key, request));
                }else{
                    xAxis.add("其他");
                }
                yAxis.add(String.valueOf(map.get(key).size()));
            }
            
            AnalysisVO vo = new AnalysisVO();
            vo.setData1(xAxis);
            vo.setData2(yAxis);
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
     * @param request
     * @return
     */
    @ResponseBody
    @RequestMapping(value="analysisHigh")
    public ResultBean analysisHigh(HttpServletRequest request){
        ResultBean rb = new ResultBean();
        try{
            Map<String, List<EnterpriseBasic>> map = enterpriseBasicService.findHighEnterprise(EnableTypeEnum.QY.getKey());
            List<String> xAxis = new ArrayList<String>(); 
            List<String> yAxis = new ArrayList<String>(); 
            
            Set<String> keys = map.keySet();
            for(String key : keys){
                if(!StringUtils.isEmpty(key)){
                    xAxis.add(DictionaryUtil.getNameByCode(key, request));
                    
                }else{
                    xAxis.add("其他");
                }
                yAxis.add(String.valueOf(map.get(key).size()));
            }
            
            AnalysisVO vo = new AnalysisVO();
            vo.setData1(xAxis);
            vo.setData2(yAxis);
            rb.setData(vo);
            
            rb.isSuccess();
        }catch(Exception e){
            e.printStackTrace();
            rb.isFail();
        }
        return rb;
    }
    
    @ResponseBody
    @RequestMapping(value="analysisMember")
    public ResultBean analysisMember(HttpServletRequest request){
        ResultBean rb = new ResultBean();
        try{
            List<EnterpriseBasic> pos = enterpriseBasicService.findListAllEnterpriseByEnable(EnableTypeEnum.QY.getKey());
            Map<String, List<EnterpriseBasic>> map = new HashMap<String,List<EnterpriseBasic>>();
           
            for(EnterpriseBasic po : pos){
               Date date = po.getFirstTime();
                if(date != null){
                    String year = String.valueOf(DateUtil.getYear(date));
                    List<EnterpriseBasic> b = map.get(year);
                    if(b==null){
                        List<EnterpriseBasic> c = new ArrayList<EnterpriseBasic>();
                        if("1".equals(po.getIsMember())){
                            c.add(po);
                        }
                        map.put(year, c);
                    }else{
                        b.add(po);
                    }
                }
            }
            
            List<String> xAxis = new ArrayList<String>(); 
            List<String> yAxis = new ArrayList<String>(); 
            Set<String> keys = map.keySet();
            for(String key : keys){
                xAxis.add(key);
//                yAxis.add(String.valueOf(map.get(key).size()));
            }
            Collections.sort(xAxis);  
            for(String key : xAxis){
                yAxis.add(String.valueOf(map.get(key).size()));
            }
            
            AnalysisVO vo = new AnalysisVO();
            vo.setData1(xAxis);
            vo.setData2(yAxis);
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
     * @param request
     * @return
     */
    @ResponseBody
    @RequestMapping(value="analysisMap")
    public ResultBean analysisMap(HttpServletRequest request,EnterpriseBasicVO vo){
        ResultBean rb = new ResultBean();
        try{
            Map params = WebTools.getParameterMap(request);
            params.put(ConstantUtil.REQUEST, request);
            if(!StringUtils.isEmpty(request.getParameter("address"))){
                params.put("address", DictionaryUtil.codeToUp(request.getParameter("address")));
            }
            params.put("techs", vo.getTechs());
            params.put("industrys", vo.getIndustrys());
            params.put("lastYear", DateUtil.getYearNew(new Date())-1);
            //如果不是超级管理员角色,则只能查看自己所属得企业信息
           /* if(!"1".equals(UserUtil.getUser(request).getIsSuper())){
                params.put("owner", UserUtil.getUser(request).getId());
            }*/
            
            
            List<EnterpriseBasicVO> pos = enterpriseBasicService.findByParams(params);
            
            rb.setData(pos);
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
     * @param request
     * @return
     */
    @ResponseBody
    @RequestMapping(value="analysisCustom")
    public ResultBean analysisCustom(HttpServletRequest request,EnterpriseBasicVO vo){
        ResultBean rb = new ResultBean();
        try{
            Map params = WebTools.getParameterMap(request);
            if(!StringUtils.isEmpty(request.getParameter("address"))){
                params.put("address", DictionaryUtil.codeToUp(request.getParameter("address")));
            }
            params.put(ConstantUtil.REQUEST, request);
            params.put("techs", vo.getTechs());
            params.put("industrys", vo.getIndustrys());
            params.put("lastYear", DateUtil.getYearNew(new Date())-1);
            //如果不是超级管理员角色,则只能查看自己所属得企业信息
           /* if(!"1".equals(UserUtil.getUser(request).getIsSuper())){
                params.put("owner", UserUtil.getUser(request).getId());
            }*/
            
            
            List<EnterpriseBasicVO> vos = enterpriseBasicService.findByParams(params);
            
            Map<String, List<EnterpriseBasicVO>> map = new HashMap<String,List<EnterpriseBasicVO>>();
            
            for(EnterpriseBasicVO vo2 : vos){
              String areaCode = vo2.getAddress();
              List<EnterpriseBasicVO> b = map.get(areaCode);
              if(b==null){
                  List<EnterpriseBasicVO> c = new ArrayList<EnterpriseBasicVO>();
                  c.add(vo2);
                  map.put(areaCode, c);
              }else{
                  b.add(vo2);
              }
            }
            
            List<String> xAxis = new ArrayList<String>(); 
            List<String> yAxis = new ArrayList<String>(); 
            Set<String> keys = map.keySet();
            for(String key : keys){
                if(!StringUtils.isEmpty(key)){
                    xAxis.add(DictionaryUtil.getNameByCode(key, request));
                }else{
                    xAxis.add("其他");
                }
                yAxis.add(String.valueOf(map.get(key).size()));
            }
            
            AnalysisVO vo3 = new AnalysisVO();
            vo3.setData1(xAxis);
            vo3.setData2(yAxis);
            rb.setData(vo3);
            rb.isSuccess();
        }catch(Exception e){
            e.printStackTrace();
            rb.isFail();
        }
        return rb;
    }

    /**
     * 
     * @Description 知识产权汇总统计
     * @param request
     * @return
     */
    @ResponseBody
    @RequestMapping(value="analysisKnowledge")
    public ResultBean analysisKnowledge(HttpServletRequest request){
        ResultBean rb = new ResultBean();
        try{
            List<KnowledgeRight> pos = knowledgeRightDao.selectAll();
            
            Map<String, List<KnowledgeRight>> map = new HashMap<String,List<KnowledgeRight>>();
            
            for(KnowledgeRight po : pos){
                String year = String.valueOf(DateUtil.getYearNew(po.getPassTime()));
                if(map.get(year)==null){
                    List<KnowledgeRight> krs = new ArrayList<KnowledgeRight>();
                    krs.add(po);
                    map.put(year, krs);
                }else{
                    map.get(year).add(po);
                }
            }
            
            
            List<String> xAxis = new ArrayList<String>(); 
            List<String> yAxis = new ArrayList<String>(); 
            Set<String> keys = map.keySet();
            for(String key : keys){
                xAxis.add(key);
//                yAxis.add(String.valueOf(map.get(key).size()));
            }
            Collections.sort(xAxis);  
            for(String key : xAxis){
                yAxis.add(String.valueOf(map.get(key).size()));
            }
            
            AnalysisVO vo = new AnalysisVO();
            vo.setData1(xAxis);
            vo.setData2(yAxis);
            rb.setData(vo);
            rb.isSuccess();
        }catch(Exception e){
            e.printStackTrace();
            rb.isFail();
        }
        return rb;
    }
    
    
    
    
}
