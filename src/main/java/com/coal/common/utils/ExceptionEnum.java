package com.coal.common.utils;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public enum ExceptionEnum {
    VERIFICATION_CODE_ERROR(403,"验证码错误！"),
    ILLEGAL_DELETE_REQUEST(403,"非法删除请求"),
    INVALID_REQUEST_PARAM(400,"请求参数非法"),
    PURCHASE_NOT_FOUND (404,"未找到符合条件的申请采购单"),
    NOTICE_NOT_FOUND (404,"未找到符合条件的公告"),
    ROLE_NOT_FOUND (404,"角色不存在"),
    COMPANY_NOT_FOUND (404,"公司未找到"),
    USER_NOT_FOUND (404,"用户未找到"),
    LOGIN_FAIL (403,"用户名或密码错误"),
    INVAILID_USER_DATA_TYPE(400,"用户数据类型不正确"),
    ;
    private Integer code;
    private String msg;
}
