package com.coal.contorller;

import com.coal.common.utils.PageResult;
import com.coal.pojo.Role;
import com.coal.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

public interface RoleController {

     ResponseEntity<PageResult<Role>> getAllRoles(
            @RequestParam(value = "page", defaultValue = "1") Integer page,
            @RequestParam(value = "rows", defaultValue = "5") Integer rows
    );

     ResponseEntity<Role>getRoleById(Integer id);

     ResponseEntity<Integer> addRole(Role role);

     ResponseEntity<Integer>updateRole(Role role);

     ResponseEntity<Integer>deleteRole(@PathVariable("id") Integer id);
}
