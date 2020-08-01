package com.coal.contorller;

import com.coal.common.utils.PageResult;
import com.coal.pojo.Company;

import com.coal.vo.CompaniesNames;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

public interface CompanyController {

     ResponseEntity<List<CompaniesNames>> getAllCompaniesName(
            @RequestParam(value = "key", required = false) String key);

     ResponseEntity<PageResult<Company>> getAllCompanies(
            @RequestParam(value = "page", defaultValue = "1") Integer page,
            @RequestParam(value = "rows", defaultValue = "5") Integer rows,
            @RequestParam(value = "sortBy", required = false) String sortBy,
            @RequestParam(value = "desc", defaultValue = "false") boolean desc,
            @RequestParam(value = "key", required = false) String key);

     ResponseEntity<Company> getCompanyById(Integer id);

     ResponseEntity<Integer> regeister(Company company) ;

     ResponseEntity<Integer> updateCompany(Company company) ;

}
