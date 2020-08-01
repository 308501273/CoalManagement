package com.coal.service;


import com.coal.pojo.User;

public interface UserService {

    Integer register(User user);

    Integer updateInfo(User user);

    User login(String loginkey, String pwd);

    User fillCompanyAndRole(User user);

    User getUserById(Integer id);

    Integer changeRole(Integer userId, Integer roleId);

    Boolean updatePassword(Integer id, String oldpwd, String newpwd);

    Boolean checkData(String data, Integer type);
}
