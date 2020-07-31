package com.sunshine.bean;
import java.io.Serializable;

/**
 * Created
 */
public class Users implements Serializable {

    private static final long serialVersionUID = -8209492142737702621L;

    private int id;

    private String name;

    private String password;

    private String phone;

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    private Integer status;

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String username) {
        this.name = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }


    @Override
    public String toString() {
        return "Users{" +
                "id=" + id +
                ", username='" + name + '\'' +
                ", password='" + password + '\'' +
//                ", password_salt='" + password_salt + '\'' +
                '}';
    }
}
