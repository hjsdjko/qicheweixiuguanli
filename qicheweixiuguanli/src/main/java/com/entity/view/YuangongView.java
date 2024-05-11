package com.entity.view;

import com.entity.YuangongEntity;
import com.baomidou.mybatisplus.annotations.TableName;
import org.apache.commons.beanutils.BeanUtils;
import java.lang.reflect.InvocationTargetException;
import org.springframework.format.annotation.DateTimeFormat;
import com.fasterxml.jackson.annotation.JsonFormat;
import java.io.Serializable;
import java.util.Date;

/**
 * 员工
 * 后端返回视图实体辅助类
 * （通常后端关联的表或者自定义的字段需要返回使用）
 */
@TableName("yuangong")
public class YuangongView extends YuangongEntity implements Serializable {
    private static final long serialVersionUID = 1L;

		/**
		* 性别的值
		*/
		private String sexValue;
		/**
		* 身份的值
		*/
		private String shenfenValue;



	public YuangongView() {

	}

	public YuangongView(YuangongEntity yuangongEntity) {
		try {
			BeanUtils.copyProperties(this, yuangongEntity);
		} catch (IllegalAccessException | InvocationTargetException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}



			/**
			* 获取： 性别的值
			*/
			public String getSexValue() {
				return sexValue;
			}
			/**
			* 设置： 性别的值
			*/
			public void setSexValue(String sexValue) {
				this.sexValue = sexValue;
			}
			/**
			* 获取： 身份的值
			*/
			public String getShenfenValue() {
				return shenfenValue;
			}
			/**
			* 设置： 身份的值
			*/
			public void setShenfenValue(String shenfenValue) {
				this.shenfenValue = shenfenValue;
			}















}
