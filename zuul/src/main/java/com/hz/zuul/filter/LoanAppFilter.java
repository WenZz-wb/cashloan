package com.hz.zuul.filter;

import com.hz.zuul.utils.JWTUtils;
import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import com.netflix.zuul.exception.ZuulException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.StringUtils;

import javax.servlet.http.HttpServletRequest;

/**
 * 验证数据真实性，token校验
 */

public class LoanAppFilter extends ZuulFilter {

    private  static  final Logger logger = LoggerFactory.getLogger(LoanAppFilter.class);

    /**
     * 返回字符串代表过滤器的类型
     *
     * @return
     */

    @Override
    public String filterType() {
        return "pre";
    }

    /**
     * 通过返回的int值来定义过滤器的执行顺序，数字越小优先级越高。
     *
     * @return
     */
    @Override
    public int filterOrder() {
        return 0;
    }

    /**
     * 返回一个Boolean值，判断该过滤器是否需要执行。返回true执行，返回false不执行。
     *
     * @return
     */
    @Override
    public boolean shouldFilter() {
        return true;
    }

    /**
     * 过滤器的具体业务逻辑
     *
     * @return
     * @throws ZuulException
     */
    @Override
    public Object run() throws ZuulException {
        RequestContext requestContext = RequestContext.getCurrentContext();
        HttpServletRequest request = requestContext.getRequest();
        //获取请求的参数
        //登录成功生成
        //验证用户是否登录,JWT来做
        String token = request.getParameter("token");
        logger.info("----------token:{}",token);
        if(!StringUtils.isEmpty(token)){
            boolean flag = JWTUtils.verify(token);
            if(!flag){
                logger.info("---token失效----");
                requestContext.setSendZuulResponse(false); // 过滤该请求，不对其进行路由
                requestContext.setResponseStatusCode(401); // 设置响应状态码
                requestContext.setResponseBody("无效的token"); // 设置响应状态码
            }
        }
        return null;
    }
}
