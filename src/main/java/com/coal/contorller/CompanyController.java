package com.coal.contorller;

import com.coal.common.utils.PageResult;
import com.coal.pojo.Company;

import com.coal.vo.CompaniesNames;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

public interface CompanyController {
     /**
      * 获取所有公司的名字和id
      * @param key
      * @return
      */
     ResponseEntity<List<CompaniesNames>> getAllCompaniesName(
            @RequestParam(value = "key", required = false) String key);

     /**
      * 获取所有公司的信息
      * @param page
      * @param rows
      * @param sortBy
      * @param desc
      * @param key
      * @return
      */
     ResponseEntity<PageResult<Company>> getAllCompanies(
            @RequestParam(value = "page", defaultValue = "1") Integer page,
            @RequestParam(value = "rows", defaultValue = "5") Integer rows,
            @RequestParam(value = "sortBy", required = false) String sortBy,
            @RequestParam(value = "desc", defaultValue = "false") boolean desc,
            @RequestParam(value = "key", required = false) String key);

     /**
      * 根据id查询公司
      * @param id
      * @return
      */
     ResponseEntity<Company> getCompanyById(Integer id);

     /**
      * 公司注册
      * @param company
      * @return
      */
     ResponseEntity<Integer> regeister(Company company) ;

     /**
      * 修改公司信息
      * @param company
      * @return
      */
     ResponseEntity<Integer> updateCompany(Company company) ;

}
