package com.entity.view;

import com.entity.PeijianChuruInoutListEntity;
import com.baomidou.mybatisplus.annotations.TableName;
import org.apache.commons.beanutils.BeanUtils;
import java.lang.reflect.InvocationTargetException;
import org.springframework.format.annotation.DateTimeFormat;
import com.fasterxml.jackson.annotation.JsonFormat;
import java.io.Serializable;
import java.util.Date;

/**
 * 出入库详情
 * 后端返回视图实体辅助类
 * （通常后端关联的表或者自定义的字段需要返回使用）
 */
@TableName("peijian_churu_inout_list")
public class PeijianChuruInoutListView extends PeijianChuruInoutListEntity implements Serializable {
    private static final long serialVersionUID = 1L;




		//级联表 peijian
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
				* 配件类型的值
				*/
				private String peijianValue;
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

		//级联表 peijian_churu_inout
			/**
			* 出入库流水号
			*/
			private String peijianChuruInoutUuidNumber;
			/**
			* 出入库名称
			*/
			private String peijianChuruInoutName;
			/**
			* 出入库类型
			*/
			private Integer peijianChuruInoutTypes;
				/**
				* 出入库类型的值
				*/
				private String peijianChuruInoutValue;
			/**
			* 备注
			*/
			private String peijianChuruInoutContent;

	public PeijianChuruInoutListView() {

	}

	public PeijianChuruInoutListView(PeijianChuruInoutListEntity peijianChuruInoutListEntity) {
		try {
			BeanUtils.copyProperties(this, peijianChuruInoutListEntity);
		} catch (IllegalAccessException | InvocationTargetException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}




















				//级联表的get和set peijian

					/**
					* 获取： 配件编号
					*/
					public String getPeijianUuidNumber() {
						return peijianUuidNumber;
					}
					/**
					* 设置： 配件编号
					*/
					public void setPeijianUuidNumber(String peijianUuidNumber) {
						this.peijianUuidNumber = peijianUuidNumber;
					}

					/**
					* 获取： 配件名称
					*/
					public String getPeijianName() {
						return peijianName;
					}
					/**
					* 设置： 配件名称
					*/
					public void setPeijianName(String peijianName) {
						this.peijianName = peijianName;
					}

					/**
					* 获取： 配件类型
					*/
					public Integer getPeijianTypes() {
						return peijianTypes;
					}
					/**
					* 设置： 配件类型
					*/
					public void setPeijianTypes(Integer peijianTypes) {
						this.peijianTypes = peijianTypes;
					}


						/**
						* 获取： 配件类型的值
						*/
						public String getPeijianValue() {
							return peijianValue;
						}
						/**
						* 设置： 配件类型的值
						*/
						public void setPeijianValue(String peijianValue) {
							this.peijianValue = peijianValue;
						}

					/**
					* 获取： 配件库存
					*/
					public Integer getPeijianKucunNumber() {
						return peijianKucunNumber;
					}
					/**
					* 设置： 配件库存
					*/
					public void setPeijianKucunNumber(Integer peijianKucunNumber) {
						this.peijianKucunNumber = peijianKucunNumber;
					}

					/**
					* 获取： 单价
					*/
					public Double getPeijianNewMoney() {
						return peijianNewMoney;
					}
					/**
					* 设置： 单价
					*/
					public void setPeijianNewMoney(Double peijianNewMoney) {
						this.peijianNewMoney = peijianNewMoney;
					}

					/**
					* 获取： 配件介绍
					*/
					public String getPeijianContent() {
						return peijianContent;
					}
					/**
					* 设置： 配件介绍
					*/
					public void setPeijianContent(String peijianContent) {
						this.peijianContent = peijianContent;
					}

					/**
					* 获取： 逻辑删除
					*/
					public Integer getPeijianDelete() {
						return peijianDelete;
					}
					/**
					* 设置： 逻辑删除
					*/
					public void setPeijianDelete(Integer peijianDelete) {
						this.peijianDelete = peijianDelete;
					}


				//级联表的get和set peijian_churu_inout

					/**
					* 获取： 出入库流水号
					*/
					public String getPeijianChuruInoutUuidNumber() {
						return peijianChuruInoutUuidNumber;
					}
					/**
					* 设置： 出入库流水号
					*/
					public void setPeijianChuruInoutUuidNumber(String peijianChuruInoutUuidNumber) {
						this.peijianChuruInoutUuidNumber = peijianChuruInoutUuidNumber;
					}

					/**
					* 获取： 出入库名称
					*/
					public String getPeijianChuruInoutName() {
						return peijianChuruInoutName;
					}
					/**
					* 设置： 出入库名称
					*/
					public void setPeijianChuruInoutName(String peijianChuruInoutName) {
						this.peijianChuruInoutName = peijianChuruInoutName;
					}

					/**
					* 获取： 出入库类型
					*/
					public Integer getPeijianChuruInoutTypes() {
						return peijianChuruInoutTypes;
					}
					/**
					* 设置： 出入库类型
					*/
					public void setPeijianChuruInoutTypes(Integer peijianChuruInoutTypes) {
						this.peijianChuruInoutTypes = peijianChuruInoutTypes;
					}


						/**
						* 获取： 出入库类型的值
						*/
						public String getPeijianChuruInoutValue() {
							return peijianChuruInoutValue;
						}
						/**
						* 设置： 出入库类型的值
						*/
						public void setPeijianChuruInoutValue(String peijianChuruInoutValue) {
							this.peijianChuruInoutValue = peijianChuruInoutValue;
						}

					/**
					* 获取： 备注
					*/
					public String getPeijianChuruInoutContent() {
						return peijianChuruInoutContent;
					}
					/**
					* 设置： 备注
					*/
					public void setPeijianChuruInoutContent(String peijianChuruInoutContent) {
						this.peijianChuruInoutContent = peijianChuruInoutContent;
					}






















}
