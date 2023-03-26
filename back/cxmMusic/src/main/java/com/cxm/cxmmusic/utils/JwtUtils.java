package com.cxm.cxmmusic.utils;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTCreator;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.exceptions.TokenExpiredException;
import com.auth0.jwt.interfaces.DecodedJWT;

import java.util.Calendar;
import java.util.Map;

public class JwtUtils {
    private static final String TOKEN = "token!Q@W3e4r";
    public static final Integer EXPIRES = 120;
    /**
     * 生成token
     * @param map  //传入payload
     * @return 返回token
     */
    public static String getToken(Map<String,String> map){
        JWTCreator.Builder builder = JWT.create();
        //设置playLoad参数
        map.forEach(builder::withClaim);
        //设置jwt有效的时长
        Calendar instance = Calendar.getInstance();
        instance.add(Calendar.MINUTE,EXPIRES);
        builder.withExpiresAt(instance.getTime());

        return builder.sign(Algorithm.HMAC256(TOKEN));
    }

    /**
     * 生成token 可设置过期时间
     * @param map  //传入payload
     * @return 返回token
     */
    public static String getToken(Map<String,String> map,Integer expires){
        JWTCreator.Builder builder = JWT.create();
        //设置playLoad参数
        map.forEach(builder::withClaim);
        //设置jwt有效的时长
        Calendar instance = Calendar.getInstance();
        instance.add(Calendar.MINUTE,expires);
        builder.withExpiresAt(instance.getTime());

        return builder.sign(Algorithm.HMAC256(TOKEN));
    }


    /**
     * 验证token
     * @param token
     * @return
     */
    public static void verify(String token) throws JWTVerificationException {
        JWT.require(Algorithm.HMAC256(TOKEN)).build().verify(token);
    }
    /**
     * 获取token中payload
     * @param token
     * @return
     */
    public static DecodedJWT getPayload(String token){
        return JWT.require(Algorithm.HMAC256(TOKEN)).build().verify(token);
    }
}