package com.hz.manager.service;

import com.hz.manager.dto.AdminLoanDTO;
import com.hz.manager.model.Loan;
import com.hz.manager.resp.PageDataResult;
import com.hz.manager.resp.ResponseResult;

public interface AdminLoanService {

    public ResponseResult addLoan(Loan loan);

    public PageDataResult  selectLoanList(AdminLoanDTO loan, Integer pageNum, Integer pageSize);

    public  ResponseResult deleteLoan(String phone);

}
