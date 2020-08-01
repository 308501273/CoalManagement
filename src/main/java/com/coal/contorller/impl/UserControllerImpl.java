package com.coal.contorller.impl;

import com.coal.common.exception.CoalException;
import com.coal.common.utils.ExceptionEnum;
import com.coal.common.utils.PageResult;
import com.coal.contorller.UserController;
import com.coal.pojo.Company;
import com.coal.pojo.Notice;
import com.coal.pojo.Role;
import com.coal.pojo.User;
import com.coal.service.CompanyService;
import com.coal.service.NoticeService;
import com.coal.service.RoleService;
import com.coal.service.UserService;
import com.coal.vo.CompaniesNames;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("user")
public class UserControllerImpl implements UserController {
    @Autowired
    private UserService userService;
    @Autowired
    private NoticeService noticeService;
    @Autowired
    private RoleService roleService;
    @Autowired
    private CompanyService companyService;
    @GetMapping("test")
    public void test(String phone,String code){
        userService.test(phone,code);
    }

    @PostMapping("register")
    public ResponseEntity<Integer> register(User user,String code) {
        return ResponseEntity.status(HttpStatus.CREATED).body(userService.register(user,code));
    }

    @PutMapping
    public ResponseEntity<Integer> updateInfo(User user) {
        return ResponseEntity.status(HttpStatus.ACCEPTED).body(userService.updateInfo(user));
    }

    @PostMapping("login")
    public ResponseEntity<User> login(String loginkey, String pwd) {
        if (StringUtils.isBlank(loginkey) || StringUtils.isBlank(pwd))
            throw new CoalException(ExceptionEnum.INVALID_REQUEST_PARAM);
        return ResponseEntity.ok(userService.login(loginkey, pwd));
    }

    @GetMapping("check/{data}/{type}")
    public ResponseEntity<Boolean> checkeckData(@PathVariable("data") String data, @PathVariable("type") Integer type) {
        //判断类型
        return ResponseEntity.ok(userService.checkData(data, type));
    }

    @GetMapping
    public ResponseEntity<User> getUserById(Integer id) {
        return ResponseEntity.ok(userService.getUserById(id));
    }

    @PutMapping("role")
    public ResponseEntity<Integer> changeRole(Integer userId, Integer roleId) {
        return ResponseEntity.status(HttpStatus.ACCEPTED).body(userService.changeRole(userId, roleId));
    }

    @PutMapping("password")
    public ResponseEntity<Boolean> updatePassword(Integer id, String oldpwd, String newpwd) {
        return ResponseEntity.status(HttpStatus.ACCEPTED).body(userService.updatePassword(id, oldpwd, newpwd));
    }

    @GetMapping("notices")
    public ResponseEntity<PageResult<Notice>> getNotices(
            @RequestParam(value = "page", defaultValue = "1") Integer page,
            @RequestParam(value = "rows", defaultValue = "5") Integer rows,
            @RequestParam(value = "sortBy", required = false) String sortBy,
            @RequestParam(value = "desc", defaultValue = "false") boolean desc,
            @RequestParam(value = "key", required = false) String key) {
        return ResponseEntity.ok(noticeService.getNotices(page, rows, sortBy, desc, key));
    }

    @GetMapping("roles")
    public ResponseEntity<PageResult<Role>> getAllRoles(
            @RequestParam(value = "page", defaultValue = "1") Integer page,
            @RequestParam(value = "rows", defaultValue = "5") Integer rows
    ) {
        return ResponseEntity.ok(roleService.getAllRoles(page, rows));
    }

    @GetMapping("companies")
    public ResponseEntity<List<CompaniesNames>> getAllCompaniesName(
            @RequestParam(value = "key", required = false) String key) {
        return ResponseEntity.ok(companyService.getAllCompaniesName(key));
    }

    @GetMapping("notice")
    public ResponseEntity<Notice> getNoticeById(Integer id) {
        return ResponseEntity.ok(noticeService.getNoticeById(id));
    }

    /**
     * 获取手机验证码（用于绑定手机号）
     *
     * @param phone
     * @return
     */
    @GetMapping("phonecode")
    public ResponseEntity<Void> getMessageCode(String phone) {
        userService.sendMessageCode(phone);
        return ResponseEntity.ok().build();
    }


}
