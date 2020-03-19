package cn.chilam.websiteback.pojo;

/**
 * @program: website-back
 * @description: 用户实体
 * @author: chilam
 * @create: 2020-03-01 22:53
 **/
public class User {
    private int id;
    private String username;
    private String password;
    private String role;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }
}
