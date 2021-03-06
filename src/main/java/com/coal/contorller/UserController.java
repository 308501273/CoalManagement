package com.coal.contorller;


import com.coal.common.utils.PageResult;

import com.coal.pojo.Notice;
import com.coal.pojo.Role;
import com.coal.pojo.User;

import com.coal.vo.CompaniesNames;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


public interface UserController {
    /**
     * 用户注册
     * @param user
     * @return
     */
     ResponseEntity<Integer> register(User user,String code);

    /**
     * 更新用户信息
     * @param user
     * @return
     */
     ResponseEntity<Integer> updateInfo(User user);
    /**
     * 用户登录（支持手机号，邮箱，用户名等登录方式）
     *
     * @param loginkey
     * @param pwd
     * @return
     */
     ResponseEntity<User> login(String loginkey,  String pwd) ;

    /**
     * 数据校验
     * @param data
     * @param type
     * @return
     */
     ResponseEntity<Boolean> checkeckData(@PathVariable("data") String data, @PathVariable("type") Integer type) ;

     ResponseEntity<User> getUserById(Integer id);

     ResponseEntity<Integer> changeRole(Integer userId,Integer roleId);

     ResponseEntity<Boolean> updatePassword(Integer id, String oldpwd, String newpwd) ;

     ResponseEntity<PageResult<Notice>> getNotices(
            @RequestParam(value = "page", defaultValue = "1") Integer page,
            @RequestParam(value = "rows", defaultValue = "5") Integer rows,
            @RequestParam(value = "sortBy", required = false) String sortBy,
            @RequestParam(value = "desc", defaultValue = "false") boolean desc,
            @RequestParam(value = "key", required = false) String key);

     ResponseEntity<PageResult<Role>> getAllRoles(
            @RequestParam(value = "page", defaultValue = "1") Integer page,
            @RequestParam(value = "rows", defaultValue = "5") Integer rows
    );

     ResponseEntity<List<CompaniesNames>> getAllCompaniesName(
            @RequestParam(value = "key", required = false) String key);

     ResponseEntity<Notice>getNoticeById(Integer id);

}
