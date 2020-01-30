package com.example.jkl.pojo;

import lombok.Data;

import java.util.Date;
import javax.persistence.*;
@Data
@Table(name = "t_goods")
public class Goods {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "g_name")
    private String gName;

    @Column(name = "g_count")
    private Integer gCount;

    @Column(name = "g_price")
    private Double gPrice;

    private String img1;

    private String img2;

    private String img3;

    private String img4;

    @Column(name = "g_brief")
    private String gBrief;

    @Column(name = "g_state")
    private Short gState;

    @Column(name = "g_address")
    private String gAddress;

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
     * @return g_name
     */
    public String getgName() {
        return gName;
    }

    /**
     * @param gName
     */
    public void setgName(String gName) {
        this.gName = gName == null ? null : gName.trim();
    }

    /**
     * @return g_count
     */
    public Integer getgCount() {
        return gCount;
    }

    /**
     * @param gCount
     */
    public void setgCount(Integer gCount) {
        this.gCount = gCount;
    }

    /**
     * @return g_price
     */
    public Double getgPrice() {
        return gPrice;
    }

    /**
     * @param gPrice
     */
    public void setgPrice(Double gPrice) {
        this.gPrice = gPrice;
    }

    /**
     * @return img1
     */
    public String getImg1() {
        return img1;
    }

    /**
     * @param img1
     */
    public void setImg1(String img1) {
        this.img1 = img1 == null ? null : img1.trim();
    }

    /**
     * @return img2
     */
    public String getImg2() {
        return img2;
    }

    /**
     * @param img2
     */
    public void setImg2(String img2) {
        this.img2 = img2 == null ? null : img2.trim();
    }

    /**
     * @return img3
     */
    public String getImg3() {
        return img3;
    }

    /**
     * @param img3
     */
    public void setImg3(String img3) {
        this.img3 = img3 == null ? null : img3.trim();
    }

    /**
     * @return img4
     */
    public String getImg4() {
        return img4;
    }

    /**
     * @param img4
     */
    public void setImg4(String img4) {
        this.img4 = img4 == null ? null : img4.trim();
    }

    /**
     * @return g_brief
     */
    public String getgBrief() {
        return gBrief;
    }

    /**
     * @param gBrief
     */
    public void setgBrief(String gBrief) {
        this.gBrief = gBrief == null ? null : gBrief.trim();
    }

    /**
     * @return g_state
     */
    public Short getgState() {
        return gState;
    }

    /**
     * @param gState
     */
    public void setgState(Short gState) {
        this.gState = gState;
    }

    /**
     * @return g_address
     */
    public String getgAddress() {
        return gAddress;
    }

    /**
     * @param gAddress
     */
    public void setgAddress(String gAddress) {
        this.gAddress = gAddress == null ? null : gAddress.trim();
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