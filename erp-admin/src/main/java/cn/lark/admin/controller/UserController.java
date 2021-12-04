package cn.lark.admin.controller;

import cn.lark.admin.exceptions.ParamsException;
import cn.lark.admin.pojo.User;
import cn.lark.admin.service.IUserService;
import cn.lark.admin.vo.RespBean;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

/**
 * <p>
 * 用户表 前端控制器
 * </p>
 *
 * @author lark
 * @since 2021-12-02
 */
@Controller
@RequestMapping("/user")
public class UserController {

    @Resource
    private IUserService userService;

    @RequestMapping("login")
    @ResponseBody
    public RespBean login(String userName, String password, HttpSession session){
            User user = userService.login(userName,password);
            session.setAttribute("user",user);
            return RespBean.success("用户登录成功");
    }

    //重新点击设置的时候，发现更新完的资料并没有及时展示
    //所以方法就是重新加去数据库里面拿一下，并把他加到session里面
    @RequestMapping("setting")
    public String setting(HttpSession session){
        User user  = (User) session.getAttribute("user");
        //在这里重新做了一次查询，并把它加到session里面
        session.setAttribute("user",userService.getById(user.getId()));
        return "user/setting";
    }

    @RequestMapping("updateUserInfo")
    @ResponseBody//踩坑，一定要加这个交互信息，不然和前台沟通不了数据
    public RespBean updateUserInfo(User user){
        try {
            userService.updateUserInfo(user);
            return RespBean.success("用户更新成功");
        } catch (ParamsException e) {
            e.printStackTrace();
            return RespBean.error(e.getMsg());
        }catch (Exception e) {
            e.printStackTrace();
            return RespBean.error("用户信息更新失败!");
        }
    }


    @RequestMapping("password")
    public String password(){
        return "user/password";
    }


    @RequestMapping("updateUserPassword")//这里的路径必须与资料保持一致，不知道怎么修改？？
    @ResponseBody
    //这里的方法名必须与资料保持一致，也不知道怎么修改
    public RespBean updatePassword(HttpSession session,String oldPassword,String newPassword,String confirmPassword){
        try {
            User user = (User) session.getAttribute("user");
            userService.updatePassword(user.getUserName(),oldPassword,newPassword,confirmPassword);
            return RespBean.success("用户密码更新成功");
        } catch (ParamsException e) {
            e.printStackTrace();
            return RespBean.error(e.getMsg());
        }catch (Exception e) {
            e.printStackTrace();
            return RespBean.error("用户密码更新失败!");
        }
    }


}
