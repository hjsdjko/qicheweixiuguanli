package com.dao;

import com.entity.WeixiuxiangmuLiuyanEntity;
import com.baomidou.mybatisplus.mapper.BaseMapper;
import java.util.List;
import java.util.Map;
import com.baomidou.mybatisplus.plugins.pagination.Pagination;

import org.apache.ibatis.annotations.Param;
import com.entity.view.WeixiuxiangmuLiuyanView;

/**
 * 维修项目评论 Dao 接口
 *
 * @author 
 */
public interface WeixiuxiangmuLiuyanDao extends BaseMapper<WeixiuxiangmuLiuyanEntity> {

   List<WeixiuxiangmuLiuyanView> selectListView(Pagination page,@Param("params")Map<String,Object> params);

}
