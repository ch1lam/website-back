package cn.chilam.websiteback.common.entity;

import io.jsonwebtoken.Claims;

/**
 * @program: website-back
 * @description: 结果对象，状态码
 * @author: chilam
 * @create: 2020-03-15 12:37
 **/
public class JWTResult {
    private int errCode;
    private boolean success;
    private Claims claims;

    public int getErrCode() {
        return errCode;
    }

    public void setErrCode(int errCode) {
        this.errCode = errCode;
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public Claims getClaims() {
        return claims;
    }

    public void setClaims(Claims claims) {
        this.claims = claims;
    }
}
