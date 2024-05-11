
package com.controller;

import java.io.File;
import java.math.BigDecimal;
import java.net.URL;
import java.text.SimpleDateFormat;
import com.alibaba.fastjson.JSONObject;
import java.util.*;
import org.springframework.beans.BeanUtils;
import javax.servlet.http.HttpServletRequest;
import org.springframework.web.context.ContextLoader;
import javax.servlet.ServletContext;
import com.service.TokenService;
import com.utils.*;
import java.lang.reflect.InvocationTargetException;

import com.service.DictionaryService;
import org.apache.commons.lang3.StringUtils;
import com.annotation.IgnoreAuth;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.mapper.Wrapper;
import com.entity.*;
import com.entity.view.*;
import com.service.*;
import com.utils.PageUtils;
import com.utils.R;
import com.alibaba.fastjson.*;

/**
 * 维修项目
 * 后端接口
 * @author
 * @email
*/
@RestController
@Controller
@RequestMapping("/weixiuxiangmu")
public class WeixiuxiangmuController {
    private static final Logger logger = LoggerFactory.getLogger(WeixiuxiangmuController.class);

    @Autowired
    private WeixiuxiangmuService weixiuxiangmuService;


    @Autowired
    private TokenService tokenService;
    @Autowired
    private DictionaryService dictionaryService;

    //级联表service

    @Autowired
    private YonghuService yonghuService;
    @Autowired
    private YuangongService yuangongService;


    /**
    * 后端列表
    */
    @RequestMapping("/page")
    public R page(@RequestParam Map<String, Object> params, HttpServletRequest request){
        logger.debug("page方法:,,Controller:{},,params:{}",this.getClass().getName(),JSONObject.toJSONString(params));
        String role = String.valueOf(request.getSession().getAttribute("role"));
        if(false)
            return R.error(511,"永不会进入");
        else if("用户".equals(role))
            params.put("yonghuId",request.getSession().getAttribute("userId"));
        else if("员工".equals(role))
            params.put("yuangongId",request.getSession().getAttribute("userId"));
        params.put("weixiuxiangmuDeleteStart",1);params.put("weixiuxiangmuDeleteEnd",1);
        if(params.get("orderBy")==null || params.get("orderBy")==""){
            params.put("orderBy","id");
        }
        PageUtils page = weixiuxiangmuService.queryPage(params);

        //字典表数据转换
        List<WeixiuxiangmuView> list =(List<WeixiuxiangmuView>)page.getList();
        for(WeixiuxiangmuView c:list){
            //修改对应字典表字段
            dictionaryService.dictionaryConvert(c, request);
        }
        return R.ok().put("data", page);
    }

    /**
    * 后端详情
    */
    @RequestMapping("/info/{id}")
    public R info(@PathVariable("id") Long id, HttpServletRequest request){
        logger.debug("info方法:,,Controller:{},,id:{}",this.getClass().getName(),id);
        WeixiuxiangmuEntity weixiuxiangmu = weixiuxiangmuService.selectById(id);
        if(weixiuxiangmu !=null){
            //entity转view
            WeixiuxiangmuView view = new WeixiuxiangmuView();
            BeanUtils.copyProperties( weixiuxiangmu , view );//把实体数据重构到view中

            //修改对应字典表字段
            dictionaryService.dictionaryConvert(view, request);
            return R.ok().put("data", view);
        }else {
            return R.error(511,"查不到数据");
        }

    }

    /**
    * 后端保存
    */
    @RequestMapping("/save")
    public R save(@RequestBody WeixiuxiangmuEntity weixiuxiangmu, HttpServletRequest request){
        logger.debug("save方法:,,Controller:{},,weixiuxiangmu:{}",this.getClass().getName(),weixiuxiangmu.toString());

        String role = String.valueOf(request.getSession().getAttribute("role"));
        if(false)
            return R.error(511,"永远不会进入");

        Wrapper<WeixiuxiangmuEntity> queryWrapper = new EntityWrapper<WeixiuxiangmuEntity>()
            .eq("weixiuxiangmu_name", weixiuxiangmu.getWeixiuxiangmuName())
            .eq("weixiuxiangmu_types", weixiuxiangmu.getWeixiuxiangmuTypes())
            .eq("weixiuxiangmu_clicknum", weixiuxiangmu.getWeixiuxiangmuClicknum())
            .eq("shangxia_types", weixiuxiangmu.getShangxiaTypes())
            .eq("weixiuxiangmu_delete", weixiuxiangmu.getWeixiuxiangmuDelete())
            ;

        logger.info("sql语句:"+queryWrapper.getSqlSegment());
        WeixiuxiangmuEntity weixiuxiangmuEntity = weixiuxiangmuService.selectOne(queryWrapper);
        if(weixiuxiangmuEntity==null){
            weixiuxiangmu.setWeixiuxiangmuClicknum(1);
            weixiuxiangmu.setShangxiaTypes(1);
            weixiuxiangmu.setWeixiuxiangmuDelete(1);
            weixiuxiangmu.setCreateTime(new Date());
            weixiuxiangmuService.insert(weixiuxiangmu);
            return R.ok();
        }else {
            return R.error(511,"表中有相同数据");
        }
    }

    /**
    * 后端修改
    */
    @RequestMapping("/update")
    public R update(@RequestBody WeixiuxiangmuEntity weixiuxiangmu, HttpServletRequest request){
        logger.debug("update方法:,,Controller:{},,weixiuxiangmu:{}",this.getClass().getName(),weixiuxiangmu.toString());

        String role = String.valueOf(request.getSession().getAttribute("role"));
//        if(false)
//            return R.error(511,"永远不会进入");
        //根据字段查询是否有相同数据
        Wrapper<WeixiuxiangmuEntity> queryWrapper = new EntityWrapper<WeixiuxiangmuEntity>()
            .notIn("id",weixiuxiangmu.getId())
            .andNew()
            .eq("weixiuxiangmu_name", weixiuxiangmu.getWeixiuxiangmuName())
            .eq("weixiuxiangmu_types", weixiuxiangmu.getWeixiuxiangmuTypes())
            .eq("weixiuxiangmu_clicknum", weixiuxiangmu.getWeixiuxiangmuClicknum())
            .eq("shangxia_types", weixiuxiangmu.getShangxiaTypes())
            .eq("weixiuxiangmu_delete", weixiuxiangmu.getWeixiuxiangmuDelete())
            ;

        logger.info("sql语句:"+queryWrapper.getSqlSegment());
        WeixiuxiangmuEntity weixiuxiangmuEntity = weixiuxiangmuService.selectOne(queryWrapper);
        if("".equals(weixiuxiangmu.getWeixiuxiangmuPhoto()) || "null".equals(weixiuxiangmu.getWeixiuxiangmuPhoto())){
                weixiuxiangmu.setWeixiuxiangmuPhoto(null);
        }
        if(weixiuxiangmuEntity==null){
            weixiuxiangmuService.updateById(weixiuxiangmu);//根据id更新
            return R.ok();
        }else {
            return R.error(511,"表中有相同数据");
        }
    }



    /**
    * 删除
    */
    @RequestMapping("/delete")
    public R delete(@RequestBody Integer[] ids){
        logger.debug("delete:,,Controller:{},,ids:{}",this.getClass().getName(),ids.toString());
        ArrayList<WeixiuxiangmuEntity> list = new ArrayList<>();
        for(Integer id:ids){
            WeixiuxiangmuEntity weixiuxiangmuEntity = new WeixiuxiangmuEntity();
            weixiuxiangmuEntity.setId(id);
            weixiuxiangmuEntity.setWeixiuxiangmuDelete(2);
            list.add(weixiuxiangmuEntity);
        }
        if(list != null && list.size() >0){
            weixiuxiangmuService.updateBatchById(list);
        }
        return R.ok();
    }


    /**
     * 批量上传
     */
    @RequestMapping("/batchInsert")
    public R save( String fileName, HttpServletRequest request){
        logger.debug("batchInsert方法:,,Controller:{},,fileName:{}",this.getClass().getName(),fileName);
        Integer yonghuId = Integer.valueOf(String.valueOf(request.getSession().getAttribute("userId")));
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        try {
            List<WeixiuxiangmuEntity> weixiuxiangmuList = new ArrayList<>();//上传的东西
            Map<String, List<String>> seachFields= new HashMap<>();//要查询的字段
            Date date = new Date();
            int lastIndexOf = fileName.lastIndexOf(".");
            if(lastIndexOf == -1){
                return R.error(511,"该文件没有后缀");
            }else{
                String suffix = fileName.substring(lastIndexOf);
                if(!".xls".equals(suffix)){
                    return R.error(511,"只支持后缀为xls的excel文件");
                }else{
                    URL resource = this.getClass().getClassLoader().getResource("static/upload/" + fileName);//获取文件路径
                    File file = new File(resource.getFile());
                    if(!file.exists()){
                        return R.error(511,"找不到上传文件，请联系管理员");
                    }else{
                        List<List<String>> dataList = PoiUtil.poiImport(file.getPath());//读取xls文件
                        dataList.remove(0);//删除第一行，因为第一行是提示
                        for(List<String> data:dataList){
                            //循环
                            WeixiuxiangmuEntity weixiuxiangmuEntity = new WeixiuxiangmuEntity();
//                            weixiuxiangmuEntity.setWeixiuxiangmuName(data.get(0));                    //标题 要改的
//                            weixiuxiangmuEntity.setWeixiuxiangmuPhoto("");//详情和图片
//                            weixiuxiangmuEntity.setWeixiuxiangmuTypes(Integer.valueOf(data.get(0)));   //维修类型 要改的
//                            weixiuxiangmuEntity.setWeixiuxiangmuNewMoney(data.get(0));                    //维修价格 要改的
//                            weixiuxiangmuEntity.setWeixiuxiangmuClicknum(Integer.valueOf(data.get(0)));   //点击次数 要改的
//                            weixiuxiangmuEntity.setWeixiuxiangmuContent("");//详情和图片
//                            weixiuxiangmuEntity.setShangxiaTypes(Integer.valueOf(data.get(0)));   //是否上架 要改的
//                            weixiuxiangmuEntity.setWeixiuxiangmuDelete(1);//逻辑删除字段
//                            weixiuxiangmuEntity.setCreateTime(date);//时间
                            weixiuxiangmuList.add(weixiuxiangmuEntity);


                            //把要查询是否重复的字段放入map中
                        }

                        //查询是否重复
                        weixiuxiangmuService.insertBatch(weixiuxiangmuList);
                        return R.ok();
                    }
                }
            }
        }catch (Exception e){
            e.printStackTrace();
            return R.error(511,"批量插入数据异常，请联系管理员");
        }
    }





    /**
    * 前端列表
    */
    @IgnoreAuth
    @RequestMapping("/list")
    public R list(@RequestParam Map<String, Object> params, HttpServletRequest request){
        logger.debug("list方法:,,Controller:{},,params:{}",this.getClass().getName(),JSONObject.toJSONString(params));

        // 没有指定排序字段就默认id倒序
        if(StringUtil.isEmpty(String.valueOf(params.get("orderBy")))){
            params.put("orderBy","id");
        }
        PageUtils page = weixiuxiangmuService.queryPage(params);

        //字典表数据转换
        List<WeixiuxiangmuView> list =(List<WeixiuxiangmuView>)page.getList();
        for(WeixiuxiangmuView c:list)
            dictionaryService.dictionaryConvert(c, request); //修改对应字典表字段
        return R.ok().put("data", page);
    }

    /**
    * 前端详情
    */
    @RequestMapping("/detail/{id}")
    public R detail(@PathVariable("id") Long id, HttpServletRequest request){
        logger.debug("detail方法:,,Controller:{},,id:{}",this.getClass().getName(),id);
        WeixiuxiangmuEntity weixiuxiangmu = weixiuxiangmuService.selectById(id);
            if(weixiuxiangmu !=null){

                //点击数量加1
                weixiuxiangmu.setWeixiuxiangmuClicknum(weixiuxiangmu.getWeixiuxiangmuClicknum()+1);
                weixiuxiangmuService.updateById(weixiuxiangmu);

                //entity转view
                WeixiuxiangmuView view = new WeixiuxiangmuView();
                BeanUtils.copyProperties( weixiuxiangmu , view );//把实体数据重构到view中

                //修改对应字典表字段
                dictionaryService.dictionaryConvert(view, request);
                return R.ok().put("data", view);
            }else {
                return R.error(511,"查不到数据");
            }
    }


    /**
    * 前端保存
    */
    @RequestMapping("/add")
    public R add(@RequestBody WeixiuxiangmuEntity weixiuxiangmu, HttpServletRequest request){
        logger.debug("add方法:,,Controller:{},,weixiuxiangmu:{}",this.getClass().getName(),weixiuxiangmu.toString());
        Wrapper<WeixiuxiangmuEntity> queryWrapper = new EntityWrapper<WeixiuxiangmuEntity>()
            .eq("weixiuxiangmu_name", weixiuxiangmu.getWeixiuxiangmuName())
            .eq("weixiuxiangmu_types", weixiuxiangmu.getWeixiuxiangmuTypes())
            .eq("weixiuxiangmu_clicknum", weixiuxiangmu.getWeixiuxiangmuClicknum())
            .eq("shangxia_types", weixiuxiangmu.getShangxiaTypes())
            .eq("weixiuxiangmu_delete", weixiuxiangmu.getWeixiuxiangmuDelete())
            ;
        logger.info("sql语句:"+queryWrapper.getSqlSegment());
        WeixiuxiangmuEntity weixiuxiangmuEntity = weixiuxiangmuService.selectOne(queryWrapper);
        if(weixiuxiangmuEntity==null){
            weixiuxiangmu.setWeixiuxiangmuDelete(1);
            weixiuxiangmu.setCreateTime(new Date());
        weixiuxiangmuService.insert(weixiuxiangmu);
            return R.ok();
        }else {
            return R.error(511,"表中有相同数据");
        }
    }


}
