package com.coal.service;

import com.coal.common.utils.PageResult;
import com.coal.pojo.Company;
import com.coal.vo.CompaniesNames;

import java.util.List;

public interface CompanyService {

     Integer regeister(Company company);

     Integer updateCompany(Company company) ;

     Company getCompanyById(Integer id);

     PageResult<Company> getAllCompanies(Integer page, Integer rows, String sortBy, boolean desc, String key);

     List<CompaniesNames> getAllCompaniesName(String key);

     List<Company> getChirldCompanies(Integer companyId,List<Company> chirldCompanies);

     List<Integer> getChirldCompanies(Integer id);
}
