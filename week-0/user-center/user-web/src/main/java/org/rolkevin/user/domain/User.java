package org.rolkevin.user.domain;

import org.hibernate.validator.constraints.Email;
import org.rolkevin.user.bean.validation.UserValid;
import org.rolkevin.user.bean.validation.group.AddUserGroup;
import org.rolkevin.user.bean.validation.group.UpdateUserGroup;

import javax.persistence.*;
import javax.validation.constraints.*;
import java.util.Objects;

/**
 * 用户领域对象
 *
 * @since 1.0
 */
@Entity
@Table(name = "users")
@UserValid(groups = {UpdateUserGroup.class, AddUserGroup.class})
public class User {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    @NotNull(message = "ID不能为空",groups = UpdateUserGroup.class)
    private Long id;

    @Column(name = "name")
    @NotNull(message = "用户名不能为空",groups = {UpdateUserGroup.class, AddUserGroup.class})
    private String name;

    @Column(name = "password")
    //@Size(min = 6,max = 32,message = "密码不能低于6位,大于32位",groups = {UpdateUserGroup.class, AddUserGroup.class})
    private String password;

    @Column(name = "email")
    @NotNull(message = "邮箱不能为空",groups = {UpdateUserGroup.class, AddUserGroup.class})
    @Email()
    private String email;

    @Column(name = "phonenumber")
    @NotNull(message = "电话号码不能为空")
    //@Pattern(regexp = "^[1][3,4,5,6,7,8,9][0-9]{9}$",message = "手机号码格式不合法",groups = {UpdateUserGroup.class, AddUserGroup.class})
    private String phoneNumber;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return Objects.equals(id, user.id) && Objects.equals(name, user.name) && Objects.equals(password, user.password) && Objects.equals(email, user.email) && Objects.equals(phoneNumber, user.phoneNumber);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, password, email, phoneNumber);
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", password='" + password + '\'' +
                ", email='" + email + '\'' +
                ", phoneNumber='" + phoneNumber + '\'' +
                '}';
    }
}
