package cn.lark.admin.service.impl;

import cn.lark.admin.pojo.User;
import cn.lark.admin.mapper.UserMapper;
import cn.lark.admin.service.IUserService;
import cn.lark.admin.utils.AssertUtil;
import cn.lark.admin.utils.StringUtils;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;

/**
 * <p>
 * 用户表 服务实现类
 * </p>
 *
 * @author lark
 * @since 2021-12-02
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements IUserService {

    @Resource
    private UserMapper userMapper;

    @Override
    public User login(String userName, String password) {
        AssertUtil.isTrue(StringUtils.isEmpty(userName),"用户名不能为空！");
        AssertUtil.isTrue(StringUtils.isEmpty(password),"密码不能为空！");
        User user = this.findUserByUserName(userName);
        AssertUtil.isTrue(user == null,"用户不存在或已注销");
        AssertUtil.isTrue(!user.getPassword().equals(password),"密码错误");
        return user;
    }

    @Override
    public User findUserByUserName(String userName) {
        return this.baseMapper.selectOne(new QueryWrapper<User>().eq("is_del",0).eq("user_name",userName));
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED,rollbackFor = Exception.class)
    public void updateUserInfo(User user) {
        /**
         * 用户名的判断（非空且唯一）
         */
        AssertUtil.isTrue(StringUtils.isEmpty(user.getUserName()),"用户名不能为空");
        User temp = this.findUserByUserName(user.getUserName());
        AssertUtil.isTrue(null != temp&&!(temp.getId().equals(user.getId())),"用户名重复");
        AssertUtil.isTrue(!(this.updateById(user)),"用户信息更新失败");
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED,rollbackFor = Exception.class)
    public void updatePassword(String userName,String oldPassword, String newPassWord, String confirmNewPassword) {
        User user = null;
        user = this.findUserByUserName(userName);
        AssertUtil.isTrue(user == null,"用户不存在或未登录");
        AssertUtil.isTrue(StringUtils.isEmpty(oldPassword),"请输入旧密码");
        AssertUtil.isTrue(StringUtils.isEmpty(newPassWord),"请输入新密码");
        AssertUtil.isTrue(StringUtils.isEmpty(confirmNewPassword),"请再输入新密码");
        //检查原始密码输入，比对新旧密码
        AssertUtil.isTrue(!user.getPassword().equals(oldPassword),"原始密码输入错误");
        AssertUtil.isTrue(newPassWord.equals(oldPassword),"新密码与原密码重复");
        AssertUtil.isTrue(!newPassWord.equals(confirmNewPassword),"新密码必须一致");
        user.setPassword(newPassWord);
        AssertUtil.isTrue(!(this.updateById(user)),"修改密码失败");
    }


}
