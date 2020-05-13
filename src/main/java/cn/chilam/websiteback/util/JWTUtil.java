package cn.chilam.websiteback.util;

import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.JWT;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.auth0.jwt.exceptions.JWTDecodeException;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.DecodedJWT;

import java.io.UnsupportedEncodingException;
import java.util.Date;

/**
 * @program: website-back
 * @description: JWT工具
 * @author: chilam
 * @create: 2020-03-09 00:23
 **/
public class JWTUtil {
    // 过期时间 24 小时
    private static final long EXPIRE_TIME = 24 * 60 * 60 * 1000;
    // 密钥
    private static final String SECRET = "SHIRO+JWT";


    /**
     * 生成 token
     *
     * @param username 用户名
     * @return 加密的token
     */
    public static String createToken(String ID, String username) {
        try {
            Date date = new Date(System.currentTimeMillis() + EXPIRE_TIME);
            Algorithm algorithm = Algorithm.HMAC256(SECRET);
            // 附带username信息
            return JWT.create()
                    .withKeyId(ID)
                    .withClaim("username", username)
                    //到期时间
                    .withExpiresAt(date)
                    //创建一个新的JWT，并使用给定的算法进行标记
                    .sign(algorithm);
        } catch (JWTCreationException | UnsupportedEncodingException e) {
            return null;
        }
    }

    /**
     * @description: 校验 Token 是否正确
     * @author: chilam
     * @param: token
     * @param: username
     * @return: boolean
     * @date: 2020-04-04
     */
    public static boolean verify(String token, String username) {
        try {
            Algorithm algorithm = Algorithm.HMAC256(SECRET);
            //在token中附带了username信息
            JWTVerifier verifier = JWT.require(algorithm)
                    .withClaim("username", username)
                    .build();
            //验证 token
            verifier.verify(token);
            return true;
        } catch (JWTVerificationException | UnsupportedEncodingException exception) {
            return false;
        }
    }


    /**
     * @description: 获取 Token 中的用户名
     * @author: chilam
     * @param: token
     * @return: java.lang.String
     * @date: 2020-04-04
     */
    public static String getUsername(String token) {
        try {
            DecodedJWT jwt = JWT.decode(token);
            return jwt.getClaim("username").asString();
        } catch (JWTDecodeException e) {
            return null;
        }
    }

    /**
     * @description: 获取 Token 中的ID
     * @author: chilam
     * @param: token
     * @return: java.lang.String
     * @date: 2020-04-04
     */
    public static String getKeyId(String token) {
        try {
            DecodedJWT jwt = JWT.decode(token);
            return jwt.getKeyId();
        } catch (JWTDecodeException e) {
            return null;
        }
    }


}
