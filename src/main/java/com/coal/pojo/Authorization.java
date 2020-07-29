package com.coal.pojo;

import lombok.Data;

import javax.persistence.*;

@Data
@Table(name = "tb_authorization")
public class Authorization {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    /**
     * 角色id
     */
    private Integer roleId;

    /**
     * 权限id
     */
    private Integer permissionId;

}