package com.entity.view;

import com.entity.PeijianEntity;
import com.baomidou.mybatisplus.annotations.TableName;
import org.apache.commons.beanutils.BeanUtils;
import java.lang.reflect.InvocationTargetException;
import org.springframework.format.annotation.DateTimeFormat;
import com.fasterxml.jackson.annotation.JsonFormat;
import java.io.Serializable;
import java.util.Date;

/**
 * 配件
 * 后端返回视图实体辅助类
 * （通常后端关联的表或者自定义的字段需要返回使用）
 */
@TableName("peijian")
public class PeijianView extends PeijianEntity implements Serializable {
    private static final long serialVersionUID = 1L;

		/**
		* 配件类型的值
		*/
		private String peijianValue;



	public PeijianView() {

	}

	public PeijianView(PeijianEntity peijianEntity) {
		try {
			BeanUtils.copyProperties(this, peijianEntity);
		} catch (IllegalAccessException | InvocationTargetException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
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















}
