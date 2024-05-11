package com.entity.view;

import com.entity.WeixiuxiangmuEntity;
import com.baomidou.mybatisplus.annotations.TableName;
import org.apache.commons.beanutils.BeanUtils;
import java.lang.reflect.InvocationTargetException;
import org.springframework.format.annotation.DateTimeFormat;
import com.fasterxml.jackson.annotation.JsonFormat;
import java.io.Serializable;
import java.util.Date;

/**
 * 维修项目
 * 后端返回视图实体辅助类
 * （通常后端关联的表或者自定义的字段需要返回使用）
 */
@TableName("weixiuxiangmu")
public class WeixiuxiangmuView extends WeixiuxiangmuEntity implements Serializable {
    private static final long serialVersionUID = 1L;

		/**
		* 维修类型的值
		*/
		private String weixiuxiangmuValue;
		/**
		* 是否上架的值
		*/
		private String shangxiaValue;



	public WeixiuxiangmuView() {

	}

	public WeixiuxiangmuView(WeixiuxiangmuEntity weixiuxiangmuEntity) {
		try {
			BeanUtils.copyProperties(this, weixiuxiangmuEntity);
		} catch (IllegalAccessException | InvocationTargetException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
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















}
