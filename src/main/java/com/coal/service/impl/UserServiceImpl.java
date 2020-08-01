package com.coal.service.impl;

import com.coal.common.exception.CoalException;
import com.coal.common.utils.*;
import com.coal.mapper.CompanyMapper;
import com.coal.mapper.RoleMapper;
import com.coal.mapper.UserMapper;
import com.coal.pojo.Coal;
import com.coal.pojo.User;
import com.coal.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import tk.mybatis.mapper.entity.Example;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

@Slf4j
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper userMapper;
    @Autowired
    private CompanyMapper companyMapper;
    @Autowired
    private RoleMapper roleMapper;
    @Autowired
    private StringRedisTemplate redisTemplate;
    @Autowired
    private AmqpTemplate amqpTemplate;

    public Integer register(User user, String code) {
        if (user.getPhone() == null)
            throw new CoalException(ExceptionEnum.INVAILID_USER_DATA_TYPE);
        String key = Tool.getRedisKey(user.getPhone());
        if (!code.equals(redisTemplate.opsForValue().get(key)))
            throw new CoalException(ExceptionEnum.VERIFICATION_CODE_ERROR);
        user.setId(null);
        user.setStatus(ConstantClassFiled.NORMAL_STATUS);
        String salt = CodeUtils.generateSalt();
        user.setSalt(salt);
        user.setPassword(CodeUtils.md5Hex(user.getPassword(), salt));
        user.setCreateTime(new Date());
        user.setRoleId(ConstantClassFiled.USER_ROLE_ID);
        int flag=userMapper.insert(user) ;
        if (flag==1) {
            try {
                redisTemplate.delete(key);
            } catch (Exception e) {
                log.error("【用户服务】删除Redis邮箱验证码失败，code：{}", code, e);
            }
        }
        return flag;
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

    public Boolean checkData(String data, Integer type) {
        //判断数据类型
        User record = new User();

        switch (type) {
            case 1:
                record.setUserName(data);
                break;
            case 2:
                record.setPhone(data);
                break;
            case 3:
                record.setEmail(data);
            default:
                throw new CoalException(ExceptionEnum.INVAILID_USER_DATA_TYPE);
        }

        int count = userMapper.selectCount(record);
        return count == 0;
    }
    public void sendMessageCode(String phone) {
        Map<String, String> msg = new HashMap<>();
        String code = NumberUtils.generateCode(6);
        msg.put("phone", phone);
        msg.put("code", code);
        amqpTemplate.convertAndSend("coal.sms.exchange", "coal.sms.code", msg);
        String key = Tool.getRedisKey(phone);
        //保存验证码
        redisTemplate.opsForValue().set(key, code, 5, TimeUnit.MINUTES);
    }

    @Override
    public void test(String phone, String code) {
        String key = Tool.getRedisKey(phone);
        if (!code.equals(redisTemplate.opsForValue().get(key)))
            throw new CoalException(ExceptionEnum.VERIFICATION_CODE_ERROR);
        try {
            redisTemplate.delete(key);
        } catch (Exception e) {
            log.error("【用户服务】删除Redis邮箱验证码失败，code：{}", code, e);
        }
    }
}
