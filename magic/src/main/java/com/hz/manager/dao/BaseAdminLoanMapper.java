package com.hz.manager.dao;

import com.hz.manager.dto.AdminLoanDTO;
import com.hz.manager.model.Loan;
import com.hz.manager.pojo.BaseAdminLoan;
import org.springframework.stereotype.Repository;
import tk.mapper.MyMapper;

import java.util.List;

@Repository
public interface BaseAdminLoanMapper extends MyMapper<BaseAdminLoan> {

       int addLoan(Loan loan);

       List<Loan>   selectLoanList(AdminLoanDTO loan);

       Integer deleteLoan(String phone);

}
