package com.entity.model;

import com.entity.WeixiuxiangmuOrderEntity;

import com.baomidou.mybatisplus.annotations.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import java.util.Date;
import org.springframework.format.annotation.DateTimeFormat;
import java.io.Serializable;


/**
 * 维修订单
 * 接收传参的实体类
 *（实际开发中配合移动端接口开发手动去掉些没用的字段， 后端一般用entity就够用了）
 * 取自ModelAndView 的model名称
 */
public class WeixiuxiangmuOrderModel implements Serializable {
    private static final long serialVersionUID = 1L;




    /**
     * 主键
     */
    private Integer id;


    /**
     * 订单号
     */
    private String weixiuxiangmuOrderUuidNumber;


    /**
     * 维修项目
     */
    private Integer weixiuxiangmuId;


    /**
     * 用户
     */
    private Integer yonghuId;


    /**
     * 维修车辆
     */
    private Integer cheliangId;


    /**
     * 预约时间
     */
    @JsonFormat(locale="zh", timezone="GMT+8", pattern="yyyy-MM-dd HH:mm:ss")
	@DateTimeFormat
    private Date weixiuxiangmuOrderTime;


    /**
     * 实付价格
     */
    private Double weixiuxiangmuOrderTruePrice;


    /**
     * 订单类型
     */
    private Integer weixiuxiangmuOrderTypes;


    /**
     * 订单创建时间
     */
    @JsonFormat(locale="zh", timezone="GMT+8", pattern="yyyy-MM-dd HH:mm:ss")
	@DateTimeFormat
    private Date insertTime;


    /**
     * 创建时间 show3
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
	 * 获取：订单号
	 */
    public String getWeixiuxiangmuOrderUuidNumber() {
        return weixiuxiangmuOrderUuidNumber;
    }


    /**
	 * 设置：订单号
	 */
    public void setWeixiuxiangmuOrderUuidNumber(String weixiuxiangmuOrderUuidNumber) {
        this.weixiuxiangmuOrderUuidNumber = weixiuxiangmuOrderUuidNumber;
    }
    /**
	 * 获取：维修项目
	 */
    public Integer getWeixiuxiangmuId() {
        return weixiuxiangmuId;
    }


    /**
	 * 设置：维修项目
	 */
    public void setWeixiuxiangmuId(Integer weixiuxiangmuId) {
        this.weixiuxiangmuId = weixiuxiangmuId;
    }
    /**
	 * 获取：用户
	 */
    public Integer getYonghuId() {
        return yonghuId;
    }


    /**
	 * 设置：用户
	 */
    public void setYonghuId(Integer yonghuId) {
        this.yonghuId = yonghuId;
    }
    /**
	 * 获取：维修车辆
	 */
    public Integer getCheliangId() {
        return cheliangId;
    }


    /**
	 * 设置：维修车辆
	 */
    public void setCheliangId(Integer cheliangId) {
        this.cheliangId = cheliangId;
    }
    /**
	 * 获取：预约时间
	 */
    public Date getWeixiuxiangmuOrderTime() {
        return weixiuxiangmuOrderTime;
    }


    /**
	 * 设置：预约时间
	 */
    public void setWeixiuxiangmuOrderTime(Date weixiuxiangmuOrderTime) {
        this.weixiuxiangmuOrderTime = weixiuxiangmuOrderTime;
    }
    /**
	 * 获取：实付价格
	 */
    public Double getWeixiuxiangmuOrderTruePrice() {
        return weixiuxiangmuOrderTruePrice;
    }


    /**
	 * 设置：实付价格
	 */
    public void setWeixiuxiangmuOrderTruePrice(Double weixiuxiangmuOrderTruePrice) {
        this.weixiuxiangmuOrderTruePrice = weixiuxiangmuOrderTruePrice;
    }
    /**
	 * 获取：订单类型
	 */
    public Integer getWeixiuxiangmuOrderTypes() {
        return weixiuxiangmuOrderTypes;
    }


    /**
	 * 设置：订单类型
	 */
    public void setWeixiuxiangmuOrderTypes(Integer weixiuxiangmuOrderTypes) {
        this.weixiuxiangmuOrderTypes = weixiuxiangmuOrderTypes;
    }
    /**
	 * 获取：订单创建时间
	 */
    public Date getInsertTime() {
        return insertTime;
    }


    /**
	 * 设置：订单创建时间
	 */
    public void setInsertTime(Date insertTime) {
        this.insertTime = insertTime;
    }
    /**
	 * 获取：创建时间 show3
	 */
    public Date getCreateTime() {
        return createTime;
    }


    /**
	 * 设置：创建时间 show3
	 */
    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    }
