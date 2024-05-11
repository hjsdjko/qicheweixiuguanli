package com.dao;

import com.entity.WeixiuxiangmuEntity;
import com.baomidou.mybatisplus.mapper.BaseMapper;
import java.util.List;
import java.util.Map;
import com.baomidou.mybatisplus.plugins.pagination.Pagination;

import org.apache.ibatis.annotations.Param;
import com.entity.view.WeixiuxiangmuView;

/**
 * 维修项目 Dao 接口
 *
 * @author 
 */
public interface WeixiuxiangmuDao extends BaseMapper<WeixiuxiangmuEntity> {

   List<WeixiuxiangmuView> selectListView(Pagination page,@Param("params")Map<String,Object> params);

}
