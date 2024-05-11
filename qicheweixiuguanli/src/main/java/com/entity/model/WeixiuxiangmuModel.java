package com.entity.model;

import com.entity.WeixiuxiangmuEntity;

import com.baomidou.mybatisplus.annotations.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import java.util.Date;
import org.springframework.format.annotation.DateTimeFormat;
import java.io.Serializable;


/**
 * 维修项目
 * 接收传参的实体类
 *（实际开发中配合移动端接口开发手动去掉些没用的字段， 后端一般用entity就够用了）
 * 取自ModelAndView 的model名称
 */
public class WeixiuxiangmuModel implements Serializable {
    private static final long serialVersionUID = 1L;




    /**
     * 主键
     */
    private Integer id;


    /**
     * 标题
     */
    private String weixiuxiangmuName;


    /**
     * 封面
     */
    private String weixiuxiangmuPhoto;


    /**
     * 维修类型
     */
    private Integer weixiuxiangmuTypes;


    /**
     * 维修价格
     */
    private Double weixiuxiangmuNewMoney;


    /**
     * 点击次数
     */
    private Integer weixiuxiangmuClicknum;


    /**
     * 维修介绍
     */
    private String weixiuxiangmuContent;


    /**
     * 是否上架
     */
    private Integer shangxiaTypes;


    /**
     * 逻辑删除
     */
    private Integer weixiuxiangmuDelete;


    /**
     * 创建时间  show1 show2 photoShow
     */
    @JsonFormat(locale="zh", timezone="GMT+8", pattern="yyyy-MM-dd HH:mm:ss")
	@DateTimeFormat
    private Date createTime;


    /**
	 * 获取：主键
	 */
    public Integer getId() {
        return id;
    }


    /**
	 * 设置：主键
	 */
    public void setId(Integer id) {
        this.id = id;
    }
    /**
	 * 获取：标题
	 */
    public String getWeixiuxiangmuName() {
        return weixiuxiangmuName;
    }


    /**
	 * 设置：标题
	 */
    public void setWeixiuxiangmuName(String weixiuxiangmuName) {
        this.weixiuxiangmuName = weixiuxiangmuName;
    }
    /**
	 * 获取：封面
	 */
    public String getWeixiuxiangmuPhoto() {
        return weixiuxiangmuPhoto;
    }


    /**
	 * 设置：封面
	 */
    public void setWeixiuxiangmuPhoto(String weixiuxiangmuPhoto) {
        this.weixiuxiangmuPhoto = weixiuxiangmuPhoto;
    }
    /**
	 * 获取：维修类型
	 */
    public Integer getWeixiuxiangmuTypes() {
        return weixiuxiangmuTypes;
    }


    /**
	 * 设置：维修类型
	 */
    public void setWeixiuxiangmuTypes(Integer weixiuxiangmuTypes) {
        this.weixiuxiangmuTypes = weixiuxiangmuTypes;
    }
    /**
	 * 获取：维修价格
	 */
    public Double getWeixiuxiangmuNewMoney() {
        return weixiuxiangmuNewMoney;
    }


    /**
	 * 设置：维修价格
	 */
    public void setWeixiuxiangmuNewMoney(Double weixiuxiangmuNewMoney) {
        this.weixiuxiangmuNewMoney = weixiuxiangmuNewMoney;
    }
    /**
	 * 获取：点击次数
	 */
    public Integer getWeixiuxiangmuClicknum() {
        return weixiuxiangmuClicknum;
    }


    /**
	 * 设置：点击次数
	 */
    public void setWeixiuxiangmuClicknum(Integer weixiuxiangmuClicknum) {
        this.weixiuxiangmuClicknum = weixiuxiangmuClicknum;
    }
    /**
	 * 获取：维修介绍
	 */
    public String getWeixiuxiangmuContent() {
        return weixiuxiangmuContent;
    }


    /**
	 * 设置：维修介绍
	 */
    public void setWeixiuxiangmuContent(String weixiuxiangmuContent) {
        this.weixiuxiangmuContent = weixiuxiangmuContent;
    }
    /**
	 * 获取：是否上架
	 */
    public Integer getShangxiaTypes() {
        return shangxiaTypes;
    }


    /**
	 * 设置：是否上架
	 */
    public void setShangxiaTypes(Integer shangxiaTypes) {
        this.shangxiaTypes = shangxiaTypes;
    }
    /**
	 * 获取：逻辑删除
	 */
    public Integer getWeixiuxiangmuDelete() {
        return weixiuxiangmuDelete;
    }


    /**
	 * 设置：逻辑删除
	 */
    public void setWeixiuxiangmuDelete(Integer weixiuxiangmuDelete) {
        this.weixiuxiangmuDelete = weixiuxiangmuDelete;
    }
    /**
	 * 获取：创建时间  show1 show2 photoShow
	 */
    public Date getCreateTime() {
        return createTime;
    }


    /**
	 * 设置：创建时间  show1 show2 photoShow
	 */
    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    }
