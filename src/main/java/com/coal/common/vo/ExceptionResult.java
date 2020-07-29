package com.coal.common.vo;

import com.coal.common.utils.ExceptionEnum;
import lombok.Data;

@Data
public class ExceptionResult {
    private Integer status;
    private String message;
    private Long timestamp;

    public ExceptionResult(ExceptionEnum em) {
        this.status=em.getCode();
        this.message=em.getMsg();
        this.timestamp= System.currentTimeMillis();
    }
}