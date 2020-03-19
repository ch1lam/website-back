package cn.chilam.websiteback.common.entity;

/**
 * @program: website-back
 * @description: 作为subject数据使用
 * @author: chilam
 * @create: 2020-03-16 00:08
 **/
public class JWTSubject {
    private String username;

    public JWTSubject() {
        super();
    }

    public JWTSubject(String username) {
        super();
        this.username = username;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}
