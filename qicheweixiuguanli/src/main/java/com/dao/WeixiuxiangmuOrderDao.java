package com.dao;

import com.entity.WeixiuxiangmuOrderEntity;
import com.baomidou.mybatisplus.mapper.BaseMapper;
import java.util.List;
import java.util.Map;
import com.baomidou.mybatisplus.plugins.pagination.Pagination;

import org.apache.ibatis.annotations.Param;
import com.entity.view.WeixiuxiangmuOrderView;

/**
 * 维修订单 Dao 接口
 *
 * @author 
 */
public interface WeixiuxiangmuOrderDao extends BaseMapper<WeixiuxiangmuOrderEntity> {

   List<WeixiuxiangmuOrderView> selectListView(Pagination page,@Param("params")Map<String,Object> params);

}
