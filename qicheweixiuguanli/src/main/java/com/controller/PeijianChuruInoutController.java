
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
 * 出入库
 * 后端接口
 * @author
 * @email
*/
@RestController
@Controller
@RequestMapping("/peijianChuruInout")
public class PeijianChuruInoutController {
    private static final Logger logger = LoggerFactory.getLogger(PeijianChuruInoutController.class);

    @Autowired
    private PeijianChuruInoutService peijianChuruInoutService;


    @Autowired
    private TokenService tokenService;
    @Autowired
    private DictionaryService dictionaryService;

    //级联表service

    // 列表详情的表级联service
    @Autowired
    private PeijianChuruInoutListService peijianChuruInoutListService;
//    @Autowired
//    private YonghuService yonghuService;
    @Autowired
    private PeijianService peijianService;
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
        if(params.get("orderBy")==null || params.get("orderBy")==""){
            params.put("orderBy","id");
        }
        PageUtils page = peijianChuruInoutService.queryPage(params);

        //字典表数据转换
        List<PeijianChuruInoutView> list =(List<PeijianChuruInoutView>)page.getList();
        for(PeijianChuruInoutView c:list){
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
        PeijianChuruInoutEntity peijianChuruInout = peijianChuruInoutService.selectById(id);
        if(peijianChuruInout !=null){
            //entity转view
            PeijianChuruInoutView view = new PeijianChuruInoutView();
            BeanUtils.copyProperties( peijianChuruInout , view );//把实体数据重构到view中

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
    public R save(@RequestBody PeijianChuruInoutEntity peijianChuruInout, HttpServletRequest request){
        logger.debug("save方法:,,Controller:{},,peijianChuruInout:{}",this.getClass().getName(),peijianChuruInout.toString());

        String role = String.valueOf(request.getSession().getAttribute("role"));
        if(false)
            return R.error(511,"永远不会进入");

        Wrapper<PeijianChuruInoutEntity> queryWrapper = new EntityWrapper<PeijianChuruInoutEntity>()
            .eq("peijian_churu_inout_uuid_number", peijianChuruInout.getPeijianChuruInoutUuidNumber())
            .eq("peijian_churu_inout_name", peijianChuruInout.getPeijianChuruInoutName())
            .eq("peijian_churu_inout_types", peijianChuruInout.getPeijianChuruInoutTypes())
            ;

        logger.info("sql语句:"+queryWrapper.getSqlSegment());
        PeijianChuruInoutEntity peijianChuruInoutEntity = peijianChuruInoutService.selectOne(queryWrapper);
        if(peijianChuruInoutEntity==null){
            peijianChuruInout.setInsertTime(new Date());
            peijianChuruInout.setCreateTime(new Date());
            peijianChuruInoutService.insert(peijianChuruInout);
            return R.ok();
        }else {
            return R.error(511,"表中有相同数据");
        }
    }

    /**
    * 后端修改
    */
    @RequestMapping("/update")
    public R update(@RequestBody PeijianChuruInoutEntity peijianChuruInout, HttpServletRequest request){
        logger.debug("update方法:,,Controller:{},,peijianChuruInout:{}",this.getClass().getName(),peijianChuruInout.toString());

        String role = String.valueOf(request.getSession().getAttribute("role"));
//        if(false)
//            return R.error(511,"永远不会进入");
        //根据字段查询是否有相同数据
        Wrapper<PeijianChuruInoutEntity> queryWrapper = new EntityWrapper<PeijianChuruInoutEntity>()
            .notIn("id",peijianChuruInout.getId())
            .andNew()
            .eq("peijian_churu_inout_uuid_number", peijianChuruInout.getPeijianChuruInoutUuidNumber())
            .eq("peijian_churu_inout_name", peijianChuruInout.getPeijianChuruInoutName())
            .eq("peijian_churu_inout_types", peijianChuruInout.getPeijianChuruInoutTypes())
            ;

        logger.info("sql语句:"+queryWrapper.getSqlSegment());
        PeijianChuruInoutEntity peijianChuruInoutEntity = peijianChuruInoutService.selectOne(queryWrapper);
        if(peijianChuruInoutEntity==null){
            peijianChuruInoutService.updateById(peijianChuruInout);//根据id更新
            return R.ok();
        }else {
            return R.error(511,"表中有相同数据");
        }
    }


    /**
    * 出库
    */
    @RequestMapping("/outPeijianChuruInoutList")
    public R outPeijianChuruInoutList(@RequestBody  Map<String, Object> params,HttpServletRequest request){
        logger.debug("outPeijianChuruInoutList方法:,,Controller:{},,params:{}",this.getClass().getName(),JSONObject.toJSONString(params));

        String role = String.valueOf(request.getSession().getAttribute("role"));

        //取出入库名称并判断是否存在
        String peijianChuruInoutName = String.valueOf(params.get("peijianChuruInoutName"));
        Wrapper<PeijianChuruInoutEntity> queryWrapper = new EntityWrapper<PeijianChuruInoutEntity>()
            .eq("peijian_churu_inout_name", peijianChuruInoutName)
            ;
        PeijianChuruInoutEntity peijianChuruInoutSelectOne = peijianChuruInoutService.selectOne(queryWrapper);
        if(peijianChuruInoutSelectOne != null)
            return R.error(511,"出入库名称已被使用");


    //取当前表的级联表并判断是否前台传入

        Map<String, Integer> map = (Map<String, Integer>) params.get("map");
        if(map == null || map.size() == 0)
            return R.error(511,"列表内容不能为空");


        Set<String> ids = map.keySet();

        List<PeijianEntity> peijianList = peijianService.selectBatchIds(ids);
        if(peijianList == null || peijianList.size() == 0){
            return R.error(511,"查数据库查不到数据");
        }else{
            for(PeijianEntity w:peijianList){
                Integer value = w.getPeijianKucunNumber()-map.get(String.valueOf(w.getId()));
                if(value <0){
                    return R.error(511,"出库数量大于库存数量");
                }
                w.setPeijianKucunNumber(value);
            }
        }

        //当前表
        PeijianChuruInoutEntity peijianChuruInoutEntity = new PeijianChuruInoutEntity<>();
            peijianChuruInoutEntity.setPeijianChuruInoutUuidNumber(String.valueOf(new Date().getTime()));
            peijianChuruInoutEntity.setPeijianChuruInoutName(peijianChuruInoutName);
            peijianChuruInoutEntity.setPeijianChuruInoutTypes(1);
            peijianChuruInoutEntity.setPeijianChuruInoutContent("");
            peijianChuruInoutEntity.setInsertTime(new Date());
            peijianChuruInoutEntity.setCreateTime(new Date());

        boolean insertPeijianChuruInout = peijianChuruInoutService.insert(peijianChuruInoutEntity);
        if(insertPeijianChuruInout){
            //级联表
            ArrayList<PeijianChuruInoutListEntity> peijianChuruInoutLists = new ArrayList<>();
            for(String id:ids){
                PeijianChuruInoutListEntity peijianChuruInoutListEntity = new PeijianChuruInoutListEntity();
                    peijianChuruInoutListEntity.setPeijianChuruInoutId(peijianChuruInoutEntity.getId());
                    peijianChuruInoutListEntity.setPeijianId(Integer.valueOf(id));
                    peijianChuruInoutListEntity.setPeijianChuruInoutListNumber(map.get(id));
                    peijianChuruInoutListEntity.setInsertTime(new Date());
                    peijianChuruInoutListEntity.setCreateTime(new Date());
                peijianChuruInoutLists.add(peijianChuruInoutListEntity);
                peijianService.updateBatchById(peijianList);
            }
            peijianChuruInoutListService.insertBatch(peijianChuruInoutLists);
        }
        return R.ok();
    }

    /**
    *入库
    */
    @RequestMapping("/inPeijianChuruInoutList")
    public R inPeijianChuruInoutList(@RequestBody  Map<String, Object> params,HttpServletRequest request){
        logger.debug("inPeijianChuruInoutList方法:,,Controller:{},,params:{}",this.getClass().getName(),JSONObject.toJSONString(params));
        //params:{"map":{"1":2,"2":3},"wuziOutinName":"订单1"}

        String role = String.valueOf(request.getSession().getAttribute("role"));

        //取当前表名称并判断
        String peijianChuruInoutName = String.valueOf(params.get("peijianChuruInoutName"));
        Wrapper<PeijianChuruInoutEntity> queryWrapper = new EntityWrapper<PeijianChuruInoutEntity>()
            .eq("peijian_churu_inout_name", peijianChuruInoutName)
            ;
        PeijianChuruInoutEntity peijianChuruInoutSelectOne = peijianChuruInoutService.selectOne(queryWrapper);
        if(peijianChuruInoutSelectOne != null)
            return R.error(511,"出入库名称已被使用");


        //取当前表的级联表并判断是否前台传入

        Map<String, Integer> map = (Map<String, Integer>) params.get("map");
        if(map == null || map.size() == 0)
            return R.error(511,"列表内容不能为空");

        Set<String> ids = map.keySet();

        List<PeijianEntity> peijianList = peijianService.selectBatchIds(ids);
        if(peijianList == null || peijianList.size() == 0){
            return R.error(511,"查数据库查不到数据");
        }else{
            for(PeijianEntity w:peijianList){
                w.setPeijianKucunNumber(w.getPeijianKucunNumber()+map.get(String.valueOf(w.getId())));
            }
        }

        //当前表
        PeijianChuruInoutEntity peijianChuruInoutEntity = new PeijianChuruInoutEntity<>();
            peijianChuruInoutEntity.setPeijianChuruInoutUuidNumber(String.valueOf(new Date().getTime()));
            peijianChuruInoutEntity.setPeijianChuruInoutName(peijianChuruInoutName);
            peijianChuruInoutEntity.setPeijianChuruInoutTypes(2);
            peijianChuruInoutEntity.setPeijianChuruInoutContent("");
            peijianChuruInoutEntity.setInsertTime(new Date());
            peijianChuruInoutEntity.setCreateTime(new Date());


        boolean insertPeijianChuruInout = peijianChuruInoutService.insert(peijianChuruInoutEntity);
        if(insertPeijianChuruInout){
            //级联表
            ArrayList<PeijianChuruInoutListEntity> peijianChuruInoutLists = new ArrayList<>();
            for(String id:ids){
                PeijianChuruInoutListEntity peijianChuruInoutListEntity = new PeijianChuruInoutListEntity();
                peijianChuruInoutListEntity.setPeijianChuruInoutId(peijianChuruInoutEntity.getId());
                peijianChuruInoutListEntity.setPeijianId(Integer.valueOf(id));
                peijianChuruInoutListEntity.setPeijianChuruInoutListNumber(map.get(id));
                peijianChuruInoutListEntity.setInsertTime(new Date());
                peijianChuruInoutListEntity.setCreateTime(new Date());
                peijianChuruInoutLists.add(peijianChuruInoutListEntity);
                peijianService.updateBatchById(peijianList);
            }
            peijianChuruInoutListService.insertBatch(peijianChuruInoutLists);
        }

        return R.ok();
    }
    /**
    * 删除
    */
    @RequestMapping("/delete")
    public R delete(@RequestBody Integer[] ids){
        logger.debug("delete:,,Controller:{},,ids:{}",this.getClass().getName(),ids.toString());
        peijianChuruInoutService.deleteBatchIds(Arrays.asList(ids));
        peijianChuruInoutListService.delete(new EntityWrapper<PeijianChuruInoutListEntity>().in("peijian_churu_inout_id",ids));
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
            List<PeijianChuruInoutEntity> peijianChuruInoutList = new ArrayList<>();//上传的东西
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
                            PeijianChuruInoutEntity peijianChuruInoutEntity = new PeijianChuruInoutEntity();
//                            peijianChuruInoutEntity.setPeijianChuruInoutUuidNumber(data.get(0));                    //出入库流水号 要改的
//                            peijianChuruInoutEntity.setPeijianChuruInoutName(data.get(0));                    //出入库名称 要改的
//                            peijianChuruInoutEntity.setPeijianChuruInoutTypes(Integer.valueOf(data.get(0)));   //出入库类型 要改的
//                            peijianChuruInoutEntity.setPeijianChuruInoutContent("");//详情和图片
//                            peijianChuruInoutEntity.setInsertTime(date);//时间
//                            peijianChuruInoutEntity.setCreateTime(date);//时间
                            peijianChuruInoutList.add(peijianChuruInoutEntity);


                            //把要查询是否重复的字段放入map中
                                //出入库流水号
                                if(seachFields.containsKey("peijianChuruInoutUuidNumber")){
                                    List<String> peijianChuruInoutUuidNumber = seachFields.get("peijianChuruInoutUuidNumber");
                                    peijianChuruInoutUuidNumber.add(data.get(0));//要改的
                                }else{
                                    List<String> peijianChuruInoutUuidNumber = new ArrayList<>();
                                    peijianChuruInoutUuidNumber.add(data.get(0));//要改的
                                    seachFields.put("peijianChuruInoutUuidNumber",peijianChuruInoutUuidNumber);
                                }
                        }

                        //查询是否重复
                         //出入库流水号
                        List<PeijianChuruInoutEntity> peijianChuruInoutEntities_peijianChuruInoutUuidNumber = peijianChuruInoutService.selectList(new EntityWrapper<PeijianChuruInoutEntity>().in("peijian_churu_inout_uuid_number", seachFields.get("peijianChuruInoutUuidNumber")));
                        if(peijianChuruInoutEntities_peijianChuruInoutUuidNumber.size() >0 ){
                            ArrayList<String> repeatFields = new ArrayList<>();
                            for(PeijianChuruInoutEntity s:peijianChuruInoutEntities_peijianChuruInoutUuidNumber){
                                repeatFields.add(s.getPeijianChuruInoutUuidNumber());
                            }
                            return R.error(511,"数据库的该表中的 [出入库流水号] 字段已经存在 存在数据为:"+repeatFields.toString());
                        }
                        peijianChuruInoutService.insertBatch(peijianChuruInoutList);
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
        PageUtils page = peijianChuruInoutService.queryPage(params);

        //字典表数据转换
        List<PeijianChuruInoutView> list =(List<PeijianChuruInoutView>)page.getList();
        for(PeijianChuruInoutView c:list)
            dictionaryService.dictionaryConvert(c, request); //修改对应字典表字段
        return R.ok().put("data", page);
    }

    /**
    * 前端详情
    */
    @RequestMapping("/detail/{id}")
    public R detail(@PathVariable("id") Long id, HttpServletRequest request){
        logger.debug("detail方法:,,Controller:{},,id:{}",this.getClass().getName(),id);
        PeijianChuruInoutEntity peijianChuruInout = peijianChuruInoutService.selectById(id);
            if(peijianChuruInout !=null){


                //entity转view
                PeijianChuruInoutView view = new PeijianChuruInoutView();
                BeanUtils.copyProperties( peijianChuruInout , view );//把实体数据重构到view中

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
    public R add(@RequestBody PeijianChuruInoutEntity peijianChuruInout, HttpServletRequest request){
        logger.debug("add方法:,,Controller:{},,peijianChuruInout:{}",this.getClass().getName(),peijianChuruInout.toString());
        Wrapper<PeijianChuruInoutEntity> queryWrapper = new EntityWrapper<PeijianChuruInoutEntity>()
            .eq("peijian_churu_inout_uuid_number", peijianChuruInout.getPeijianChuruInoutUuidNumber())
            .eq("peijian_churu_inout_name", peijianChuruInout.getPeijianChuruInoutName())
            .eq("peijian_churu_inout_types", peijianChuruInout.getPeijianChuruInoutTypes())
            ;
        logger.info("sql语句:"+queryWrapper.getSqlSegment());
        PeijianChuruInoutEntity peijianChuruInoutEntity = peijianChuruInoutService.selectOne(queryWrapper);
        if(peijianChuruInoutEntity==null){
            peijianChuruInout.setInsertTime(new Date());
            peijianChuruInout.setCreateTime(new Date());
        peijianChuruInoutService.insert(peijianChuruInout);
            return R.ok();
        }else {
            return R.error(511,"表中有相同数据");
        }
    }


}
