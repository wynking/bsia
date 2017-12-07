package cn.com.pansky.otp5.baseplatform.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.github.pagehelper.Page;

import cn.com.pansky.otp5.baseplatform.controller.vo.OrganizationVO;
import cn.com.pansky.otp5.baseplatform.service.IOrganizationService;
import cn.com.pansky.otp5.common.GridBean;
import cn.com.pansky.otp5.common.ResultBean;
import cn.com.pansky.otp5.common.TreeBean;
import cn.com.pansky.otp5.common.WebTools;

/**
 * 
 * @ClassName OrganizationController
 * @Description TODO(这里用一句话描述这个类的作用)
 * @author Administrator
 * @Date 2017年12月2日 下午10:49:29
 * @version 1.0.0
 */
@Controller
@RequestMapping("/organization/")
public class OrganizationController extends BaseController<OrganizationVO>{
    
    @Resource 
    private IOrganizationService organizationService;

	/**
	 * 跳转到
	 * @return
	 */
	@RequestMapping("list")
	public String index(){
		return "auth/organization/organization";
	}

    @Override
    @ResponseBody
    @RequestMapping(value="add")
    public ResultBean add(OrganizationVO vo) {
        ResultBean rb = new ResultBean();
        try {
            organizationService.insert(vo);
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
    public GridBean<OrganizationVO> findByPage(HttpServletRequest req, HttpServletResponse resp) {
        Map<?, ?> params = WebTools.getParameterMap(req);
        Page<OrganizationVO> page = (Page<OrganizationVO>) organizationService.findByPage(params);
        GridBean<OrganizationVO> grid = new GridBean<OrganizationVO>();
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
            OrganizationVO vo = organizationService.selectByPrimaryKey(id);
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
    public ResultBean updateByPrimaryKey(OrganizationVO vo) {
        ResultBean rb = new ResultBean();
        try{
            OrganizationVO target =organizationService.selectByPrimaryKey(vo.getId());
            target.setName(vo.getName());
            target.setCode(vo.getCode());
            organizationService.updateByPrimaryKey(target);
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
            organizationService.updateEnabledByPrimaryKey(enabled, id);
            rb.isSuccess();
        }catch(Exception e){
            e.printStackTrace();
            rb.isFail();
        }
        return rb;
    }

    /**
     * 
     * @Description 获取组织树
     * @param req
     * @param resp
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/showOrganizationTree")
    public List<TreeBean> showOrganizationTree(HttpServletRequest req, HttpServletResponse resp) {
        List<TreeBean> tree = new ArrayList<TreeBean>();
        String pId = req.getParameter("id");
        List<OrganizationVO> vos = organizationService.selectDatasByPId(pId);
        for (OrganizationVO vo : vos) {
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
     * @Description 获取组织树全部节点数据
     * @param req
     * @param resp
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/showAllOrganizationTree")
    public List<TreeBean> showAllOrganizationTree(HttpServletRequest req, HttpServletResponse resp) {
        List<TreeBean> tree = new ArrayList<TreeBean>();
        List<OrganizationVO> vos = organizationService.selectAll();
        for (OrganizationVO vo : vos) {
            TreeBean node = new TreeBean();
            node.setId(vo.getId());
            node.setName(vo.getName());
            node.setpId(vo.getpId());
            node.setIsParent(true);
            tree.add(node);
        }
        return tree;
    }
    
}
