package org.yhguodu.iot.auth.common;

import javax.persistence.*;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import org.hibernate.validator.constraints.NotEmpty;

@Entity
@Table(name="user")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @NotEmpty(message = "用户名不能为空")
    private String username;
    @NotEmpty(message = "密码不能为空")
    private String password;

    private String mail;

    @ManyToMany(fetch=FetchType.EAGER)
    @JoinTable(name = "user_role", joinColumns = { @JoinColumn(name = "uid") }, inverseJoinColumns = {
            @JoinColumn(name = "rid") })
    private List<Role> roleList;// 一个用户具有多个角色

    public User() {
        super();
    }

    public User(String username, String password,String mail) {
        super();
        this.username = username;
        this.password = password;
        this.mail = mail;
    }

    // 省略 get set 方法

    @Transient
    public Set<String> getRolesName() {
        List<Role> roles = getRoleList();
        Set<String> set = new HashSet<String>();
        for (Role role : roles) {
            set.add(role.getRolename());
        }
        return set;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

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

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public List<Role> getRoleList() {
        return roleList;
    }

    public void setRoleList(List<Role> roleList) {
        this.roleList = roleList;
    }
}
