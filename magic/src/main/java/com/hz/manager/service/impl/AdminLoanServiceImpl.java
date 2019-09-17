package com.hz.manager.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.hz.manager.dao.BaseAdminLoanMapper;
import com.hz.manager.dto.AdminLoanDTO;
import com.hz.manager.dto.AdminUserDTO;
import com.hz.manager.model.Loan;
import com.hz.manager.resp.PageDataResult;
import com.hz.manager.resp.ResponseResult;
import com.hz.manager.service.AdminLoanService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AdminLoanServiceImpl implements AdminLoanService {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    BaseAdminLoanMapper loanMapper;


    @Override
    public ResponseResult addLoan(Loan loan) {
        logger.info(loan.toString());
        int result = loanMapper.addLoan(loan);
        if (result == 1) {
            return ResponseResult.success();
        } else {
            return ResponseResult.erro();
        }

    }

    @Override
    public PageDataResult selectLoanList(AdminLoanDTO loan, Integer pageNum, Integer pageSize) {
        PageDataResult page = new PageDataResult();
        logger.info("pageNum:{},pageSize:{}", pageNum, pageSize);
        PageHelper.startPage(pageNum, pageSize);
        List<Loan> list = loanMapper.selectLoanList(loan);
        if (list.size() != 0) {
            PageInfo<Loan> pageInfo = new PageInfo<>(list);
            page.setList(list);
            page.setTotals((int) pageInfo.getTotal());
        }
        return page;
    }

    @Override
    public ResponseResult deleteLoan(String phone) {
        int x = loanMapper.deleteLoan(phone);
        if(x==1){
            return  ResponseResult.success();
        }
        return ResponseResult.erro();
    }
}
