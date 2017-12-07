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

import cn.com.pansky.otp5.baseplatform.controller.vo.ResourceVO;
import cn.com.pansky.otp5.baseplatform.controller.vo.RoleVO;
import cn.com.pansky.otp5.baseplatform.dao.po.RoleResource;
import cn.com.pansky.otp5.baseplatform.service.IRoleService;
import cn.com.pansky.otp5.common.GridBean;
import cn.com.pansky.otp5.common.ResultBean;
import cn.com.pansky.otp5.common.TreeBean;
import cn.com.pansky.otp5.common.WebTools;

/**
 * 
 * @ClassName RoleController
 * @Description TODO(这里用一句话描述这个类的作用)
 * @author Administrator
 * @Date 2017年12月1日 下午8:10:53
 * @version 1.0.0
 */
@Controller
@RequestMapping("/role")
public class RoleController extends BaseController<RoleVO> {

    @Resource  
    private IRoleService roleService;
	
	@RequestMapping("/list")
	public String index(){
		return "auth/role/role";
	}

	
    @Override
    @ResponseBody
    @RequestMapping(value="add")
    public ResultBean add(RoleVO vo) {
        ResultBean rb = new ResultBean();
        try {
            roleService.insert(vo);
            rb.isSuccess();
        } catch (Exception e) {
            e.printStackTrace();
            rb.isFail();
        }
        return rb;
    }

    @Override
    @ResponseBody
    @RequestMapping(value="findByPage")
    public GridBean<RoleVO> findByPage(HttpServletRequest req, HttpServletResponse resp) {
        Map<?, ?> params = WebTools.getParameterMap(req);
        Page<RoleVO> page = (Page<RoleVO>) roleService.findByPage(params);
        GridBean<RoleVO> grid = new GridBean<RoleVO>();
        grid.setTotal(page.getTotal());
        grid.setRows(page);
        return grid;
    }

    @Override
    @ResponseBody
    @RequestMapping(value="selectByPrimaryKey")
    public ResultBean selectByPrimaryKey(String id) {
        ResultBean rb = new ResultBean();
        try{
            RoleVO vo = roleService.selectByPrimaryKey(id);
            rb.setData(vo);
            rb.isSuccess();
        }catch(Exception e){
            e.printStackTrace();
            rb.isFail();
        }
        return rb;
    }

    @Override
    @ResponseBody
    @RequestMapping(value="updateByPrimaryKey")
    public ResultBean updateByPrimaryKey(RoleVO vo) {
        ResultBean rb = new ResultBean();
        try{
            RoleVO target =roleService.selectByPrimaryKey(vo.getId());
            target.setName(vo.getName());
            target.setRemark(vo.getRemark());
            target.setResourceIds(vo.getResourceIds());
            roleService.updateByPrimaryKey(target);
            rb.isSuccess();
        }catch(Exception e){
            e.printStackTrace();
            rb.isFail();
        }
        return rb;
    }

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
            roleService.updateEnabledByPrimaryKey(enabled, id);
            rb.isSuccess();
        }catch(Exception e){
            e.printStackTrace();
            rb.isFail();
        }
        return rb;
    }
    
    

    @ResponseBody
    @RequestMapping(value="selectCheckedNodes")
    public ResultBean selectCheckedNodes(String roleId) {
        ResultBean rb = new ResultBean();
        try{
            RoleVO vo = roleService.selectByPrimaryKey(roleId);
            List<RoleResource> rrs = vo.getRrs();
            rb.setData(rrs);
            rb.isSuccess();
        }catch(Exception e){
            e.printStackTrace();
            rb.isFail();
        }
        return rb;
    }
    
    
	
}
