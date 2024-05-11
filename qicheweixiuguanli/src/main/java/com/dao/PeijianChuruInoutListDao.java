package com.dao;

import com.entity.PeijianChuruInoutListEntity;
import com.baomidou.mybatisplus.mapper.BaseMapper;
import java.util.List;
import java.util.Map;
import com.baomidou.mybatisplus.plugins.pagination.Pagination;

import org.apache.ibatis.annotations.Param;
import com.entity.view.PeijianChuruInoutListView;

/**
 * 出入库详情 Dao 接口
 *
 * @author 
 */
public interface PeijianChuruInoutListDao extends BaseMapper<PeijianChuruInoutListEntity> {

   List<PeijianChuruInoutListView> selectListView(Pagination page,@Param("params")Map<String,Object> params);

}
