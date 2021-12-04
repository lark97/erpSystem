package cn.lark.admin.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpSession;

/**
 * @program: erp-manage
 * @description: 主页面
 * @author: lark
 * @create: 2021-12-02 17:29
 **/
@Controller
public class MainController {
    /**
     * 系统主页面
     * @return
     */
    @RequestMapping("main")
    public String main(){
        return "main";
    }

    /**
     * 系统登录页面
     * @return
     */
    @RequestMapping("index")
    public String index(){
        return "index";
    }

    /**
     * 系统欢迎页面
     * @return
     */
    @RequestMapping("welcome")
    public String welcome(){
        return "welcome";
    }

    @RequestMapping("signout")
    public String signout(HttpSession session){
        session.removeAttribute("user");
        return "redirect:index";
    }


}
