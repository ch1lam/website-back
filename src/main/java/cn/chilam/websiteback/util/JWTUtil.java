package cn.chilam.websiteback.util;

import cn.chilam.websiteback.common.entity.JWTResult;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.jsonwebtoken.*;
import org.apache.shiro.codec.Base64;
import org.springframework.web.bind.annotation.ResponseBody;


import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import java.util.Date;

/**
 * @program: website-back
 * @description: JWT工具
 * @author: chilam
 * @create: 2020-03-09 00:23
 **/
public class JWTUtil {
    // 服务器签名秘钥secret
    private static final String JWT_SECRET = "test_jwt_secret";
    // objm
    // pper
    private static final ObjectMapper MAPPER = new ObjectMapper();
    // toke过期
    public static final int JWT_ERRCODE_EXPIRE = 1005;
    // 验证不通过
    public static final int JWT_ERRCODE_FAIL = 1005;


    /**
     * @description: 生成base64加密后的key
     * @author: chilam
     * @return: javax.crypto.SecretKey
     * @date: 2020-03-14
     */
    public static SecretKey generalKey() {
        try {
            byte[] encodedKey = Base64.decode(JWT_SECRET);
            return new SecretKeySpec(encodedKey, 0, encodedKey.length, "AES");
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * @description: 签发JWT，创建token
     * @author: chilam
     * @param: id jwt的唯一身份标识，主要用来作为一次性token，从而回避重放攻击
     * @param: iss  jwt签发者
     * @param: subject jwt所面向的用户。 payload中记录的public claims。当前环境中就是用户登录名
     * @param: ttlMillis 有效期，单位毫秒
     * @return: token 一次性的，是为一个用户的有效登录周期准备的一个令牌。当用户退出或超时，令牌失效
     * @date: 2020-03-15
     */
    public static String createJWT(String id, String iss, String subject, long ttlMillis) {
        // 加密算法
        SignatureAlgorithm signatureAlgorithm = SignatureAlgorithm.HS256;
        // 当前时间
        long nowMillis = System.currentTimeMillis();
        // 当前时间的日期对象
        Date now = new Date(nowMillis);
        // 生成key
        SecretKey secretKey = generalKey();
        // 创建JWT的构件器。就是使用指定的信息和加密算法，生成token的工具
        JwtBuilder builder = Jwts.builder()
                .setId(id) // 设置身标志。就是一个客户端的唯一标记。如：可以使用用户的主键，客户端的ip，服务器生成的随机数据。
                .setIssuer(iss)
                .setSubject(subject)
                .setIssuedAt(now) // token的生成时间
                .signWith(signatureAlgorithm, secretKey); // 设定密匙和算法
        if (ttlMillis >= 0) {
            long expMillis = nowMillis + ttlMillis;
            Date expDate = new Date(expMillis); // token的失效时间
            builder.setExpiration(expDate);
        }
        return builder.compact(); // 生成token
    }


    /**
     * @description: 解析JWT字符串
     * @author: chilam
     * @param: jwt jwt就是服务器为客户端生成的签名数据，就是token
     * @return: io.jsonwebtoken.Claims
     * @date: 2020-03-15
     */
    public static Claims parseJWT(String jwt) throws Exception {
        SecretKey secretKey = generalKey();
        return Jwts.parser()
                .setSigningKey(secretKey)
                .parseClaimsJws(jwt)
                .getBody(); // 获取的就是token记录的payload数据。就是payload中保存的所有claims
    }

    /**
     * @description: 验证JWT
     * @author: chilam
     * @param: jwtStr
     * @return: checkResult
     * @date: 2020-03-15
     */
    public static JWTResult validateJWT(String jwtStr) {
        JWTResult checkResult = new JWTResult();
        Claims claims = null;
        try {
            claims = parseJWT(jwtStr);
            checkResult.setSuccess(true);
            checkResult.setClaims(claims);
        } catch (ExpiredJwtException e) { // token超时
            checkResult.setErrCode(JWT_ERRCODE_EXPIRE);
            checkResult.setSuccess(false);
        } catch (SignatureException e) { // 校验失败
            checkResult.setErrCode(JWT_ERRCODE_FAIL);
            checkResult.setSuccess(false);
        } catch (Exception e) {
            checkResult.setErrCode(JWT_ERRCODE_FAIL);
            checkResult.setSuccess(false);
        }
        return checkResult;
    }

    @ResponseBody
    public static String generalSubject(Object subObj) {
        try {
            return MAPPER.writeValueAsString(subObj);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
            return null;
        }
    }
}
