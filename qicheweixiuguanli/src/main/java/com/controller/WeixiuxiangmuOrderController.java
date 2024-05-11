
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
 * 维修订单
 * 后端接口
 * @author
 * @email
*/
@RestController
@Controller
@RequestMapping("/weixiuxiangmuOrder")
public class WeixiuxiangmuOrderController {
    private static final Logger logger = LoggerFactory.getLogger(WeixiuxiangmuOrderController.class);

    @Autowired
    private WeixiuxiangmuOrderService weixiuxiangmuOrderService;


    @Autowired
    private TokenService tokenService;
    @Autowired
    private DictionaryService dictionaryService;

    //级联表service
    @Autowired
    private CheliangService cheliangService;
    @Autowired
    private WeixiuxiangmuService weixiuxiangmuService;
    @Autowired
    private YonghuService yonghuService;



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
        PageUtils page = weixiuxiangmuOrderService.queryPage(params);

        //字典表数据转换
        List<WeixiuxiangmuOrderView> list =(List<WeixiuxiangmuOrderView>)page.getList();
        for(WeixiuxiangmuOrderView c:list){
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
        WeixiuxiangmuOrderEntity weixiuxiangmuOrder = weixiuxiangmuOrderService.selectById(id);
        if(weixiuxiangmuOrder !=null){
            //entity转view
            WeixiuxiangmuOrderView view = new WeixiuxiangmuOrderView();
            BeanUtils.copyProperties( weixiuxiangmuOrder , view );//把实体数据重构到view中

                //级联表
                CheliangEntity cheliang = cheliangService.selectById(weixiuxiangmuOrder.getCheliangId());
                if(cheliang != null){
                    BeanUtils.copyProperties( cheliang , view ,new String[]{ "id", "createTime", "insertTime", "updateTime", "yonghuId"});//把级联的数据添加到view中,并排除id和创建时间字段
                    view.setCheliangId(cheliang.getId());
                    view.setCheliangYonghuId(cheliang.getYonghuId());
                }
                //级联表
                WeixiuxiangmuEntity weixiuxiangmu = weixiuxiangmuService.selectById(weixiuxiangmuOrder.getWeixiuxiangmuId());
                if(weixiuxiangmu != null){
                    BeanUtils.copyProperties( weixiuxiangmu , view ,new String[]{ "id", "createTime", "insertTime", "updateTime"});//把级联的数据添加到view中,并排除id和创建时间字段
                    view.setWeixiuxiangmuId(weixiuxiangmu.getId());
                }
                //级联表
                YonghuEntity yonghu = yonghuService.selectById(weixiuxiangmuOrder.getYonghuId());
                if(yonghu != null){
                    BeanUtils.copyProperties( yonghu , view ,new String[]{ "id", "createTime", "insertTime", "updateTime"});//把级联的数据添加到view中,并排除id和创建时间字段
                    view.setYonghuId(yonghu.getId());
                }
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
    public R save(@RequestBody WeixiuxiangmuOrderEntity weixiuxiangmuOrder, HttpServletRequest request){
        logger.debug("save方法:,,Controller:{},,weixiuxiangmuOrder:{}",this.getClass().getName(),weixiuxiangmuOrder.toString());

        String role = String.valueOf(request.getSession().getAttribute("role"));
        if(false)
            return R.error(511,"永远不会进入");
        else if("用户".equals(role))
            weixiuxiangmuOrder.setYonghuId(Integer.valueOf(String.valueOf(request.getSession().getAttribute("userId"))));

        weixiuxiangmuOrder.setInsertTime(new Date());
        weixiuxiangmuOrder.setCreateTime(new Date());
        weixiuxiangmuOrderService.insert(weixiuxiangmuOrder);
        return R.ok();
    }

    /**
    * 后端修改
    */
    @RequestMapping("/update")
    public R update(@RequestBody WeixiuxiangmuOrderEntity weixiuxiangmuOrder, HttpServletRequest request){
        logger.debug("update方法:,,Controller:{},,weixiuxiangmuOrder:{}",this.getClass().getName(),weixiuxiangmuOrder.toString());

        String role = String.valueOf(request.getSession().getAttribute("role"));
//        if(false)
//            return R.error(511,"永远不会进入");
//        else if("用户".equals(role))
//            weixiuxiangmuOrder.setYonghuId(Integer.valueOf(String.valueOf(request.getSession().getAttribute("userId"))));
        //根据字段查询是否有相同数据
        Wrapper<WeixiuxiangmuOrderEntity> queryWrapper = new EntityWrapper<WeixiuxiangmuOrderEntity>()
            .eq("id",0)
            ;

        logger.info("sql语句:"+queryWrapper.getSqlSegment());
        WeixiuxiangmuOrderEntity weixiuxiangmuOrderEntity = weixiuxiangmuOrderService.selectOne(queryWrapper);
        if(weixiuxiangmuOrderEntity==null){
            weixiuxiangmuOrderService.updateById(weixiuxiangmuOrder);//根据id更新
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
        weixiuxiangmuOrderService.deleteBatchIds(Arrays.asList(ids));
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
            List<WeixiuxiangmuOrderEntity> weixiuxiangmuOrderList = new ArrayList<>();//上传的东西
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
                            WeixiuxiangmuOrderEntity weixiuxiangmuOrderEntity = new WeixiuxiangmuOrderEntity();
//                            weixiuxiangmuOrderEntity.setWeixiuxiangmuOrderUuidNumber(data.get(0));                    //订单号 要改的
//                            weixiuxiangmuOrderEntity.setWeixiuxiangmuId(Integer.valueOf(data.get(0)));   //维修项目 要改的
//                            weixiuxiangmuOrderEntity.setYonghuId(Integer.valueOf(data.get(0)));   //用户 要改的
//                            weixiuxiangmuOrderEntity.setCheliangId(Integer.valueOf(data.get(0)));   //维修车辆 要改的
//                            weixiuxiangmuOrderEntity.setWeixiuxiangmuOrderTime(sdf.parse(data.get(0)));          //预约时间 要改的
//                            weixiuxiangmuOrderEntity.setWeixiuxiangmuOrderTruePrice(data.get(0));                    //实付价格 要改的
//                            weixiuxiangmuOrderEntity.setWeixiuxiangmuOrderTypes(Integer.valueOf(data.get(0)));   //订单类型 要改的
//                            weixiuxiangmuOrderEntity.setInsertTime(date);//时间
//                            weixiuxiangmuOrderEntity.setCreateTime(date);//时间
                            weixiuxiangmuOrderList.add(weixiuxiangmuOrderEntity);


                            //把要查询是否重复的字段放入map中
                                //订单号
                                if(seachFields.containsKey("weixiuxiangmuOrderUuidNumber")){
                                    List<String> weixiuxiangmuOrderUuidNumber = seachFields.get("weixiuxiangmuOrderUuidNumber");
                                    weixiuxiangmuOrderUuidNumber.add(data.get(0));//要改的
                                }else{
                                    List<String> weixiuxiangmuOrderUuidNumber = new ArrayList<>();
                                    weixiuxiangmuOrderUuidNumber.add(data.get(0));//要改的
                                    seachFields.put("weixiuxiangmuOrderUuidNumber",weixiuxiangmuOrderUuidNumber);
                                }
                        }

                        //查询是否重复
                         //订单号
                        List<WeixiuxiangmuOrderEntity> weixiuxiangmuOrderEntities_weixiuxiangmuOrderUuidNumber = weixiuxiangmuOrderService.selectList(new EntityWrapper<WeixiuxiangmuOrderEntity>().in("weixiuxiangmu_order_uuid_number", seachFields.get("weixiuxiangmuOrderUuidNumber")));
                        if(weixiuxiangmuOrderEntities_weixiuxiangmuOrderUuidNumber.size() >0 ){
                            ArrayList<String> repeatFields = new ArrayList<>();
                            for(WeixiuxiangmuOrderEntity s:weixiuxiangmuOrderEntities_weixiuxiangmuOrderUuidNumber){
                                repeatFields.add(s.getWeixiuxiangmuOrderUuidNumber());
                            }
                            return R.error(511,"数据库的该表中的 [订单号] 字段已经存在 存在数据为:"+repeatFields.toString());
                        }
                        weixiuxiangmuOrderService.insertBatch(weixiuxiangmuOrderList);
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
        PageUtils page = weixiuxiangmuOrderService.queryPage(params);

        //字典表数据转换
        List<WeixiuxiangmuOrderView> list =(List<WeixiuxiangmuOrderView>)page.getList();
        for(WeixiuxiangmuOrderView c:list)
            dictionaryService.dictionaryConvert(c, request); //修改对应字典表字段
        return R.ok().put("data", page);
    }

    /**
    * 前端详情
    */
    @RequestMapping("/detail/{id}")
    public R detail(@PathVariable("id") Long id, HttpServletRequest request){
        logger.debug("detail方法:,,Controller:{},,id:{}",this.getClass().getName(),id);
        WeixiuxiangmuOrderEntity weixiuxiangmuOrder = weixiuxiangmuOrderService.selectById(id);
            if(weixiuxiangmuOrder !=null){


                //entity转view
                WeixiuxiangmuOrderView view = new WeixiuxiangmuOrderView();
                BeanUtils.copyProperties( weixiuxiangmuOrder , view );//把实体数据重构到view中

                //级联表
                    CheliangEntity cheliang = cheliangService.selectById(weixiuxiangmuOrder.getCheliangId());
                if(cheliang != null){
                    BeanUtils.copyProperties( cheliang , view ,new String[]{ "id", "createDate"});//把级联的数据添加到view中,并排除id和创建时间字段
                    view.setCheliangId(cheliang.getId());
                }
                //级联表
                    WeixiuxiangmuEntity weixiuxiangmu = weixiuxiangmuService.selectById(weixiuxiangmuOrder.getWeixiuxiangmuId());
                if(weixiuxiangmu != null){
                    BeanUtils.copyProperties( weixiuxiangmu , view ,new String[]{ "id", "createDate"});//把级联的数据添加到view中,并排除id和创建时间字段
                    view.setWeixiuxiangmuId(weixiuxiangmu.getId());
                }
                //级联表
                    YonghuEntity yonghu = yonghuService.selectById(weixiuxiangmuOrder.getYonghuId());
                if(yonghu != null){
                    BeanUtils.copyProperties( yonghu , view ,new String[]{ "id", "createDate"});//把级联的数据添加到view中,并排除id和创建时间字段
                    view.setYonghuId(yonghu.getId());
                }
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
    public R add(@RequestBody WeixiuxiangmuOrderEntity weixiuxiangmuOrder, HttpServletRequest request){
        logger.debug("add方法:,,Controller:{},,weixiuxiangmuOrder:{}",this.getClass().getName(),weixiuxiangmuOrder.toString());
            WeixiuxiangmuEntity weixiuxiangmuEntity = weixiuxiangmuService.selectById(weixiuxiangmuOrder.getWeixiuxiangmuId());
            if(weixiuxiangmuEntity == null){
                return R.error(511,"查不到该维修项目");
            }
            if(false){
            }
            else if(weixiuxiangmuEntity.getWeixiuxiangmuNewMoney() == null){
                return R.error(511,"维修项目价格不能为空");
            }

            //计算所获得积分
            Double buyJifen =0.0;
            Integer userId = (Integer) request.getSession().getAttribute("userId");
            YonghuEntity yonghuEntity = yonghuService.selectById(userId);
            if(yonghuEntity == null)
                return R.error(511,"用户不能为空");
            if(yonghuEntity.getNewMoney() == null)
                return R.error(511,"用户金额不能为空");
            double balance = yonghuEntity.getNewMoney() - weixiuxiangmuEntity.getWeixiuxiangmuNewMoney()*1;//余额
            if(balance<0)
                return R.error(511,"余额不够支付");
            weixiuxiangmuOrder.setWeixiuxiangmuOrderTypes(1); //设置订单状态为已支付
            weixiuxiangmuOrder.setWeixiuxiangmuOrderTruePrice(weixiuxiangmuEntity.getWeixiuxiangmuNewMoney()*1); //设置实付价格
            weixiuxiangmuOrder.setYonghuId(userId); //设置订单支付人id
            weixiuxiangmuOrder.setWeixiuxiangmuOrderUuidNumber(String.valueOf(new Date().getTime()));
            weixiuxiangmuOrder.setCheliangId(weixiuxiangmuOrder.getCheliangId());
            weixiuxiangmuOrder.setWeixiuxiangmuOrderTime(weixiuxiangmuOrder.getWeixiuxiangmuOrderTime());
            weixiuxiangmuOrder.setInsertTime(new Date());
            weixiuxiangmuOrder.setCreateTime(new Date());
                weixiuxiangmuOrderService.insert(weixiuxiangmuOrder);//新增订单
            yonghuEntity.setNewMoney(balance);//设置金额
            yonghuService.updateById(yonghuEntity);
            return R.ok();
    }

    /**
    * 退款
    */
    @RequestMapping("/refund")
    public R refund(Integer id, HttpServletRequest request){
        logger.debug("refund方法:,,Controller:{},,id:{}",this.getClass().getName(),id);
        String role = String.valueOf(request.getSession().getAttribute("role"));

            WeixiuxiangmuOrderEntity weixiuxiangmuOrder = weixiuxiangmuOrderService.selectById(id);
            Integer buyNumber = 1;
            Integer weixiuxiangmuOrderPaymentTypes = 1;
            Integer weixiuxiangmuId = weixiuxiangmuOrder.getWeixiuxiangmuId();
            if(weixiuxiangmuId == null)
                return R.error(511,"查不到该维修项目");
            WeixiuxiangmuEntity weixiuxiangmuEntity = weixiuxiangmuService.selectById(weixiuxiangmuId);
            if(weixiuxiangmuEntity == null)
                return R.error(511,"查不到该维修项目");
            Double weixiuxiangmuNewMoney = weixiuxiangmuEntity.getWeixiuxiangmuNewMoney();
            if(weixiuxiangmuNewMoney == null)
                return R.error(511,"维修项目价格不能为空");

            Integer userId = (Integer) request.getSession().getAttribute("userId");
            YonghuEntity yonghuEntity = yonghuService.selectById(userId);
            if(yonghuEntity == null)
                return R.error(511,"用户不能为空");
            if(yonghuEntity.getNewMoney() == null)
                return R.error(511,"用户金额不能为空");

            Double zhekou = 1.0;


            //判断是什么支付方式 1代表余额 2代表积分
            if(weixiuxiangmuOrderPaymentTypes == 1){//余额支付
                //计算金额
                Double money = weixiuxiangmuEntity.getWeixiuxiangmuNewMoney() * buyNumber  * zhekou;
                //计算所获得积分
                Double buyJifen = 0.0;
                yonghuEntity.setNewMoney(yonghuEntity.getNewMoney() + money); //设置金额


            }


            weixiuxiangmuOrder.setWeixiuxiangmuOrderTypes(2);//设置订单状态为退款
            weixiuxiangmuOrderService.updateById(weixiuxiangmuOrder);//根据id更新
            yonghuService.updateById(yonghuEntity);//更新用户信息
            weixiuxiangmuService.updateById(weixiuxiangmuEntity);//更新订单中维修项目的信息
            return R.ok();
    }


    /**
     * 发货
     */
    @RequestMapping("/deliver")
    public R deliver(Integer id ){
        logger.debug("refund:,,Controller:{},,ids:{}",this.getClass().getName(),id.toString());
        WeixiuxiangmuOrderEntity  weixiuxiangmuOrderEntity = new  WeixiuxiangmuOrderEntity();;
        weixiuxiangmuOrderEntity.setId(id);
        weixiuxiangmuOrderEntity.setWeixiuxiangmuOrderTypes(3);
        boolean b =  weixiuxiangmuOrderService.updateById( weixiuxiangmuOrderEntity);
        if(!b){
            return R.error("操作出错");
        }
        return R.ok();
    }

















}
