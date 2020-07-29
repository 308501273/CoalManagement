package com.coal.contorller;

import com.coal.common.utils.PageResult;
import com.coal.pojo.Company;
import com.coal.service.CompanyService;
import com.coal.vo.CompaniesNames;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("company")
public class CompanyController {

    @Autowired
    private CompanyService companyService;

    @GetMapping("companies_names")
    public ResponseEntity<List<CompaniesNames>> getAllCompaniesName(
            @RequestParam(value = "key", required = false) String key){
        return ResponseEntity.ok(companyService.getAllCompaniesName(key));
    }

    @GetMapping("all")
    public ResponseEntity<PageResult<Company>> getAllCompanies(
            @RequestParam(value = "page", defaultValue = "1") Integer page,
            @RequestParam(value = "rows", defaultValue = "5") Integer rows,
            @RequestParam(value = "sortBy", required = false) String sortBy,
            @RequestParam(value = "desc", defaultValue = "false") boolean desc,
            @RequestParam(value = "key", required = false) String key
    ){
        return ResponseEntity.ok(companyService.getAllCompanies(page, rows, sortBy, desc, key));
    }

    @GetMapping
    public ResponseEntity<Company> getCompanyById(Integer id){
        return ResponseEntity.ok(companyService.getCompanyById(id));
    }

    @PostMapping("regeister")
    public ResponseEntity<Integer> regeister(Company company) {
        return ResponseEntity.status(HttpStatus.CREATED).body(companyService.regeister(company));
    }

    @PutMapping
    public ResponseEntity<Integer> updateCompany(Company company) {
        return ResponseEntity.status(HttpStatus.ACCEPTED).body(companyService.updateCompany(company));
    }


}
