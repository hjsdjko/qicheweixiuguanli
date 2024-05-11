
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
 * 配件
 * 后端接口
 * @author
 * @email
*/
@RestController
@Controller
@RequestMapping("/peijian")
public class PeijianController {
    private static final Logger logger = LoggerFactory.getLogger(PeijianController.class);

    @Autowired
    private PeijianService peijianService;


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
        params.put("peijianDeleteStart",1);params.put("peijianDeleteEnd",1);
        if(params.get("orderBy")==null || params.get("orderBy")==""){
            params.put("orderBy","id");
        }
        PageUtils page = peijianService.queryPage(params);

        //字典表数据转换
        List<PeijianView> list =(List<PeijianView>)page.getList();
        for(PeijianView c:list){
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
        PeijianEntity peijian = peijianService.selectById(id);
        if(peijian !=null){
            //entity转view
            PeijianView view = new PeijianView();
            BeanUtils.copyProperties( peijian , view );//把实体数据重构到view中

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
    public R save(@RequestBody PeijianEntity peijian, HttpServletRequest request){
        logger.debug("save方法:,,Controller:{},,peijian:{}",this.getClass().getName(),peijian.toString());

        String role = String.valueOf(request.getSession().getAttribute("role"));
        if(false)
            return R.error(511,"永远不会进入");

        Wrapper<PeijianEntity> queryWrapper = new EntityWrapper<PeijianEntity>()
            .eq("peijian_uuid_number", peijian.getPeijianUuidNumber())
            .eq("peijian_name", peijian.getPeijianName())
            .eq("peijian_types", peijian.getPeijianTypes())
            .eq("peijian_kucun_number", peijian.getPeijianKucunNumber())
            .eq("peijian_delete", peijian.getPeijianDelete())
            ;

        logger.info("sql语句:"+queryWrapper.getSqlSegment());
        PeijianEntity peijianEntity = peijianService.selectOne(queryWrapper);
        if(peijianEntity==null){
            peijian.setPeijianDelete(1);
            peijian.setCreateTime(new Date());
            peijianService.insert(peijian);
            return R.ok();
        }else {
            return R.error(511,"表中有相同数据");
        }
    }

    /**
    * 后端修改
    */
    @RequestMapping("/update")
    public R update(@RequestBody PeijianEntity peijian, HttpServletRequest request){
        logger.debug("update方法:,,Controller:{},,peijian:{}",this.getClass().getName(),peijian.toString());

        String role = String.valueOf(request.getSession().getAttribute("role"));
//        if(false)
//            return R.error(511,"永远不会进入");
        //根据字段查询是否有相同数据
        Wrapper<PeijianEntity> queryWrapper = new EntityWrapper<PeijianEntity>()
            .notIn("id",peijian.getId())
            .andNew()
            .eq("peijian_uuid_number", peijian.getPeijianUuidNumber())
            .eq("peijian_name", peijian.getPeijianName())
            .eq("peijian_types", peijian.getPeijianTypes())
            .eq("peijian_kucun_number", peijian.getPeijianKucunNumber())
            .eq("peijian_delete", peijian.getPeijianDelete())
            ;

        logger.info("sql语句:"+queryWrapper.getSqlSegment());
        PeijianEntity peijianEntity = peijianService.selectOne(queryWrapper);
        if(peijianEntity==null){
            peijianService.updateById(peijian);//根据id更新
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
        ArrayList<PeijianEntity> list = new ArrayList<>();
        for(Integer id:ids){
            PeijianEntity peijianEntity = new PeijianEntity();
            peijianEntity.setId(id);
            peijianEntity.setPeijianDelete(2);
            list.add(peijianEntity);
        }
        if(list != null && list.size() >0){
            peijianService.updateBatchById(list);
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
            List<PeijianEntity> peijianList = new ArrayList<>();//上传的东西
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
                            PeijianEntity peijianEntity = new PeijianEntity();
//                            peijianEntity.setPeijianUuidNumber(data.get(0));                    //配件编号 要改的
//                            peijianEntity.setPeijianName(data.get(0));                    //配件名称 要改的
//                            peijianEntity.setPeijianTypes(Integer.valueOf(data.get(0)));   //配件类型 要改的
//                            peijianEntity.setPeijianKucunNumber(Integer.valueOf(data.get(0)));   //配件库存 要改的
//                            peijianEntity.setPeijianNewMoney(data.get(0));                    //单价 要改的
//                            peijianEntity.setPeijianContent("");//详情和图片
//                            peijianEntity.setPeijianDelete(1);//逻辑删除字段
//                            peijianEntity.setCreateTime(date);//时间
                            peijianList.add(peijianEntity);


                            //把要查询是否重复的字段放入map中
                                //配件编号
                                if(seachFields.containsKey("peijianUuidNumber")){
                                    List<String> peijianUuidNumber = seachFields.get("peijianUuidNumber");
                                    peijianUuidNumber.add(data.get(0));//要改的
                                }else{
                                    List<String> peijianUuidNumber = new ArrayList<>();
                                    peijianUuidNumber.add(data.get(0));//要改的
                                    seachFields.put("peijianUuidNumber",peijianUuidNumber);
                                }
                        }

                        //查询是否重复
                         //配件编号
                        List<PeijianEntity> peijianEntities_peijianUuidNumber = peijianService.selectList(new EntityWrapper<PeijianEntity>().in("peijian_uuid_number", seachFields.get("peijianUuidNumber")).eq("peijian_delete", 1));
                        if(peijianEntities_peijianUuidNumber.size() >0 ){
                            ArrayList<String> repeatFields = new ArrayList<>();
                            for(PeijianEntity s:peijianEntities_peijianUuidNumber){
                                repeatFields.add(s.getPeijianUuidNumber());
                            }
                            return R.error(511,"数据库的该表中的 [配件编号] 字段已经存在 存在数据为:"+repeatFields.toString());
                        }
                        peijianService.insertBatch(peijianList);
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
        PageUtils page = peijianService.queryPage(params);

        //字典表数据转换
        List<PeijianView> list =(List<PeijianView>)page.getList();
        for(PeijianView c:list)
            dictionaryService.dictionaryConvert(c, request); //修改对应字典表字段
        return R.ok().put("data", page);
    }

    /**
    * 前端详情
    */
    @RequestMapping("/detail/{id}")
    public R detail(@PathVariable("id") Long id, HttpServletRequest request){
        logger.debug("detail方法:,,Controller:{},,id:{}",this.getClass().getName(),id);
        PeijianEntity peijian = peijianService.selectById(id);
            if(peijian !=null){


                //entity转view
                PeijianView view = new PeijianView();
                BeanUtils.copyProperties( peijian , view );//把实体数据重构到view中

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
    public R add(@RequestBody PeijianEntity peijian, HttpServletRequest request){
        logger.debug("add方法:,,Controller:{},,peijian:{}",this.getClass().getName(),peijian.toString());
        Wrapper<PeijianEntity> queryWrapper = new EntityWrapper<PeijianEntity>()
            .eq("peijian_uuid_number", peijian.getPeijianUuidNumber())
            .eq("peijian_name", peijian.getPeijianName())
            .eq("peijian_types", peijian.getPeijianTypes())
            .eq("peijian_kucun_number", peijian.getPeijianKucunNumber())
            .eq("peijian_delete", peijian.getPeijianDelete())
            ;
        logger.info("sql语句:"+queryWrapper.getSqlSegment());
        PeijianEntity peijianEntity = peijianService.selectOne(queryWrapper);
        if(peijianEntity==null){
            peijian.setPeijianDelete(1);
            peijian.setCreateTime(new Date());
        peijianService.insert(peijian);
            return R.ok();
        }else {
            return R.error(511,"表中有相同数据");
        }
    }


}
