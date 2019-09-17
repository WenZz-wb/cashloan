package com.hz.userloan.utils;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * toekn生成验证工具
 */
public class JWTUtils {

    private static final long EXPIRE_TIME = 15 * 60 * 1000;

    private static final String TOKEN_SECRET = "thefirsttoken123";

    /**
     * 生成签名，
     * @param **username**
     * @param **password**
     * @return
     */
    public static String sign(String username, String password) {
        try {
            // 设置过期时间
            Date date = new Date(System.currentTimeMillis() + EXPIRE_TIME);
            // 私钥和加密算法
            Algorithm algorithm = Algorithm.HMAC256(TOKEN_SECRET);
            // 设置头部信息
            Map<String, Object> header = new HashMap<>(2);
            header.put("Type", "Jwt");
            header.put("alg", "HS256");
            // 返回token字符串
            return JWT.create()
                    .withHeader(header)
                    .withClaim("loginName", username)
                    .withClaim("pwd", password)
                    .withExpiresAt(date)
                    .sign(algorithm);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * 检验token是否正确
     * @param **token**
     * @return
     */
    public static boolean verify(String token){
        try {
            Algorithm algorithm = Algorithm.HMAC256(TOKEN_SECRET);
            JWTVerifier verifier = JWT.require(algorithm).build();
            DecodedJWT jwt = verifier.verify(token);
            return true;
        } catch (Exception e){
            return false;
        }
    }

    public static void main(String[] args) {
        String str = JWTUtils.sign("123","456");
        System.out.println(str);
        boolean flag = JWTUtils.verify("eyJhbGciOiJIUzI1NiIsIlR5cGUiOiJKd3QiLCJ0eXAiOiJKV1QifQ.eyJsb2dpbk5hbWUiOiIxMjMiLCJwd2QiOiI0NTYiLCJleHAiOjE1NjgwODEwOTZ9.AO7iC8s5ieXGxZv_gT1Gc5AD9L2F2Ay3nc1vbE88F_M");
        System.out.println(flag);

    }
}
