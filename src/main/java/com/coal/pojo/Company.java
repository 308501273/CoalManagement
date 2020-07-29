package com.coal.pojo;

import lombok.Data;

import javax.persistence.*;

@Data
@Table(name = "tb_company")
public class Company {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    /**
     * 供应商名称
     */
    private String name;

    /**
     * 组织机构代码
     */
    @Column(name = "organization_code")
    private String organizationCode;

    /**
     * 供应商简称
     */
    @Column(name = "supplier_abbreviation")
    private String supplierAbbreviation;

    /**
     * 法人代表
     */
    @Column(name = "legal_representative")
    private String legalRepresentative;

    /**
     * 法人代表身份证号
     */
    @Column(name = "id_card_legal_representative")
    private String idCardLegalRepresentative;

    /**
     * 注册资金
     */
    @Column(name = "registered_capital")
    private Double registeredCapital;

    /**
     * 税务登记证代码
     */
    @Column(name = "tax_registration_certificate_code")
    private String taxRegistrationCertificateCode;

    /**
     * 经营执照编号
     */
    @Column(name = "business_license_number")
    private String businessLicenseNumber;

    /**
     * 开户银行
     */
    @Column(name = "bank_of_deposit")
    private String bankOfDeposit;

    /**
     * 开户银行账号
     */
    @Column(name = "bank_account")
    private String bankAccount;

    /**
     * 注册地址
     */
    private String address;

    /**
     * 联系人
     */
    private String contacts;

    /**
     * 传真
     */
    private String fax;

    /**
     * 联系人电话
     */
    private String phone;

    /**
     * 联系地址
     */
    @Column(name = "contacts_address")
    private String contactsAddress;

    /**
     * 邮政编号
     */
    @Column(name = "postal_code")
    private String postalCode;

    /**
     * 联系人邮箱
     */
    @Column(name = "contacts_email")
    private String contactsEmail;

    /**
     * 煤炭存放地点
     */
    @Column(name = "storage_location")
    private String storageLocation;

    /**
     * 煤炭存放量
     */
    @Column(name = "storage_capacity")
    private Double storageCapacity;

    /**
     * 状态
     */
    private Integer status;

    /**
     * 父公司，上级机构的id
     */
    private Integer parentid;

    /**
     * 公司类型【1:供应商，2:电厂，0:分（子）公司】
     */
    private Integer type;


}