package com.coal.service;


import com.coal.common.utils.PageResult;
import com.coal.pojo.PurchaseApplication;

public interface PurchaseService {

     PageResult<PurchaseApplication> getPurchaseApplications(Integer userId,Integer page, Integer rows, String sortBy,
                                                                   Integer status, boolean desc, String key, String value) ;

     PurchaseApplication getPurchaseApplicationById(Integer id);

     Integer checkPurchaseApplication(Integer checkUserId, Integer id, String remark, Integer options,Boolean isReportUp) ;
}
