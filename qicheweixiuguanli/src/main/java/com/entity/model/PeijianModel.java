package com.entity.model;

import com.entity.PeijianEntity;

import com.baomidou.mybatisplus.annotations.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import java.util.Date;
import org.springframework.format.annotation.DateTimeFormat;
import java.io.Serializable;


/**
 * 配件
 * 接收传参的实体类
 *（实际开发中配合移动端接口开发手动去掉些没用的字段， 后端一般用entity就够用了）
 * 取自ModelAndView 的model名称
 */
public class PeijianModel implements Serializable {
    private static final long serialVersionUID = 1L;




    /**
     * 主键
     */
    private Integer id;


    /**
     * 配件编号
     */
    private String peijianUuidNumber;


    /**
     * 配件名称
     */
    private String peijianName;


    /**
     * 配件类型
     */
    private Integer peijianTypes;


    /**
     * 配件库存
     */
    private Integer peijianKucunNumber;


    /**
     * 单价
     */
    private Double peijianNewMoney;


    /**
     * 配件介绍
     */
    private String peijianContent;


    /**
     * 逻辑删除
     */
    private Integer peijianDelete;


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
	 * 获取：配件编号
	 */
    public String getPeijianUuidNumber() {
        return peijianUuidNumber;
    }


    /**
	 * 设置：配件编号
	 */
    public void setPeijianUuidNumber(String peijianUuidNumber) {
        this.peijianUuidNumber = peijianUuidNumber;
    }
    /**
	 * 获取：配件名称
	 */
    public String getPeijianName() {
        return peijianName;
    }


    /**
	 * 设置：配件名称
	 */
    public void setPeijianName(String peijianName) {
        this.peijianName = peijianName;
    }
    /**
	 * 获取：配件类型
	 */
    public Integer getPeijianTypes() {
        return peijianTypes;
    }


    /**
	 * 设置：配件类型
	 */
    public void setPeijianTypes(Integer peijianTypes) {
        this.peijianTypes = peijianTypes;
    }
    /**
	 * 获取：配件库存
	 */
    public Integer getPeijianKucunNumber() {
        return peijianKucunNumber;
    }


    /**
	 * 设置：配件库存
	 */
    public void setPeijianKucunNumber(Integer peijianKucunNumber) {
        this.peijianKucunNumber = peijianKucunNumber;
    }
    /**
	 * 获取：单价
	 */
    public Double getPeijianNewMoney() {
        return peijianNewMoney;
    }


    /**
	 * 设置：单价
	 */
    public void setPeijianNewMoney(Double peijianNewMoney) {
        this.peijianNewMoney = peijianNewMoney;
    }
    /**
	 * 获取：配件介绍
	 */
    public String getPeijianContent() {
        return peijianContent;
    }


    /**
	 * 设置：配件介绍
	 */
    public void setPeijianContent(String peijianContent) {
        this.peijianContent = peijianContent;
    }
    /**
	 * 获取：逻辑删除
	 */
    public Integer getPeijianDelete() {
        return peijianDelete;
    }


    /**
	 * 设置：逻辑删除
	 */
    public void setPeijianDelete(Integer peijianDelete) {
        this.peijianDelete = peijianDelete;
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
