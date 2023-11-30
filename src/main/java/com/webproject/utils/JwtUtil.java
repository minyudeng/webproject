package com.webproject.utils;

import com.alibaba.fastjson2.JSON;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import java.util.Base64;
import java.util.Date;
import java.util.UUID;

@Component
public class JwtUtil {
    // 有效期
    private static final long JWT_EXPIRE = 24*60*60*1000L;  //一天
    // 令牌秘钥
    private static final String JWT_KEY = "123456sfadgaaaaasjkhfsahfiahgieahgieahgiahdgihdighdaihg" +
            "fjsafkjahdfkjadgjkdagdakjfnalfnaldfnaigeaigsafsakfaksfgausfgusafgksajfjsakfbakufguwka" +
            "sadihasiofagoiaioegoieagfidlakfbdaifgioeageasadihaifowagfiwagfgwiagfwafwa" +
            "sahfvksajfaksjfkbafbaifwagfiwaogfasf";


    public  String createToken(Object data){
        // 当前时间
        long currentTime = System.currentTimeMillis();
        // 过期时间
        long expTime = currentTime+JWT_EXPIRE;
        // 构建jwt
        JwtBuilder builder = Jwts.builder()
                .setId(UUID.randomUUID()+"")
                .setSubject(JSON.toJSONString(data))
                .setIssuer("system")
                .setIssuedAt(new Date(currentTime))    //再加密
                .signWith(encodeSecret())
                .setExpiration(new Date(expTime));
        return builder.compact();
    }

    private SecretKey encodeSecret(){
        byte[] apiKeySecretBytes = JWT_KEY.getBytes();
        return new SecretKeySpec(apiKeySecretBytes, SignatureAlgorithm.HS256.getJcaName());
    }

    public Claims parseToken(String token){
        Claims body = Jwts.parser()
                .setSigningKey(encodeSecret())
                .parseClaimsJws(token)
                .getBody();
        return body;
    }

    public <T> T parseToken(String token,Class<T> clazz){
        Claims body = Jwts.parser()
                .setSigningKey(encodeSecret())
                .parseClaimsJws(token)
                .getBody();
        return JSON.parseObject(body.getSubject(),clazz);
    }

}
