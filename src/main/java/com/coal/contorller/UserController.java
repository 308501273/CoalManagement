package com.coal.contorller;

import com.coal.common.exception.CoalException;
import com.coal.common.utils.ExceptionEnum;
import com.coal.common.utils.PageResult;
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
public class UserController {
    @Autowired
    private UserService userService;
    @Autowired
    private NoticeService noticeService;
    @Autowired
    private RoleService roleService;
    @Autowired
    private CompanyService companyService;
    @PostMapping("register")
    public ResponseEntity<Integer> register(User user){
        return ResponseEntity.status(HttpStatus.CREATED).body(userService.register(user));
    }
    @PutMapping
    public ResponseEntity<Integer> updateInfo(User user){
        return ResponseEntity.status(HttpStatus.ACCEPTED).body(userService.updateInfo(user));
    }
    /**
     * 用户登录（支持手机号，邮箱，用户名等登录方式）
     *
     * @param loginkey
     * @param pwd
     * @return
     */
    @PostMapping("login")
    public ResponseEntity<User> login(String loginkey,  String pwd) {
        if (StringUtils.isBlank(loginkey) || StringUtils.isBlank(pwd))
            throw new CoalException(ExceptionEnum.INVALID_REQUEST_PARAM);
        return ResponseEntity.ok(userService.login(loginkey, pwd));
    }
    @GetMapping
    public ResponseEntity<User> getUserById(Integer id){
        return  ResponseEntity.ok(userService.getUserById(id));
    }
    @PutMapping("role")
    public ResponseEntity<Integer> changeRole(Integer userId,Integer roleId){
        return ResponseEntity.status(HttpStatus.ACCEPTED).body(userService.changeRole(userId,roleId));
    }
    @PutMapping("password")
    public ResponseEntity<Boolean> updatePassword(Integer id, String oldpwd, String newpwd) {
        return ResponseEntity.ok(userService.updatePassword(id, oldpwd, newpwd));
    }
    @GetMapping("notices")
    public ResponseEntity<PageResult<Notice>> getNotices(
            @RequestParam(value = "page", defaultValue = "1") Integer page,
            @RequestParam(value = "rows", defaultValue = "5") Integer rows,
            @RequestParam(value = "sortBy", required = false) String sortBy,
            @RequestParam(value = "desc", defaultValue = "false") boolean desc,
            @RequestParam(value = "key", required = false) String key) {
        return ResponseEntity.ok(noticeService.getNotices(page,rows,sortBy,desc,key));
    }
    @GetMapping("roles")
    public ResponseEntity<PageResult<Role>> getAllRoles(
            @RequestParam(value = "page", defaultValue = "1") Integer page,
            @RequestParam(value = "rows", defaultValue = "5") Integer rows
    ){
        return ResponseEntity.ok(roleService.getAllRoles(page,rows));
    }
    @GetMapping("companies")
    public ResponseEntity<List<CompaniesNames>> getAllCompaniesName(
            @RequestParam(value = "key", required = false) String key){
        return ResponseEntity.ok(companyService.getAllCompaniesName(key));
    }
    @GetMapping("notice")
    public ResponseEntity<Notice>getNoticeById(Integer id){
        return ResponseEntity.ok(noticeService.getNoticeById(id));
    }
}
