package com.entity.vo;

import com.entity.WeixiuxiangmuEntity;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import java.util.Date;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;

/**
 * 维修项目
 * 手机端接口返回实体辅助类
 * （主要作用去除一些不必要的字段）
 */
@TableName("weixiuxiangmu")
public class WeixiuxiangmuVO implements Serializable {
    private static final long serialVersionUID = 1L;


    /**
     * 主键
     */

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
     * 创建时间  show1 show2 photoShow
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
	 * 设置：创建时间  show1 show2 photoShow
	 */
    public Date getCreateTime() {
        return createTime;
    }


    /**
	 * 获取：创建时间  show1 show2 photoShow
	 */

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

}
