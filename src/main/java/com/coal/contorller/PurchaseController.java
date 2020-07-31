package com.coal.contorller;

import com.coal.common.utils.PageResult;
import com.coal.pojo.PurchaseApplication;
import com.coal.service.PurchaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.persistence.Id;

@RestController
@RequestMapping("purchase")
public class PurchaseController {
    @Autowired
    private PurchaseService purchaseService;

    @GetMapping("search")
    public ResponseEntity<PageResult<PurchaseApplication>> getPurchaseApplications(
            @RequestParam(value = "page",defaultValue = "1")Integer page,
            @RequestParam(value = "rows",defaultValue = "5")Integer rows,
            @RequestParam(value = "sortBy", required = false) String sortBy,
            @RequestParam(value = "status",required = false)Integer status,
            @RequestParam(value = "desc", defaultValue = "false") boolean desc,
            @RequestParam(value = "key", required = false) String key,
            @RequestParam(value = "value",required = false) String value
    ){
        return ResponseEntity.ok(purchaseService.getPurchaseApplications(page,rows,sortBy,status,desc,key,value));
    }
    @GetMapping
    public ResponseEntity<PurchaseApplication> getPurchaseApplicationById(Integer id){
        return ResponseEntity.ok(purchaseService.getPurchaseApplicationById(id));
    }



}
