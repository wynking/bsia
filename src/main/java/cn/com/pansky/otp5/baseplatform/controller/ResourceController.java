package cn.com.pansky.otp5.baseplatform.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.github.pagehelper.Page;

import cn.com.pansky.otp5.baseplatform.controller.vo.ResourceVO;
import cn.com.pansky.otp5.baseplatform.dao.po.Dictionary;
import cn.com.pansky.otp5.baseplatform.service.IResourceService;
import cn.com.pansky.otp5.common.GridBean;
import cn.com.pansky.otp5.common.ResultBean;
import cn.com.pansky.otp5.common.TreeBean;
import cn.com.pansky.otp5.common.WebTools;

/**
 * 
 * @ClassName ResourceController
 * @Description 资源信息管理Controller
 * @author wyn
 * @Date 2017年9月12日 上午12:09:08
 * @version 1.0.0
 */
@Controller
@RequestMapping("/resource/")
public class ResourceController extends BaseController<ResourceVO>{
    
    @Resource  
    private IResourceService resourceService;

	/**
	 * 跳转到
	 * @return
	 */
	@RequestMapping("list")
	public String index(){
		return "auth/resource/resource";
	}

    @Override
    @ResponseBody
    @RequestMapping(value="add")
    public ResultBean add(ResourceVO vo) {
        ResultBean rb = new ResultBean();
        try {
            resourceService.insert(vo);
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
     * @see cn.com.pansky.otp5.baseplatform.controller.BaseController#findByPage(javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse)
     */
    @Override
    @ResponseBody
    @RequestMapping(value="findByPage")
    public GridBean<ResourceVO> findByPage(HttpServletRequest req, HttpServletResponse resp) {
        Map<?, ?> params = WebTools.getParameterMap(req);
        Page<ResourceVO> page = (Page<ResourceVO>) resourceService.findByPage(params);
        GridBean<ResourceVO> grid = new GridBean<ResourceVO>();
        grid.setTotal(page.getTotal());
        grid.setRows(page);
        return grid;
    }

    /*
     * (非 Javadoc)
     * Description:
     * @see cn.com.pansky.otp5.baseplatform.controller.BaseController#selectByPrimaryKey(java.lang.String)
     */
    @Override
    @ResponseBody
    @RequestMapping(value="selectByPrimaryKey")
    public ResultBean selectByPrimaryKey(String id) {
        ResultBean rb = new ResultBean();
        try{
            ResourceVO vo = resourceService.selectByPrimaryKey(id);
            rb.setData(vo);
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
     * @see cn.com.pansky.otp5.baseplatform.controller.BaseController#updateByPrimaryKey(java.lang.Object)
     */
    @Override
    @ResponseBody
    @RequestMapping(value="updateByPrimaryKey")
    public ResultBean updateByPrimaryKey(ResourceVO vo) {
        ResultBean rb = new ResultBean();
        try{
            ResourceVO target =resourceService.selectByPrimaryKey(vo.getId());
            target.setName(vo.getName());
            target.setUrl(vo.getUrl());
            resourceService.updateByPrimaryKey(target);
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
    @Override
    public ResultBean deleteByPrimaryKey(String id) {
        // TODO Auto-generated method stub
        return null;
    }

    /**
     * @Description 更改记录状态
     * @param enabled
     * @param id
     * @return
     */
    @ResponseBody
    @RequestMapping(value="updateEnabledByPrimaryKey")
    public ResultBean updateEnabledByPrimaryKey(String enabled, String id) {
        ResultBean rb = new ResultBean();
        try{
            resourceService.updateEnabledByPrimaryKey(enabled, id);
            rb.isSuccess();
        }catch(Exception e){
            e.printStackTrace();
            rb.isFail();
        }
        return rb;
    }

    /**
     * 
     * @Description 获取资源树
     * @param req
     * @param resp
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/showResourceTree")
    public List<TreeBean> showResourceTree(HttpServletRequest req, HttpServletResponse resp) {
        List<TreeBean> tree = new ArrayList<TreeBean>();
        String pId = req.getParameter("id");
        List<ResourceVO> vos = resourceService.selectResourcesByPId(pId);
        for (ResourceVO vo : vos) {
            TreeBean node = new TreeBean();
            node.setId(vo.getId());
            node.setName(vo.getName());
            node.setIsParent(true);
            /*Map<String, String> others = new HashMap<String, String>();
            others.put("type",dic.getType());
            others.put("code",dic.getCode());
            node.setParams(others);*/
            tree.add(node);
        }
        return tree;
    }
    
    


    /**
     * 
     * @Description 获取资源树全部节点数据
     * @param req
     * @param resp
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/showAllResourceTree")
    public List<TreeBean> showAllResourceTree(HttpServletRequest req, HttpServletResponse resp) {
        List<TreeBean> tree = new ArrayList<TreeBean>();
        List<ResourceVO> vos = resourceService.selectAll();
        for (ResourceVO vo : vos) {
            TreeBean node = new TreeBean();
            node.setId(vo.getId());
            node.setName(vo.getName());
            node.setpId(vo.getpId());
            node.setIsParent(true);
            /*Map<String, String> others = new HashMap<String, String>();
            others.put("type",dic.getType());
            others.put("code",dic.getCode());
            node.setParams(others);*/
            tree.add(node);
        }
        return tree;
    }
    
    
    
    
    
    
    
	
}
