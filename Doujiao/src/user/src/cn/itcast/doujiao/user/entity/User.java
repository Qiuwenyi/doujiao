package cn.itcast.doujiao.user.entity;

public class User extends Person{
    private String username;
    private String password;
    private String IdNumber;
    private String Identity;

    private String role="NORMAL";

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

    public String getIdNumber() {
        return IdNumber;
    }

    public void setIdNumber(String idNumber) {
        IdNumber = idNumber;
    }

    //TODO 对身份identity进行分类
    public String getIdentity() {
        return Identity;
    }

    public void setIdentity(String Identity) {
        this.Identity = Identity;
    }
}

