package cn.chilam.websiteback.common.entity;

/**
 * @program: website-back
 * @description: 发送给客户端的数据对象。统一数据类型
 * @author: chilam
 * @create: 2020-03-14 18:42
 **/
public class JWTResponseData {
    private Integer code; // 返回码
    private Object data; // 业务数据
    private String msg; // 返回描述
    private String token; // 身份标识，JWT生成的令牌

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}