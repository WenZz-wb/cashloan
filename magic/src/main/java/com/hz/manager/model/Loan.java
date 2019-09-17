package com.hz.manager.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Date;


@Data
@NoArgsConstructor
@AllArgsConstructor
public class Loan {

    private Integer loanID;
    private String loanName;   //姓名
    private String loanApplication;   //申请日期
    private String loanPhone;  //手机号
    private String loanQuota; //申请额度
    private String loanRepayment; //还款方式
    private String loanTerm; //借款期限
    private String loanType; //贷款类型
    private String loanState; //贷款状态
    private String loanPreliminary;   //初审时间
    private String loanReview;  //复审时间
    private String preliminaryPersonnel;  //初审人
    private String reviewPersonnel;   //复审人


}
