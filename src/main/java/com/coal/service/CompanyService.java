package com.coal.service;

import com.coal.common.exception.CoalException;
import com.coal.common.utils.ConstantClassFiled;
import com.coal.common.utils.ExceptionEnum;
import com.coal.common.utils.PageResult;
import com.coal.mapper.CompanyMapper;
import com.coal.pojo.Company;
import com.coal.pojo.Notice;
import com.coal.vo.CompaniesNames;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.RequestParam;
import tk.mybatis.mapper.entity.Example;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collector;
import java.util.stream.Collectors;

@Service
public class CompanyService {

    @Autowired
    private CompanyMapper companyMapper;

    private static CompaniesNames apply(Company company) {
        CompaniesNames companiesName = new CompaniesNames(company.getId(), company.getName());
        return companiesName;
    }

    public Integer regeister(Company company) {
        company.setId(null);
        company.setStatus(ConstantClassFiled.APPROVAL_STATUS);
        if(company.getType()==ConstantClassFiled.COMPANY_TYPE_SUPPLIER)
            company.setParentid(0);
        return companyMapper.insert(company);
    }

    public Integer updateCompany(Company company) {
        company.setStatus(ConstantClassFiled.COMPANY_APPROVAL_STATUS);
        return companyMapper.updateByPrimaryKey(company);
    }



    public Company getCompanyById(Integer id) {
        Company company = companyMapper.selectByPrimaryKey(id);
        if (company == null)
            throw new CoalException(ExceptionEnum.COMPANY_NOT_FOUND);
        return company;
    }

    public PageResult<Company> getAllCompanies(Integer page, Integer rows, String sortBy, boolean desc, String key) {
        PageHelper.startPage(page, rows);

        Example example = new Example(Company.class);
        if (StringUtils.isNotBlank(key)) {
            example.createCriteria().orLike("name", "%" + key + "%");
        }
        if (StringUtils.isNotBlank(sortBy)) {
            String orderByClause = sortBy + (desc ? " DESC" : " ASC");
            example.setOrderByClause(orderByClause);
        }
        example.createCriteria().andEqualTo("status", ConstantClassFiled.NORMAL_STATUS);
        List<Company> companyList = companyMapper.selectByExample(example);

        if (CollectionUtils.isEmpty(companyList)) {
            throw new CoalException(ExceptionEnum.COMPANY_NOT_FOUND);
        }
        PageInfo<Company> result = new PageInfo<>(companyList);
        
        return new PageResult<>(result.getTotal(), page, companyList.size(), companyList, rows);
    }

    public List<CompaniesNames> getAllCompaniesName(String key) {
        Example example = new Example(Company.class);
        if (StringUtils.isNotBlank(key)) {
            example.createCriteria().orLike("name", "%" + key + "%");
        }
        String orderByClause = "name DESC";
        example.setOrderByClause(orderByClause);
        List<Company> companyList = companyMapper.selectByExample(example);
        if (CollectionUtils.isEmpty(companyList)) {
            throw new CoalException(ExceptionEnum.COMPANY_NOT_FOUND);
        }
        List<CompaniesNames> companiesNames = companyList.stream()
                .map(CompanyService::apply).collect(Collectors.toList());
        return companiesNames;
    }
}
