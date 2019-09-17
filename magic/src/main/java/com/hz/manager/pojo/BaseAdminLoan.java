package com.hz.manager.pojo;

import lombok.Data;

import javax.persistence.*;
import java.util.Date;


@Data
@Table(name = "base_admin_loan")
public class BaseAdminLoan {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer loanID;

    /**
     * 角色名称
     */
    @Column(name = "loan_name")
    private String roleName;  //姓名

    @Column(name = "loan_date_application")
    private String loanApplication;   //申请日期

    @Column(name = "loan_phone")
    private String loanPhone;  //手机号

    @Column(name = "loan_quota")
    private String loanQuota; //申请额度

    @Column(name = "loan_repayment")
    private String loanRepayment; //还款方式

    @Column(name = "loan_term")
    private String loanTerm; //借款期限

    @Column(name = "loan_type")
    private String loanType; //贷款类型

    @Column(name = "loan_state")
    private String loanState; //贷款状态

    @Column(name = "loan_preliminary_date")
    private String loanPreliminary;   //初审时间

    @Column(name = "loan_review_date")
    private String loanReview;  //复审时间

    @Column(name = "loan_preliminary_personnel")
    private String preliminaryPersonnel;  //初审人

    @Column(name = "loan_review_personnel")
    private String reviewPersonnel;   //复审人
}
