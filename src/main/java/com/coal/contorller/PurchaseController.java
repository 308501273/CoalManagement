package com.coal.contorller;

import com.coal.common.utils.PageResult;
import com.coal.pojo.PurchaseApplication;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


public interface PurchaseController {


     ResponseEntity<PageResult<PurchaseApplication>> getPurchaseApplications(
            @RequestParam(value = "page",defaultValue = "1")Integer page,
            @RequestParam(value = "rows",defaultValue = "5")Integer rows,
            @RequestParam(value = "sortBy", required = false) String sortBy,
            @RequestParam(value = "status",required = false)Integer status,
            @RequestParam(value = "desc", defaultValue = "false") boolean desc,
            @RequestParam(value = "key", required = false) String key,
            @RequestParam(value = "value",required = false) String value,
            Integer userId
    );

     ResponseEntity<PurchaseApplication> getPurchaseApplicationById(Integer id);

     ResponseEntity<Integer> checkPurchaseApplication(Integer checkUserId,Integer id,
                                                            @RequestParam(required = false) String remark,Integer options,
                                                            @RequestParam(defaultValue = "true")Boolean isReportUp );

}
