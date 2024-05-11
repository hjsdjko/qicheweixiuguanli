package com.entity;

import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import java.lang.reflect.InvocationTargetException;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import org.springframework.format.annotation.DateTimeFormat;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.apache.commons.beanutils.BeanUtils;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.enums.FieldFill;
import com.baomidou.mybatisplus.enums.IdType;

/**
 * 维修项目
 *
 * @author 
 * @email
 */
@TableName("weixiuxiangmu")
public class WeixiuxiangmuEntity<T> implements Serializable {
    private static final long serialVersionUID = 1L;


	public WeixiuxiangmuEntity() {

	}

	public WeixiuxiangmuEntity(T t) {
		try {
			BeanUtils.copyProperties(this, t);
		} catch (IllegalAccessException | InvocationTargetException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}


    /**
     * 主键
     */
    @TableId(type = IdType.AUTO)
    @TableField(value = "id")

    private Integer id;


    /**
     * 标题
     */
    @TableField(value = "weixiuxiangmu_name")

    private String weixiuxiangmuName;


    /**
     * 封面
     */
    @TableField(value = "weixiuxiangmu_photo")

    private String weixiuxiangmuPhoto;


    /**
     * 维修类型
     */
    @TableField(value = "weixiuxiangmu_types")

    private Integer weixiuxiangmuTypes;


    /**
     * 维修价格
     */
    @TableField(value = "weixiuxiangmu_new_money")

    private Double weixiuxiangmuNewMoney;


    /**
     * 点击次数
     */
    @TableField(value = "weixiuxiangmu_clicknum")

    private Integer weixiuxiangmuClicknum;


    /**
     * 维修介绍
     */
    @TableField(value = "weixiuxiangmu_content")

    private String weixiuxiangmuContent;


    /**
     * 是否上架
     */
    @TableField(value = "shangxia_types")

    private Integer shangxiaTypes;


    /**
     * 逻辑删除
     */
    @TableField(value = "weixiuxiangmu_delete")

    private Integer weixiuxiangmuDelete;


    /**
     * 创建时间
     */
    @JsonFormat(locale="zh", timezone="GMT+8", pattern="yyyy-MM-dd HH:mm:ss")
	@DateTimeFormat
    @TableField(value = "create_time",fill = FieldFill.INSERT)

    private Date createTime;


    /**
	 * 设置：主键
	 */
    public Integer getId() {
        return id;
    }
    /**
	 * 获取：主键
	 */

    public void setId(Integer id) {
        this.id = id;
    }
    /**
	 * 设置：标题
	 */
    public String getWeixiuxiangmuName() {
        return weixiuxiangmuName;
    }
    /**
	 * 获取：标题
	 */

    public void setWeixiuxiangmuName(String weixiuxiangmuName) {
        this.weixiuxiangmuName = weixiuxiangmuName;
    }
    /**
	 * 设置：封面
	 */
    public String getWeixiuxiangmuPhoto() {
        return weixiuxiangmuPhoto;
    }
    /**
	 * 获取：封面
	 */

    public void setWeixiuxiangmuPhoto(String weixiuxiangmuPhoto) {
        this.weixiuxiangmuPhoto = weixiuxiangmuPhoto;
    }
    /**
	 * 设置：维修类型
	 */
    public Integer getWeixiuxiangmuTypes() {
        return weixiuxiangmuTypes;
    }
    /**
	 * 获取：维修类型
	 */

    public void setWeixiuxiangmuTypes(Integer weixiuxiangmuTypes) {
        this.weixiuxiangmuTypes = weixiuxiangmuTypes;
    }
    /**
	 * 设置：维修价格
	 */
    public Double getWeixiuxiangmuNewMoney() {
        return weixiuxiangmuNewMoney;
    }
    /**
	 * 获取：维修价格
	 */

    public void setWeixiuxiangmuNewMoney(Double weixiuxiangmuNewMoney) {
        this.weixiuxiangmuNewMoney = weixiuxiangmuNewMoney;
    }
    /**
	 * 设置：点击次数
	 */
    public Integer getWeixiuxiangmuClicknum() {
        return weixiuxiangmuClicknum;
    }
    /**
	 * 获取：点击次数
	 */

    public void setWeixiuxiangmuClicknum(Integer weixiuxiangmuClicknum) {
        this.weixiuxiangmuClicknum = weixiuxiangmuClicknum;
    }
    /**
	 * 设置：维修介绍
	 */
    public String getWeixiuxiangmuContent() {
        return weixiuxiangmuContent;
    }
    /**
	 * 获取：维修介绍
	 */

    public void setWeixiuxiangmuContent(String weixiuxiangmuContent) {
        this.weixiuxiangmuContent = weixiuxiangmuContent;
    }
    /**
	 * 设置：是否上架
	 */
    public Integer getShangxiaTypes() {
        return shangxiaTypes;
    }
    /**
	 * 获取：是否上架
	 */

    public void setShangxiaTypes(Integer shangxiaTypes) {
        this.shangxiaTypes = shangxiaTypes;
    }
    /**
	 * 设置：逻辑删除
	 */
    public Integer getWeixiuxiangmuDelete() {
        return weixiuxiangmuDelete;
    }
    /**
	 * 获取：逻辑删除
	 */

    public void setWeixiuxiangmuDelete(Integer weixiuxiangmuDelete) {
        this.weixiuxiangmuDelete = weixiuxiangmuDelete;
    }
    /**
	 * 设置：创建时间
	 */
    public Date getCreateTime() {
        return createTime;
    }
    /**
	 * 获取：创建时间
	 */

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    @Override
    public String toString() {
        return "Weixiuxiangmu{" +
            "id=" + id +
            ", weixiuxiangmuName=" + weixiuxiangmuName +
            ", weixiuxiangmuPhoto=" + weixiuxiangmuPhoto +
            ", weixiuxiangmuTypes=" + weixiuxiangmuTypes +
            ", weixiuxiangmuNewMoney=" + weixiuxiangmuNewMoney +
            ", weixiuxiangmuClicknum=" + weixiuxiangmuClicknum +
            ", weixiuxiangmuContent=" + weixiuxiangmuContent +
            ", shangxiaTypes=" + shangxiaTypes +
            ", weixiuxiangmuDelete=" + weixiuxiangmuDelete +
            ", createTime=" + createTime +
        "}";
    }
}
