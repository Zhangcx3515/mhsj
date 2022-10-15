package com.mhsj.demo.controller;

import com.mhsj.demo.mapper.IndentMapper;
import com.mhsj.demo.pojo.Indent;
import com.mhsj.demo.pojo.Kind;
import com.mhsj.demo.pojo.Product;
import com.mhsj.demo.pojo.User;
import com.mhsj.demo.pojo.dto.OrderDto;
import com.mhsj.demo.pojo.entity.ResponseResult;
import com.mhsj.demo.service.IndentService;
import com.mhsj.demo.service.UserService;
import io.swagger.annotations.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;

@Api("用户端")
@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    private IndentMapper indentMapper;
    @Autowired
    private IndentService indentService;
    @Autowired
    private UserService userService;

    @ApiOperation("根据用户id返回用户当前地址")
    @GetMapping("/useraddress/{id}")
    public String userAddress(@PathVariable @ApiParam(name = "id", value = "用户ID") String id) {
        String address = indentMapper.selectAddress(id);
        String warn = "没有查到您的地址，请检查个人信息";
        if (address == null) {
            return warn;
        } else {
            return address;
        }
    }

    @ApiOperation("根据根据商品类目的id返回商品的分类")
    @GetMapping("/getCategories/{kiId}")
    public List getCategories(@PathVariable @ApiParam(name = "kiId", value = "品类ID") Integer kiId) {
        List<Kind> kinds = indentMapper.selectKind(kiId);
        return kinds;
    }

    @ApiOperation("根据用户点击的商品类目返回具体商品")
    @GetMapping("/getCAList/{kiId}")
    public List<Product> getCAList(@PathVariable @ApiParam(name = "kiId", value = "品类ID") Integer kiId) {
        List<Product> products = indentMapper.selectProduct(kiId);
        return products;
    }

    //通过用户搜索查找商品
    @GetMapping("/search")
    @ApiOperation("用户端产品搜索框")
    public List<Product> search(@RequestParam @ApiParam(name = "keyword", value = "关键字") String keyword)
    {
        String kind1 = "饮料";
        String kind2 = "水果";
        if (keyword.contains(kind1)||keyword.contains(kind2))          //对用户的输入词做判断通过类品查商品还是直接查商品
        {
            List<Product> search = indentMapper.search(keyword);
            return search;
        }
        else
        {
            List<Product> products = indentMapper.search1(keyword);
            if (!products.isEmpty())
            {
                return products;
            }
            else
            {
                indentMapper.noProduct(keyword);
                return null;
            }
        }
    }

//    @ApiOperation("获取时间")
//    @GetMapping("/getorderTime")
//    public ResponseResult getOrderTime() {
//        Calendar calendar = Calendar.getInstance();
//        calendar.setTime(new Date());
//        calendar.add(Calendar.MINUTE, +20);
//        String time = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(calendar.getTime());
//        System.out.println(time);
//        return ResponseResult.okResult(time);
//    }

    @ApiOperation("提交订单")
    @PostMapping("/addorder")
    public ResponseResult addOrder(@RequestBody OrderDto orderDto) throws ParseException {
        String userId = orderDto.getUserId();
        //完善个人信息
        System.out.println(orderDto);
        User user = new User();
        user.setId(userId);
        user.setAddress(orderDto.getAddress());
//        UserAddress userAddress = new UserAddress();
//        userAddress.setId(userId);
//        userAddress.setUserSite(orderDto.getAddress());
        userService.updateById(user);
        String indentCount = indentMapper.indentCount();
        //记录下单时间
        LocalDateTime now = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        String currentTime = now.format(formatter);
        //遍历要添加的每一个订单项并给每一个订单项设置属性值(除骑手字段)
        List<Indent> ordergoods = orderDto.getList();
        for (Indent ordergood : ordergoods) {
            ordergood.setInPhone(orderDto.getPhone());
            ordergood.setInMoney(ordergood.getPrice() * ordergood.getInQuantity());
            ordergood.setInAddress(orderDto.getAddress());
            ordergood.setUserId(userId);
            ordergood.setInId(indentCount);
            ordergood.setInState(1);//下单未支付
            ordergood.setStart(currentTime);
            String inName = ordergood.getInName();
            String photoPath = indentMapper.selPhoto(inName);
            ordergood.setInPhoto(photoPath);
            indentService.save(ordergood);
        }

        int inState = 2;
        int indentNum = indentMapper.indentNumByState("1", inState);
        int quantity = indentMapper.quantityByInId("1",indentCount);
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");  //设置指定格式
        Date parse = simpleDateFormat.parse(currentTime);   //将时间字符串转为date格式
        long time1 = parse.getTime();           //获取date毫秒数
        long newtime = 0;
        long readyTime = 0;
        long inTime = 0;
        if (quantity <= 5 && quantity > 0) {
            readyTime = indentNum * 60 + 10;
            inTime = (300 + readyTime * 2) * 1000;
            newtime = time1 + inTime;
        } else if (quantity > 5 && quantity <= 10) {
            readyTime = indentNum * 60 + 20;
            inTime = (300 + readyTime * 2) * 1000;
            newtime = time1 + inTime;
        } else if (quantity > 10) {
            readyTime = indentNum * 60 + 30;
            inTime = (300 + readyTime * 2) * 1000;
            newtime = time1 + inTime;
        } else {
        }
        Date date = new Date(newtime);                     //将算法后的时间转为date格式
        String format = simpleDateFormat.format(date);      //将date格式的时间转为字符串
        Date date1 = new Date(inTime);
        String format1 = simpleDateFormat.format(date1);
        indentMapper.setTime(format, indentCount);
        indentMapper.setTime2(format1,indentCount);


        //分配骑手
        ResponseResult result = disRider(userId);
        return ResponseResult.okResult(result);
    }

    @ApiOperation("将订单表中的数据返回到订单页面")
    @GetMapping("/userorder")
    public ResponseResult userorder(@RequestParam @ApiParam(name = "id", value = "用户ID") String id) {
        List<Indent> indentList = indentMapper.getUserOrder1(id);
        if (indentList != null) {
            List<Indent> orders = new ArrayList<>();
            if (indentList.get(indentList.size() - 1).getHoId() != null) {
                orders = indentMapper.getOrders(id);
            } else {
                orders = indentMapper.getOrders1(id);
            }
            return ResponseResult.okResult(orders);
        }
        return null;
    }

    @ApiOperation("立即支付按钮")
    @PostMapping("/payment")
    public ResponseResult Payment(@RequestBody @ApiParam(name = "userId", value = "用户ID") String userId,
                                  @RequestBody @ApiParam(name = "indentId", value = "订单ID") String indentId) {

        //支付接口?

        List<Indent> indentList = indentMapper.getUserOrder(userId,indentId);
        //遍历订单每一项,改变订单状态为支付成功,等待配货
        for (int i = 0; i < indentList.size(); i++) {
            Indent indent = indentList.get(i);
            indent.setInState(2);//支付成功,等待配货
            indentMapper.updatePro2(indent.getInState(), userId);
        }
        return ResponseResult.okResult("支付成功,等待商家配货");
    }

    @ApiOperation("取消订单按钮")
    @PostMapping("/cancel")
    public ResponseResult Cancel(@RequestBody @ApiParam(name = "userId", value = "用户ID") String userId,
                                 @RequestBody @ApiParam(name = "indentId", value = "订单ID") String indentId) {
        List<Indent> indentList = indentMapper.getUserOrder(userId,indentId);
        //遍历订单每一项,改变订单状态为取消订单
        for (int i = 0; i < indentList.size(); i++) {
            Indent indent = indentList.get(i);
            indent.setInState(0);//取消订单
            indentMapper.updatePro2(indent.getInState(), userId);
        }
        return ResponseResult.okResult("订单已取消");
    }

    @ApiOperation("点击确认收货,修改订单状态为确认收货")
    @PostMapping("/receive")
    public ResponseResult receive(@RequestBody @ApiParam(name = "userId", value = "用户ID") String userId,
                                  @RequestBody @ApiParam(name = "indentId", value = "订单ID") String indentId) {
        List<Indent> indentList = indentMapper.getUserOrder(userId,indentId);
        //遍历订单每一项,改变订单状态为确认收货
        for (int i = 0; i < indentList.size(); i++) {
            Indent indent = indentList.get(i);
            indent.setInState(6);//确认收货,完成订单
            indentMapper.updatePro2(indent.getInState(), userId);
        }
        return ResponseResult.okResult("确认收货,完成订单");
    }

    @ApiOperation("点击退款,修改订单状态为退款中(state=退款中)")
    @PostMapping("/refund")
    public ResponseResult refund(@RequestBody @ApiParam(name = "userId", value = "用户ID") String userId,
                                 @RequestBody @ApiParam(name = "indentId", value = "订单ID") String indentId) {
        List<Indent> indentList = indentMapper.getUserOrder(userId,indentId);
        //遍历订单每一项,改变订单状态为退款中
        for (int i = 0; i < indentList.size(); i++) {
            Indent indent = indentList.get(i);
            indent.setInState(7);//申请退款,等待商家同意
            indentMapper.updatePro2(indent.getInState(), userId);
        }
        return ResponseResult.okResult("退款中,等待商家同意");
    }

    //分配骑手
    public ResponseResult disRider(String userId) {
        List<Indent> userOrderList = indentMapper.getOrders1(userId);
        //查询出所有正在上班状态并且当前单量最少的的骑手id
        int[] riderList = indentMapper.selRider();
        int rider;
        if (riderList.length == 1) {
            rider = riderList[0];
        } else {
            //如果符合条件的骑手不唯一就随机分配
            Random random = new Random();
            int nextInt = random.nextInt(riderList.length);
            rider = riderList[nextInt];
        }
        //遍历订单的每一项,给每一项分配骑手
        for (int i = 0; i < userOrderList.size(); i++) {
            Indent indent = userOrderList.get(i);
            indent.setInState(4);//取货成功,正在配送
            indentMapper.updatePro1(rider, indent.getInState(), userId);
            //设置日销量,总销量,库存
            Integer total = indent.getInQuantity();
            Integer prId = indent.getPrId();
            Integer daysales = indentMapper.selDaysales(prId);
            daysales = daysales + total;
            Integer productTotal = indentMapper.selTotal(prId);
            productTotal = productTotal + total;
            Integer repertory = indentMapper.selRepertory(prId);
            repertory = repertory - total;
            indentMapper.updateSales(daysales, productTotal, repertory, prId);
        }
        //分配成功后给该骑手的当前订单量加1
        int num = indentMapper.selNum(rider);
        num++;
        indentMapper.addNum(num, rider);
        return ResponseResult.okResult("取货成功,正在配送");
    }
}