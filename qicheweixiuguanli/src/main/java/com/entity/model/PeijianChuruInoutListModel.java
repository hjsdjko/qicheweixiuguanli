package com.entity.model;

import com.entity.PeijianChuruInoutListEntity;

import com.baomidou.mybatisplus.annotations.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import java.util.Date;
import org.springframework.format.annotation.DateTimeFormat;
import java.io.Serializable;


/**
 * 出入库详情
 * 接收传参的实体类
 *（实际开发中配合移动端接口开发手动去掉些没用的字段， 后端一般用entity就够用了）
 * 取自ModelAndView 的model名称
 */
public class PeijianChuruInoutListModel implements Serializable {
    private static final long serialVersionUID = 1L;




    /**
     * 主键
     */
    private Integer id;


    /**
     * 出入库
     */
    private Integer peijianChuruInoutId;


    /**
     * 配件
     */
    private Integer peijianId;


    /**
     * 操作数量
     */
    private Integer peijianChuruInoutListNumber;


    /**
     * 操作时间
     */
    @JsonFormat(locale="zh", timezone="GMT+8", pattern="yyyy-MM-dd HH:mm:ss")
	@DateTimeFormat
    private Date insertTime;


    /**
     * 创建时间
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
	 * 获取：出入库
	 */
    public Integer getPeijianChuruInoutId() {
        return peijianChuruInoutId;
    }


    /**
	 * 设置：出入库
	 */
    public void setPeijianChuruInoutId(Integer peijianChuruInoutId) {
        this.peijianChuruInoutId = peijianChuruInoutId;
    }
    /**
	 * 获取：配件
	 */
    public Integer getPeijianId() {
        return peijianId;
    }


    /**
	 * 设置：配件
	 */
    public void setPeijianId(Integer peijianId) {
        this.peijianId = peijianId;
    }
    /**
	 * 获取：操作数量
	 */
    public Integer getPeijianChuruInoutListNumber() {
        return peijianChuruInoutListNumber;
    }


    /**
	 * 设置：操作数量
	 */
    public void setPeijianChuruInoutListNumber(Integer peijianChuruInoutListNumber) {
        this.peijianChuruInoutListNumber = peijianChuruInoutListNumber;
    }
    /**
	 * 获取：操作时间
	 */
    public Date getInsertTime() {
        return insertTime;
    }


    /**
	 * 设置：操作时间
	 */
    public void setInsertTime(Date insertTime) {
        this.insertTime = insertTime;
    }
    /**
	 * 获取：创建时间
	 */
    public Date getCreateTime() {
        return createTime;
    }


    /**
	 * 设置：创建时间
	 */
    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    }
