package com.coal.contorller;

import com.coal.common.utils.PageResult;
import com.coal.pojo.PurchaseApplication;
import com.coal.service.PurchaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("purchase")
public class PurchaseController {
    @Autowired
    private PurchaseService purchaseService;

    @GetMapping("search")
    public PageResult<PurchaseApplication> getPurchaseApplications(
            @RequestParam(value = "page",defaultValue = "1")Integer page,
            @RequestParam(value = "rows",defaultValue = "5")Integer rows,
            @RequestParam(value = "sortBy", required = false) String sortBy,
            @RequestParam(value = "status",required = false)Integer status,
            @RequestParam(value = "desc", defaultValue = "false") boolean desc,
            @RequestParam(value = "key", required = false) String key,
            @RequestParam(value = "value",required = false) String value
    ){
        return purchaseService.getPurchaseApplications(page,rows,sortBy,status,desc,key,value);
    }

}
