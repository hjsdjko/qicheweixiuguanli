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
 * 配件
 *
 * @author 
 * @email
 */
@TableName("peijian")
public class PeijianEntity<T> implements Serializable {
    private static final long serialVersionUID = 1L;


	public PeijianEntity() {

	}

	public PeijianEntity(T t) {
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
     * 配件编号
     */
    @TableField(value = "peijian_uuid_number")

    private String peijianUuidNumber;


    /**
     * 配件名称
     */
    @TableField(value = "peijian_name")

    private String peijianName;


    /**
     * 配件类型
     */
    @TableField(value = "peijian_types")

    private Integer peijianTypes;


    /**
     * 配件库存
     */
    @TableField(value = "peijian_kucun_number")

    private Integer peijianKucunNumber;


    /**
     * 单价
     */
    @TableField(value = "peijian_new_money")

    private Double peijianNewMoney;


    /**
     * 配件介绍
     */
    @TableField(value = "peijian_content")

    private String peijianContent;


    /**
     * 逻辑删除
     */
    @TableField(value = "peijian_delete")

    private Integer peijianDelete;


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
	 * 设置：配件编号
	 */
    public String getPeijianUuidNumber() {
        return peijianUuidNumber;
    }
    /**
	 * 获取：配件编号
	 */

    public void setPeijianUuidNumber(String peijianUuidNumber) {
        this.peijianUuidNumber = peijianUuidNumber;
    }
    /**
	 * 设置：配件名称
	 */
    public String getPeijianName() {
        return peijianName;
    }
    /**
	 * 获取：配件名称
	 */

    public void setPeijianName(String peijianName) {
        this.peijianName = peijianName;
    }
    /**
	 * 设置：配件类型
	 */
    public Integer getPeijianTypes() {
        return peijianTypes;
    }
    /**
	 * 获取：配件类型
	 */

    public void setPeijianTypes(Integer peijianTypes) {
        this.peijianTypes = peijianTypes;
    }
    /**
	 * 设置：配件库存
	 */
    public Integer getPeijianKucunNumber() {
        return peijianKucunNumber;
    }
    /**
	 * 获取：配件库存
	 */

    public void setPeijianKucunNumber(Integer peijianKucunNumber) {
        this.peijianKucunNumber = peijianKucunNumber;
    }
    /**
	 * 设置：单价
	 */
    public Double getPeijianNewMoney() {
        return peijianNewMoney;
    }
    /**
	 * 获取：单价
	 */

    public void setPeijianNewMoney(Double peijianNewMoney) {
        this.peijianNewMoney = peijianNewMoney;
    }
    /**
	 * 设置：配件介绍
	 */
    public String getPeijianContent() {
        return peijianContent;
    }
    /**
	 * 获取：配件介绍
	 */

    public void setPeijianContent(String peijianContent) {
        this.peijianContent = peijianContent;
    }
    /**
	 * 设置：逻辑删除
	 */
    public Integer getPeijianDelete() {
        return peijianDelete;
    }
    /**
	 * 获取：逻辑删除
	 */

    public void setPeijianDelete(Integer peijianDelete) {
        this.peijianDelete = peijianDelete;
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
        return "Peijian{" +
            "id=" + id +
            ", peijianUuidNumber=" + peijianUuidNumber +
            ", peijianName=" + peijianName +
            ", peijianTypes=" + peijianTypes +
            ", peijianKucunNumber=" + peijianKucunNumber +
            ", peijianNewMoney=" + peijianNewMoney +
            ", peijianContent=" + peijianContent +
            ", peijianDelete=" + peijianDelete +
            ", createTime=" + createTime +
        "}";
    }
}
