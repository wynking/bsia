package cn.com.pansky.otp5.association.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 
 * @ClassName MapController
 * @Description TODO(这里用一句话描述这个类的作用)
 * @author Administrator
 * @Date 2017年10月14日 下午1:50:24
 * @version 1.0.0
 */
@Controller
@RequestMapping("/map/")
public class MapController {


    /**
     * 跳转到
     * 
     * @return
     */
    @RequestMapping("mapPosi")
    public String mapPosi(HttpServletRequest req, HttpServletResponse resp) {
        String name = req.getParameter("name");
        req.setAttribute("name", name);
        String id = req.getParameter("id");
        req.setAttribute("id", id);
       return "association/map_posi";
    }

    

}
