package com.coal.service.impl;

import com.coal.common.exception.CoalException;
import com.coal.common.utils.ConstantClassFiled;
import com.coal.common.utils.ExceptionEnum;
import com.coal.common.utils.PageResult;
import com.coal.mapper.RoleMapper;
import com.coal.mapper.UserMapper;
import com.coal.pojo.Role;
import com.coal.pojo.User;
import com.coal.service.RoleService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;
import tk.mybatis.mapper.entity.Example;

import java.util.List;

@Service
public class RoleServiceImpl implements RoleService {
    @Autowired
    private RoleMapper roleMapper;
    @Autowired
    private UserMapper userMapper;

    public PageResult<Role> getAllRoles(Integer page, Integer rows) {
        PageHelper.startPage(page, rows);
        List<Role> roleList = roleMapper.selectAll();
        if (CollectionUtils.isEmpty(roleList)) {
            throw new CoalException(ExceptionEnum.NOTICE_NOT_FOUND);
        }
        PageInfo<Role> result = new PageInfo<>(roleList);
        return new PageResult<>(result.getTotal(), page, roleList.size(), roleList, rows);
    }

    public Role getRoleById(Integer id) {
        Role role = roleMapper.selectByPrimaryKey(id);
        if (role == null)
            throw new CoalException(ExceptionEnum.ROLE_NOT_FOUND);
        return role;
    }

    public Integer addRole(Role role) {
        role.setId(null);
        return roleMapper.insert(role);
    }

    public Integer updateRole(Role role) {
        return roleMapper.updateByPrimaryKeySelective(role);
    }
    @Transactional
    public Integer deleteRole(Integer id){
        if (id == ConstantClassFiled.USER_ROLE_ID || id == ConstantClassFiled.ADMIN_ROLE_ID)
            throw new CoalException(ExceptionEnum.ILLEGAL_DELETE_REQUEST);
        User user = new User();
        user.setStatus(ConstantClassFiled.NORMAL_STATUS);
        user.setRoleId(ConstantClassFiled.USER_ROLE_ID);
        Example example = new Example(User.class);
        example.createCriteria().andEqualTo("roleId",id);
        userMapper.updateByExampleSelective(user,example);
        return roleMapper.deleteByPrimaryKey(id);
    }
}
