package cn.lark.admin.service;

import cn.lark.admin.pojo.User;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 * 用户表 服务类
 * </p>
 *
 * @author lark
 * @since 2021-12-02
 */
public interface IUserService extends IService<User> {

    /**
     * 用户登录方法
     * @param userName
     * @param password
     * @return
     */
    User login(String userName, String password);

    /**
     * 根据用户名查询用户记录
     * @param UserName
     * @return
     */
    User findUserByUserName(String UserName);

    void updateUserInfo(User user);

    void updatePassword(String userName,String oldPassword, String newPassWord, String confirmNewPassword);
}
