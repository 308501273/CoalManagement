package com.coal.service;

import com.coal.common.exception.CoalException;
import com.coal.common.utils.ExceptionEnum;
import com.coal.common.utils.PageResult;
import com.coal.mapper.CoalMapper;
import com.coal.mapper.CompanyMapper;
import com.coal.mapper.PurchaseApplicationMapper;
import com.coal.mapper.UserMapper;
import com.coal.pojo.Coal;
import com.coal.pojo.Company;
import com.coal.pojo.PurchaseApplication;
import com.coal.pojo.User;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import tk.mybatis.mapper.entity.Example;

import java.util.List;

@Service
public class PurchaseService {
    @Autowired
    private PurchaseApplicationMapper purchaseMapper;
    @Autowired
    private CompanyMapper companyMapper;
    @Autowired
    private UserMapper userMapper;
    @Autowired
    private CoalMapper coalMapper;

    public PageResult<PurchaseApplication> getPurchaseApplications(Integer page, Integer rows, String sortBy,
                                                                   Integer status, boolean desc, String key, String value) {
        PageHelper.startPage(page, rows);
        Example example = new Example(PurchaseApplication.class);
        if (StringUtils.isNotBlank(key)) {
            example.createCriteria().orLike(key, "%" + value + "%");
        }
        if (StringUtils.isNotBlank(sortBy)) {
            String orderByClause = sortBy + (desc ? " DESC" : " ASC");
            example.setOrderByClause(orderByClause);
        }
        if (status != null)
            example.and(example.createCriteria().andEqualTo("status", status));
        List<PurchaseApplication> purchaseList = purchaseMapper.selectByExample(example);

        if (CollectionUtils.isEmpty(purchaseList)) {
            throw new CoalException(ExceptionEnum.PURCHASE_NOT_FOUND);
        }
        PageInfo<PurchaseApplication> result = new PageInfo<>(purchaseList);
        for (PurchaseApplication purchase : purchaseList) {
            Company applicantCompany = companyMapper.selectByPrimaryKey(purchase.getApplicantCompanyId());
            if (applicantCompany != null)
                purchase.setApplicantCompanyName(applicantCompany.getName());
            Company executingCompany = companyMapper.selectByPrimaryKey(purchase.getExecutingCompanyId());
            if (executingCompany != null)
                purchase.setExecutingCompanyName(executingCompany.getName());
            User issuer = userMapper.selectByPrimaryKey(purchase.getIssuerId());
            if (issuer != null)
                purchase.setIssuserName(issuer.getName());
            User applicant = userMapper.selectByPrimaryKey(purchase.getApplicantId());
            if (applicant != null)
                purchase.setApplicantName(applicant.getName());
            Coal coal = coalMapper.selectByPrimaryKey(purchase.getCoalVarietyId());
            if (coal != null)
                purchase.setCoalVarietyName(coal.getName());
        }
        return new PageResult<>(result.getTotal(), page, purchaseList.size(), purchaseList, rows);
    }

    public PurchaseApplication getPurchaseApplicationById(Integer id) {
        return purchaseMapper.selectByPrimaryKey(id);
    }
}
