package com.hz.manager.controller.system;


import com.hz.manager.dto.AdminLoanDTO;
import com.hz.manager.model.Loan;
import com.hz.manager.resp.PageDataResult;
import com.hz.manager.resp.ResponseResult;
import com.hz.manager.service.AdminLoanService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/loan")
public class LoanSystemController {

    @Autowired
    AdminLoanService loanService;

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @RequestMapping("/information")
    public String loanInformation() {
        return "/loan/information";
    }


    @ResponseBody
    @RequestMapping(value = "/addLoan", method = RequestMethod.POST)
    public ResponseResult addLoan(Loan loan) {
        logger.info("======接收到添加贷款信息的请求:{}");
        ResponseResult responseResult = loanService.addLoan(loan);
        return responseResult;
    }

    @ResponseBody
    @RequestMapping("/getLoanList")
    public PageDataResult getLoanList(@RequestParam("pageNum") Integer pageNum, @RequestParam("pageSize") Integer pageSize, AdminLoanDTO loan) {
        logger.info("接收到查询贷款信息的请求loan:{}",loan);
        PageDataResult pdr = new PageDataResult();
        try {
            if (null == pageNum) {
                pageNum = 1;
            }
            if (null == pageSize) {
                pageSize = 10;
            }
            pdr = loanService.selectLoanList(loan,pageNum, pageSize);
            if (ObjectUtils.isEmpty(pdr)) {
                return pdr;
            }
            logger.info("查询贷款信息:{}", pdr);
        } catch (Exception e) {
            e.printStackTrace();
            logger.error("贷款信息列表查询异常！", e);
        }

        return pdr;
    }

    @ResponseBody
    @RequestMapping(value = "/deleteLoanStatus",method = RequestMethod.POST)
    public ResponseResult deleteLoan(String phone,String name) {
            logger.info("删除用户名为:{},手机号:{}的用户",name,phone);
        ResponseResult result =loanService.deleteLoan(phone);
        return result;
    }

}
