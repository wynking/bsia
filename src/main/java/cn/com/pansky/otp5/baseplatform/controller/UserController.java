package cn.com.pansky.otp5.baseplatform.controller;

import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.github.pagehelper.Page;

import cn.com.pansky.otp5.association.controller.vo.AnnualReportVO;
import cn.com.pansky.otp5.baseplatform.controller.vo.UserVO;
import cn.com.pansky.otp5.baseplatform.enums.EnableTypeEnum;
import cn.com.pansky.otp5.baseplatform.service.IUserService;
import cn.com.pansky.otp5.common.GridBean;
import cn.com.pansky.otp5.common.ResultBean;
import cn.com.pansky.otp5.common.UserUtil;
import cn.com.pansky.otp5.common.WebTools;

/**
 * 
 * @ClassName UserController
 * @Description TODO(这里用一句话描述这个类的作用)
 * @author wyn
 * @Date 2017年8月30日 下午5:23:32
 * @version 1.0.0
 */
@Controller
@RequestMapping("/user/")
public class UserController extends BaseController<UserVO>{
    
    @Resource  
    private IUserService userService;
    

	/**
	 * 跳转到
	 * @return
	 */
	@RequestMapping("list")
	public String index(){
		return "auth/user/list";
	}
	
	@RequestMapping("toUpdate")
    public String toUpdate(){
	    System.out.println("===============>>>toUpdate");
        return "auth/user/user_update";
    }
    
	
	/**
	 * 
	 * @Description (TODO这里用一句话描述这个方法的作用)
	 * @param vo
	 * @return
	 */
	@ResponseBody
    @RequestMapping(value="add")
	public ResultBean add(UserVO vo){
	    ResultBean rb = new ResultBean();
        try{
            vo.setEnabled(EnableTypeEnum.QY.getKey());
            userService.insert(vo);
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
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value="findByPage")
	public GridBean<UserVO> findByPage(HttpServletRequest req,HttpServletResponse resp){
        Map params = WebTools.getParameterMap(req);
//        System.out.println("params=====================>>>>" + params);
//        params.put("dics", DictionaryUtil.getAllEnableDics(req));
        
        Page<UserVO> page = (Page<UserVO>) userService.findByPage(params);
        
        GridBean<UserVO> grid = new GridBean<UserVO>();
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
            UserVO vo = userService.selectByPrimaryKey(id);
            vo.setConfirmPassword(vo.getPassword());
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
    @RequestMapping(value="deleteUserById")
    public ResultBean deleteByPrimaryKey(String id) {
        ResultBean rb = new ResultBean();
        try{
            UserVO vo = userService.selectByPrimaryKey(id);
            vo.setEnabled(EnableTypeEnum.SC.getKey());
            userService.updateByPrimaryKey(vo);
            rb.isSuccess();
        }catch(Exception e){
            e.printStackTrace();
            rb.isFail();
        }
        return rb;
    }

    @ResponseBody
    @RequestMapping(value="updateByPrimaryKey")
    public ResultBean updateByPrimaryKey(UserVO vo) {
        ResultBean rb = new ResultBean();
        try{
            vo.setEnabled(EnableTypeEnum.QY.getKey());
            userService.updateByPrimaryKey(vo);
            UserUtil.setUser(request, vo);
            rb.isSuccess();
        }catch(Exception e){
            e.printStackTrace();
            rb.isFail();
        }
        return rb;
    }
    
   /* @Deprecated
    @Override
    public ResultBean updateByPrimaryKey(UserVO vo) {
        // TODO Auto-generated method stub
        return null;
    }*/
	
	
	
	
	
}
