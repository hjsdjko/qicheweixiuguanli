package com.entity.vo;

import com.entity.PeijianChuruInoutListEntity;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import java.util.Date;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;

/**
 * 出入库详情
 * 手机端接口返回实体辅助类
 * （主要作用去除一些不必要的字段）
 */
@TableName("peijian_churu_inout_list")
public class PeijianChuruInoutListVO implements Serializable {
    private static final long serialVersionUID = 1L;


    /**
     * 主键
     */

    @TableField(value = "id")
    private Integer id;


    /**
     * 出入库
     */

    @TableField(value = "peijian_churu_inout_id")
    private Integer peijianChuruInoutId;


    /**
     * 配件
     */

    @TableField(value = "peijian_id")
    private Integer peijianId;


    /**
     * 操作数量
     */

    @TableField(value = "peijian_churu_inout_list_number")
    private Integer peijianChuruInoutListNumber;


    /**
     * 操作时间
     */
    @JsonFormat(locale="zh", timezone="GMT+8", pattern="yyyy-MM-dd HH:mm:ss")
	@DateTimeFormat

    @TableField(value = "insert_time")
    private Date insertTime;


    /**
     * 创建时间
     */
    @JsonFormat(locale="zh", timezone="GMT+8", pattern="yyyy-MM-dd HH:mm:ss")
	@DateTimeFormat

    @TableField(value = "create_time")
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
	 * 设置：出入库
	 */
    public Integer getPeijianChuruInoutId() {
        return peijianChuruInoutId;
    }


    /**
	 * 获取：出入库
	 */

    public void setPeijianChuruInoutId(Integer peijianChuruInoutId) {
        this.peijianChuruInoutId = peijianChuruInoutId;
    }
    /**
	 * 设置：配件
	 */
    public Integer getPeijianId() {
        return peijianId;
    }


    /**
	 * 获取：配件
	 */

    public void setPeijianId(Integer peijianId) {
        this.peijianId = peijianId;
    }
    /**
	 * 设置：操作数量
	 */
    public Integer getPeijianChuruInoutListNumber() {
        return peijianChuruInoutListNumber;
    }


    /**
	 * 获取：操作数量
	 */

    public void setPeijianChuruInoutListNumber(Integer peijianChuruInoutListNumber) {
        this.peijianChuruInoutListNumber = peijianChuruInoutListNumber;
    }
    /**
	 * 设置：操作时间
	 */
    public Date getInsertTime() {
        return insertTime;
    }


    /**
	 * 获取：操作时间
	 */

    public void setInsertTime(Date insertTime) {
        this.insertTime = insertTime;
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

}
