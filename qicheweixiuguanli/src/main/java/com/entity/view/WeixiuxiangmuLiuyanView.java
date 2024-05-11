package com.entity.view;

import com.entity.WeixiuxiangmuLiuyanEntity;
import com.baomidou.mybatisplus.annotations.TableName;
import org.apache.commons.beanutils.BeanUtils;
import java.lang.reflect.InvocationTargetException;
import org.springframework.format.annotation.DateTimeFormat;
import com.fasterxml.jackson.annotation.JsonFormat;
import java.io.Serializable;
import java.util.Date;

/**
 * 维修项目评论
 * 后端返回视图实体辅助类
 * （通常后端关联的表或者自定义的字段需要返回使用）
 */
@TableName("weixiuxiangmu_liuyan")
public class WeixiuxiangmuLiuyanView extends WeixiuxiangmuLiuyanEntity implements Serializable {
    private static final long serialVersionUID = 1L;




		//级联表 weixiuxiangmu
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
				* 维修类型的值
				*/
				private String weixiuxiangmuValue;
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
				* 是否上架的值
				*/
				private String shangxiaValue;
			/**
			* 逻辑删除
			*/
			private Integer weixiuxiangmuDelete;

		//级联表 yonghu
			/**
			* 用户姓名
			*/
			private String yonghuName;
			/**
			* 头像
			*/
			private String yonghuPhoto;
			/**
			* 联系方式
			*/
			private String yonghuPhone;
			/**
			* 邮箱
			*/
			private String yonghuEmail;
			/**
			* 余额
			*/
			private Double newMoney;
			/**
			* 假删
			*/
			private Integer yonghuDelete;

	public WeixiuxiangmuLiuyanView() {

	}

	public WeixiuxiangmuLiuyanView(WeixiuxiangmuLiuyanEntity weixiuxiangmuLiuyanEntity) {
		try {
			BeanUtils.copyProperties(this, weixiuxiangmuLiuyanEntity);
		} catch (IllegalAccessException | InvocationTargetException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}




























				//级联表的get和set weixiuxiangmu

					/**
					* 获取： 标题
					*/
					public String getWeixiuxiangmuName() {
						return weixiuxiangmuName;
					}
					/**
					* 设置： 标题
					*/
					public void setWeixiuxiangmuName(String weixiuxiangmuName) {
						this.weixiuxiangmuName = weixiuxiangmuName;
					}

					/**
					* 获取： 封面
					*/
					public String getWeixiuxiangmuPhoto() {
						return weixiuxiangmuPhoto;
					}
					/**
					* 设置： 封面
					*/
					public void setWeixiuxiangmuPhoto(String weixiuxiangmuPhoto) {
						this.weixiuxiangmuPhoto = weixiuxiangmuPhoto;
					}

					/**
					* 获取： 维修类型
					*/
					public Integer getWeixiuxiangmuTypes() {
						return weixiuxiangmuTypes;
					}
					/**
					* 设置： 维修类型
					*/
					public void setWeixiuxiangmuTypes(Integer weixiuxiangmuTypes) {
						this.weixiuxiangmuTypes = weixiuxiangmuTypes;
					}


						/**
						* 获取： 维修类型的值
						*/
						public String getWeixiuxiangmuValue() {
							return weixiuxiangmuValue;
						}
						/**
						* 设置： 维修类型的值
						*/
						public void setWeixiuxiangmuValue(String weixiuxiangmuValue) {
							this.weixiuxiangmuValue = weixiuxiangmuValue;
						}

					/**
					* 获取： 维修价格
					*/
					public Double getWeixiuxiangmuNewMoney() {
						return weixiuxiangmuNewMoney;
					}
					/**
					* 设置： 维修价格
					*/
					public void setWeixiuxiangmuNewMoney(Double weixiuxiangmuNewMoney) {
						this.weixiuxiangmuNewMoney = weixiuxiangmuNewMoney;
					}

					/**
					* 获取： 点击次数
					*/
					public Integer getWeixiuxiangmuClicknum() {
						return weixiuxiangmuClicknum;
					}
					/**
					* 设置： 点击次数
					*/
					public void setWeixiuxiangmuClicknum(Integer weixiuxiangmuClicknum) {
						this.weixiuxiangmuClicknum = weixiuxiangmuClicknum;
					}

					/**
					* 获取： 维修介绍
					*/
					public String getWeixiuxiangmuContent() {
						return weixiuxiangmuContent;
					}
					/**
					* 设置： 维修介绍
					*/
					public void setWeixiuxiangmuContent(String weixiuxiangmuContent) {
						this.weixiuxiangmuContent = weixiuxiangmuContent;
					}

					/**
					* 获取： 是否上架
					*/
					public Integer getShangxiaTypes() {
						return shangxiaTypes;
					}
					/**
					* 设置： 是否上架
					*/
					public void setShangxiaTypes(Integer shangxiaTypes) {
						this.shangxiaTypes = shangxiaTypes;
					}


						/**
						* 获取： 是否上架的值
						*/
						public String getShangxiaValue() {
							return shangxiaValue;
						}
						/**
						* 设置： 是否上架的值
						*/
						public void setShangxiaValue(String shangxiaValue) {
							this.shangxiaValue = shangxiaValue;
						}

					/**
					* 获取： 逻辑删除
					*/
					public Integer getWeixiuxiangmuDelete() {
						return weixiuxiangmuDelete;
					}
					/**
					* 设置： 逻辑删除
					*/
					public void setWeixiuxiangmuDelete(Integer weixiuxiangmuDelete) {
						this.weixiuxiangmuDelete = weixiuxiangmuDelete;
					}










				//级联表的get和set yonghu

					/**
					* 获取： 用户姓名
					*/
					public String getYonghuName() {
						return yonghuName;
					}
					/**
					* 设置： 用户姓名
					*/
					public void setYonghuName(String yonghuName) {
						this.yonghuName = yonghuName;
					}

					/**
					* 获取： 头像
					*/
					public String getYonghuPhoto() {
						return yonghuPhoto;
					}
					/**
					* 设置： 头像
					*/
					public void setYonghuPhoto(String yonghuPhoto) {
						this.yonghuPhoto = yonghuPhoto;
					}

					/**
					* 获取： 联系方式
					*/
					public String getYonghuPhone() {
						return yonghuPhone;
					}
					/**
					* 设置： 联系方式
					*/
					public void setYonghuPhone(String yonghuPhone) {
						this.yonghuPhone = yonghuPhone;
					}

					/**
					* 获取： 邮箱
					*/
					public String getYonghuEmail() {
						return yonghuEmail;
					}
					/**
					* 设置： 邮箱
					*/
					public void setYonghuEmail(String yonghuEmail) {
						this.yonghuEmail = yonghuEmail;
					}

					/**
					* 获取： 余额
					*/
					public Double getNewMoney() {
						return newMoney;
					}
					/**
					* 设置： 余额
					*/
					public void setNewMoney(Double newMoney) {
						this.newMoney = newMoney;
					}

					/**
					* 获取： 假删
					*/
					public Integer getYonghuDelete() {
						return yonghuDelete;
					}
					/**
					* 设置： 假删
					*/
					public void setYonghuDelete(Integer yonghuDelete) {
						this.yonghuDelete = yonghuDelete;
					}






}
