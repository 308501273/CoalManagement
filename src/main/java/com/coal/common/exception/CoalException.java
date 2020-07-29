package com.coal.common.exception;

import com.coal.common.utils.ExceptionEnum;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class CoalException extends RuntimeException {
    private ExceptionEnum exceptionEnum;
}
