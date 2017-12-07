package cn.com.pansky.otp5.baseplatform.controller;

import java.util.ArrayList;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.castor.util.StringUtil;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.github.pagehelper.Page;

import cn.com.pansky.otp5.baseplatform.dao.po.Dictionary;
import cn.com.pansky.otp5.baseplatform.enums.EnableTypeEnum;
import cn.com.pansky.otp5.baseplatform.service.IDictionaryService;
import cn.com.pansky.otp5.common.ConstantUtil;
import cn.com.pansky.otp5.common.DictionaryUtil;
import cn.com.pansky.otp5.common.GridBean;
import cn.com.pansky.otp5.common.ResultBean;
import cn.com.pansky.otp5.common.TreeBean;
import cn.com.pansky.otp5.common.Util;
import cn.com.pansky.otp5.common.WebTools;
import net.sf.ezmorph.bean.MorphDynaBean;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

/**
 * @ClassName DictionaryController
 * @Description TODO(这里用一句话描述这个类的作用)
 * @author Administrator
 * @Date 2017年10月9日 下午4:42:47
 * @version 1.0.0
 */
@Controller
@RequestMapping("/dictionary/")
public class DictionaryController extends BaseController<Dictionary> {

    @Resource
    private IDictionaryService dictionaryService;

    /**
     * 跳转到
     * 
     * @return
     */
    @RequestMapping("list")
    public String index(HttpServletRequest req, HttpServletResponse resp) {
        return "dictionary/list";
    }

    /**
     * @Description (TODO这里用一句话描述这个方法的作用)
     * @param req
     * @param resp
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/showDictionaryTree")
    private List<TreeBean> showDictionaryTree(HttpServletRequest req, HttpServletResponse resp) {
      /*  Enumeration enu = req.getParameterNames();
        while (enu.hasMoreElements()) {
            String paraName = (String) enu.nextElement();
            System.out.println(paraName + ": " + req.getParameter(paraName));
        }*/
        List<TreeBean> tree = new ArrayList<TreeBean>();
        // System.out.println("AAA================>>>>" + req.getParameter("id"));
        // String id = req.getParameter("id");
        Map params = WebTools.getParameterMap(req);
        List<Dictionary> dics = dictionaryService.showDictionaryTree(params);
        for (Dictionary dic : dics) {
            TreeBean node = new TreeBean();
//            node.setId(dic.getCode());
            node.setId(dic.getId());
            node.setName(dic.getName());
            node.setIsParent(true);
            Map<String, String> others = new HashMap<String, String>();
            others.put("type",dic.getType());
            others.put("code",dic.getCode());
            node.setParams(others);
            tree.add(node);
        }
        return tree;
    }

    @ResponseBody
    @RequestMapping(value = "/findByPage")
    public GridBean<Dictionary> findByPage(HttpServletRequest req, HttpServletResponse resp) {
        Map params = WebTools.getParameterMap(req);
        Dictionary pDic = dictionaryService.selectByPrimaryKey(String.valueOf(params.get("pId")));
        params.put("pCode", pDic.getCode());
        params.put("type", pDic.getType());
        System.out.println("params=========findByPage============>>>>" + params);
        params.put("dics", DictionaryUtil.getAllEnableDics(req));
        Page<Dictionary> page = (Page<Dictionary>) dictionaryService.findByPage(params);
        GridBean<Dictionary> grid = new GridBean<Dictionary>();
        grid.setTotal(page.getTotal());
        grid.setRows(page);
        
        /*System.out.println(JSONObject.toJSONString(page));
        System.out.println(JSONObject.toJSONString(grid));*/

        return grid;
    }

    /**
     * @Description (TODO这里用一句话描述这个方法的作用)
     * @param vo
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "add")
    public ResultBean add(Dictionary dic) {
        /* userService.insert(vo); */
        System.out.println("=================>>>>" + dic);
        ResultBean rb = new ResultBean();
        try {
            dictionaryService.insert(dic);
            rb.isSuccess();
        } catch (Exception e) {
            e.printStackTrace();
            rb.isFail();
        }
        return rb;
    }

   /*
    * (非 Javadoc)
    * Description:
    * @see cn.com.pansky.otp5.baseplatform.controller.BaseController#selectByPrimaryKey(java.lang.String)
    */
    @ResponseBody
    @RequestMapping(value = "selectByPrimaryKey")
    public ResultBean selectByPrimaryKey(String id) {
        ResultBean rb = new ResultBean();
        try {
            Dictionary vo = dictionaryService.selectByPrimaryKey(id);
            rb.setData(vo);
            rb.isSuccess();
        } catch (Exception e) {
            e.printStackTrace();
            rb.isFail();
        }
        return rb;
    }

    /*
     * (非 Javadoc)
     * Description:
     * @see cn.com.pansky.otp5.baseplatform.controller.BaseController#updateByPrimaryKey(java.lang.Object)
     */
    @ResponseBody
    @RequestMapping(value="updateByPrimaryKey")
    public ResultBean updateByPrimaryKey(Dictionary vo) {
        ResultBean rb = new ResultBean();
        try{
            dictionaryService.updateByPrimaryKey(vo);
            rb.isSuccess();
        }catch(Exception e){
            e.printStackTrace();
            rb.isFail();
        }
        return rb;
    }

    /*
     * (非 Javadoc)
     * Description:
     * @see cn.com.pansky.otp5.baseplatform.controller.BaseController#deleteByPrimaryKey(java.lang.String)
     */
    @ResponseBody
    @RequestMapping(value="deleteByPrimaryKey")
    public ResultBean deleteByPrimaryKey(String id){
        ResultBean rb = new ResultBean();
        try{
            Dictionary vo = dictionaryService.selectByPrimaryKey(id);
            vo.setEnable(EnableTypeEnum.SC.getKey());
            dictionaryService.updateByPrimaryKey(vo);
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
     * @param path
     * @return
     */
    @ResponseBody
    @RequestMapping(value="importRegion")
    public ResultBean importRegion(String path){
        ResultBean rb = new ResultBean();
        try{
            String JsonContext = new Util().ReadFile("D:\\java\\workspace\\baseplatform\\src\\main\\webapp\\resources\\js\\bootstrap-chinese-region-master\\example\\lib\\bootstrap-chinese-region\\sql_areas.json");  
            JSONArray jsonArray = JSONArray.fromObject(JsonContext);  
            /*String s= java.net.URLDecoder.decode(JsonContext, "utf-8"); 
            JSONObject jsonArray = new JSONObject();*/  
      
            int size = jsonArray.size();  
            System.out.println("Size: " + size);  
            List<MorphDynaBean> listObject = jsonArray.toList(jsonArray);  
            
            List<Dictionary> dics = new ArrayList<Dictionary>();
            for(MorphDynaBean temp: listObject){  
                Dictionary dic = new Dictionary();
                dic.setId((String)temp.get("id"));
                dic.setCode((String)temp.get("id"));
                dic.setName((String)temp.get("cname"));
                dic.setpCode((String)temp.get("upid"));
                if(!StringUtils.isEmpty(temp.get("level"))){
                    dic.setLevel(Integer.valueOf(temp.get("level").toString()) );
                }
                dic.setType("region");
                dics.add(dic);
            }  
            dictionaryService.importRegion(dics);
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
     * @param path
     * @return
     */
    @ResponseBody
    @RequestMapping(value="getRegion")
    public ResultBean getRegion(HttpServletRequest req, HttpServletResponse resp){
        ResultBean rb = new ResultBean();
        try{
            List<Dictionary> region = DictionaryUtil.getAllEnableDicByType(req, ConstantUtil.DIC_REGION);
            rb.setData(region);
            rb.isSuccess();
        }catch(Exception e){
            e.printStackTrace();
            rb.isFail();
        }
        return rb;
    } 
    
    

    @ResponseBody
    @RequestMapping(value="getNameByCode")
    public ResultBean getNameByCode(HttpServletRequest req, HttpServletResponse resp){
        ResultBean rb = new ResultBean();
        try{
//          List<Dictionary> region = DictionaryUtil.getAllEnableDicByType(req, ConstantUtil.DIC_REGION);
            String code = req.getParameter("code");
            String name = DictionaryUtil.getRegionNameByCode(code, req);
//            System.out.println("name==============>>>>" + name);
            rb.setData(name);
            rb.isSuccess();
        }catch(Exception e){
            e.printStackTrace();
            rb.isFail();
        }
        return rb;
    } 
    
    @ResponseBody
    @RequestMapping(value="synDicToCache")
    public ResultBean synDicToCache(HttpServletRequest req, HttpServletResponse resp){
        ResultBean rb = new ResultBean();
        try{
            List<Dictionary> allDics= dictionaryService.getAllDictionaryByEnable("1");
            DictionaryUtil.setDicToCache(req.getServletContext(), allDics);
            rb.isSuccess();
        }catch(Exception e){
            e.printStackTrace();
            rb.isFail();
        }
        return rb;
    }  
    
    
    public static void main(String[] args){

    }
    
    
    

}
