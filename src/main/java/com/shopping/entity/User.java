package com.shopping.entity;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;



@Entity
@Table(name="user_main")
public class User {

    private int id;
    private String name;
    private String password;
    private String nickName;
    private int role;

    @Id
    @GenericGenerator(name = "generator", strategy = "increment") //设置主键自增
    @GeneratedValue(generator = "generator")

    @Column(name="id")
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Column(name="name")
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Column(name="password")
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Column(name="nick_name")
    public String getNickName() {
        return nickName;
    }


    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    @Column(name="role")
    public int getRole() {
        return role;
    }

    public void setRole(int role) {
        this.role = role;
    }
}
