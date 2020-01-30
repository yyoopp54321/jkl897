package com.example.jkl.pojo;

import lombok.Data;

import java.util.Date;
import javax.persistence.*;
@Data
@Table(name = "t_user_car_ship")
public class UserCarShip {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "u_id")
    private Integer uId;

    @Column(name = "c_id")
    private Integer cId;

    @Column(name = "create_time")
    private Date createTime;

    @Column(name = "updata_time")
    private Date updataTime;

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
     * @return u_id
     */
    public Integer getuId() {
        return uId;
    }

    /**
     * @param uId
     */
    public void setuId(Integer uId) {
        this.uId = uId;
    }

    /**
     * @return c_id
     */
    public Integer getcId() {
        return cId;
    }

    /**
     * @param cId
     */
    public void setcId(Integer cId) {
        this.cId = cId;
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
     * @return updata_time
     */
    public Date getUpdataTime() {
        return updataTime;
    }

    /**
     * @param updataTime
     */
    public void setUpdataTime(Date updataTime) {
        this.updataTime = updataTime;
    }
}