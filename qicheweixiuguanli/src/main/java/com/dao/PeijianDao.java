package com.dao;

import com.entity.PeijianEntity;
import com.baomidou.mybatisplus.mapper.BaseMapper;
import java.util.List;
import java.util.Map;
import com.baomidou.mybatisplus.plugins.pagination.Pagination;

import org.apache.ibatis.annotations.Param;
import com.entity.view.PeijianView;

/**
 * 配件 Dao 接口
 *
 * @author 
 */
public interface PeijianDao extends BaseMapper<PeijianEntity> {

   List<PeijianView> selectListView(Pagination page,@Param("params")Map<String,Object> params);

}
