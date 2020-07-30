package com.coal.contorller;

import com.coal.common.utils.PageResult;
import com.coal.pojo.Role;
import com.coal.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("role")
public class RoleController {
    @Autowired
    private RoleService roleService;

    @GetMapping("all")
    public ResponseEntity<PageResult<Role>> getAllRoles(
            @RequestParam(value = "page", defaultValue = "1") Integer page,
            @RequestParam(value = "rows", defaultValue = "5") Integer rows
    ){
        return ResponseEntity.ok(roleService.getAllRoles(page,rows));
    }
    @GetMapping
    public ResponseEntity<Role>getRoleById(Integer id){
        return ResponseEntity.ok(roleService.getRoleById(id));
    }
    @PostMapping
    public ResponseEntity<Integer> addRole(Role role){
        return ResponseEntity.status(HttpStatus.CREATED).body(roleService.addRole(role));
    }
    @PutMapping
    public ResponseEntity<Integer>updateRole(Role role){
        return ResponseEntity.status(HttpStatus.ACCEPTED).body(roleService.updateRole(role));
    }
    @DeleteMapping("{id}")
    public ResponseEntity<Integer>deleteRole(@PathVariable("id") Integer id){
        return ResponseEntity.status(HttpStatus.ACCEPTED).body(roleService.deleteRole(id));
    }
}
