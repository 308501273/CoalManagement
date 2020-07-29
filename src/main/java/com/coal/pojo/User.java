package com.coal.pojo;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import java.util.Date;
import javax.persistence.*;

@Data
@Table(name = "tb_user")
public class User {
    /**
     * 主键id
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    /**
     * 用户名
     */
    @Column(name = "user_name")
    private String userName;

    /**
     * 密码
     */
    @JsonIgnore
    private String password;

    /**
     * 姓名
     */
    private String name;

    /**
     * 手机号
     */
    private String phone;

    /**
     * 邮箱
     */
    private String email;

    /**
     * 所属角色id
     */
    @Column(name = "role_id")
    private Integer roleId;

    @Transient
    private String roleName;

    /**
     * 所属公司或者电厂的id
     */
    @Column(name = "company_id")
    private Integer companyId;

    @Transient
    private String companyName;

    /**
     * 公司部门
     */
    private String department;

    /**
     * 用户状态
     */
    @JsonIgnore
    private Integer status;

    /**
     * 加密盐值
     */
    @JsonIgnore
    private String salt;

    /**
     * 创建时间
     */
    @Column(name = "create_time")
    @JsonFormat(locale = "zh", timezone = "GMT+8", pattern = "yyyy-MM-dd HH:mm:ss")
    private Date createTime;

    /**
     * 上次登录时间
     */
    @Column(name = "last_login_time")
    @JsonFormat(locale = "zh", timezone = "GMT+8", pattern = "yyyy-MM-dd HH:mm:ss")
    private Date lastLoginTime;

}