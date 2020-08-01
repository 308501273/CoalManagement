package com.coal.service;

import com.coal.common.exception.CoalException;
import com.coal.common.utils.ConstantClassFiled;
import com.coal.common.utils.ExceptionEnum;
import com.coal.common.utils.PageResult;
import com.coal.mapper.RoleMapper;
import com.coal.mapper.UserMapper;
import com.coal.pojo.Role;
import com.coal.pojo.User;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;
import tk.mybatis.mapper.entity.Example;

import java.util.List;

public interface RoleService {

     PageResult<Role> getAllRoles(Integer page, Integer rows);

     Role getRoleById(Integer id);

     Integer addRole(Role role);

     Integer updateRole(Role role);

     Integer deleteRole(Integer id);
}
