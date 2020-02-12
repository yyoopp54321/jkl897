package com.example.jkl.pojo;

import java.util.Date;
import javax.persistence.*;

@Table(name = "t_user")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "open_id")
    private Integer openId;

    private String username;

    private String password;

    @Column(name = "u_img")
    private String uImg;

    @Column(name = "nick_name")
    private String nickName;

    private Short role;

    private Integer phone;

    @Column(name = "last_money")
    private Double lastMoney;

    @Column(name = "create_time")
    private Date createTime;

    @Column(name = "update_time")
    private Date updateTime;

    /**
     * @return id
     */
    public Integer getId() {
        return id;
    }

    /**
     * @param id
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * @return open_id
     */
    public Integer getOpenId() {
        return openId;
    }

    /**
     * @param openId
     */
    public void setOpenId(Integer openId) {
        this.openId = openId;
    }

    /**
     * @return username
     */
    public String getUsername() {
        return username;
    }

    /**
     * @param username
     */
    public void setUsername(String username) {
        this.username = username == null ? null : username.trim();
    }

    /**
     * @return password
     */
    public String getPassword() {
        return password;
    }

    /**
     * @param password
     */
    public void setPassword(String password) {
        this.password = password == null ? null : password.trim();
    }

    /**
     * @return u_img
     */
    public String getuImg() {
        return uImg;
    }

    /**
     * @param uImg
     */
    public void setuImg(String uImg) {
        this.uImg = uImg == null ? null : uImg.trim();
    }

    /**
     * @return nick_name
     */
    public String getNickName() {
        return nickName;
    }

    /**
     * @param nickName
     */
    public void setNickName(String nickName) {
        this.nickName = nickName == null ? null : nickName.trim();
    }

    /**
     * @return role
     */
    public Short getRole() {
        return role;
    }

    /**
     * @param role
     */
    public void setRole(Short role) {
        this.role = role;
    }

    /**
     * @return phone
     */
    public Integer getPhone() {
        return phone;
    }

    /**
     * @param phone
     */
    public void setPhone(Integer phone) {
        this.phone = phone;
    }

    /**
     * @return last_money
     */
    public Double getLastMoney() {
        return lastMoney;
    }

    /**
     * @param lastMoney
     */
    public void setLastMoney(Double lastMoney) {
        this.lastMoney = lastMoney;
    }

    /**
     * @return create_time
     */
    public Date getCreateTime() {
        return createTime;
    }

    /**
     * @param createTime
     */
    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    /**
     * @return update_time
     */
    public Date getUpdateTime() {
        return updateTime;
    }

    /**
     * @param updateTime
     */
    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }
}