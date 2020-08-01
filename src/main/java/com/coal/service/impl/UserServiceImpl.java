package com.coal.service.impl;

import com.coal.common.exception.CoalException;
import com.coal.common.utils.CheckUtils;
import com.coal.common.utils.CodeUtils;
import com.coal.common.utils.ConstantClassFiled;
import com.coal.common.utils.ExceptionEnum;
import com.coal.mapper.CompanyMapper;
import com.coal.mapper.RoleMapper;
import com.coal.mapper.UserMapper;
import com.coal.pojo.User;
import com.coal.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import tk.mybatis.mapper.entity.Example;

import java.util.Date;
import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper userMapper;
    @Autowired
    private CompanyMapper companyMapper;
    @Autowired
    private RoleMapper roleMapper;

    public Integer register(User user) {
        user.setId(null);
        user.setStatus(ConstantClassFiled.NORMAL_STATUS);
        String salt = CodeUtils.generateSalt();
        user.setSalt(salt);
        user.setPassword(CodeUtils.md5Hex(user.getPassword(), salt));
        user.setCreateTime(new Date());
        user.setRoleId(ConstantClassFiled.USER_ROLE_ID);
        return userMapper.insert(user);
    }

    public Integer updateInfo(User user) {
        user.setStatus(null);
        user.setRoleId(null);
        user.setPassword(null);
        user.setSalt(null);
        user.setCreateTime(null);
        user.setCompanyId(null);
        return userMapper.updateByPrimaryKeySelective(user);
    }

    public User login(String loginkey, String pwd) {
        User checkUser = new User();
        if (CheckUtils.isEmail(loginkey))
            checkUser.setEmail(loginkey);
        else if (CheckUtils.isPhone(loginkey))
            checkUser.setPhone(loginkey);
        else
            checkUser.setUserName(loginkey);
        List<User> userList = userMapper.select(checkUser);
        if (CollectionUtils.isEmpty(userList))
            throw new CoalException(ExceptionEnum.LOGIN_FAIL);
        User user = userList.get(0);
        user = fillCompanyAndRole(user);
        if (!user.getPassword().equals(CodeUtils.md5Hex(pwd, user.getSalt())))
            throw new CoalException(ExceptionEnum.LOGIN_FAIL);
        user.setLastLoginTime(new Date());
        userMapper.updateByPrimaryKeySelective(user);
        return user;
    }

    public User fillCompanyAndRole(User user) {
        if (user.getStatus() != ConstantClassFiled.NORMAL_STATUS && user.getRoleId() != ConstantClassFiled.USER_ROLE_ID)
            user.setRoleId(ConstantClassFiled.USER_ROLE_ID);
        user.setCompanyName(companyMapper.selectByPrimaryKey(user.getCompanyId()).getName());
        user.setRoleName(roleMapper.selectByPrimaryKey(user.getRoleId()).getName());
        return user;
    }

    public User getUserById(Integer id) {
        User user = userMapper.selectByPrimaryKey(id);
        user = fillCompanyAndRole(user);
        if (user == null)
            throw new CoalException(ExceptionEnum.USER_NOT_FOUND);
        return user;
    }

    public Integer changeRole(Integer userId, Integer roleId) {
        User user = new User();
        user.setRoleId(roleId);
        user.setStatus(0);
        Example example = new Example(User.class);
        example.createCriteria().andEqualTo("id", userId).andEqualTo("roleId", ConstantClassFiled.USER_ROLE_ID);
        return userMapper.updateByExampleSelective(user, example);
    }

    public Boolean updatePassword(Integer id, String oldpwd, String newpwd) {
        User checkUser = new User();
        checkUser.setId(id);
        User user = userMapper.selectOne(checkUser);
        if (user == null || !user.getPassword().equals(CodeUtils.md5Hex(oldpwd, user.getSalt()))) {
            return false;
        }
        checkUser.setPassword(CodeUtils.md5Hex(newpwd, user.getSalt()));
        return userMapper.updateByPrimaryKeySelective(checkUser) == 1;
    }

    public Boolean checkUserName(String userName) {
        //判断数据类型
        User record = new User();
        record.setUserName(userName);

        int count = userMapper.selectCount(record);
        return count == 0;
    }
}
