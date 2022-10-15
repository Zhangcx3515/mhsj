package com.mhsj.demo.controller;

import com.mhsj.demo.mapper.ProductMapper;
import com.mhsj.demo.pojo.DaysalesDay;
import com.mhsj.demo.pojo.Kind;
import com.mhsj.demo.pojo.Product;
import com.mhsj.demo.pojo.entity.ResponseResult;
import com.mhsj.demo.service.OssService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@Api("商家端新增产品")
public class ProductController {

    @Autowired
    private ProductMapper productMapper;
    @Autowired
    private OssService ossService;

    @ApiOperation("查询相关产品")
    @GetMapping("/selectproductlist")
    public ResponseResult SelectProductList(@RequestParam("name") @ApiParam(name = "name", value = "产品名") String name) {
        List<Product> productList = productMapper.SelcetProducts(name);
        String warn = "未查到相关产品";
        if (productList != null) {
            return ResponseResult.okResult(productList);
        }
        return ResponseResult.okResult(warn);
    }

    @ApiOperation("在名称输入框上绑定一个请求,判断要添加的产品是否已经存在")
    @GetMapping("/exist")
    public ResponseResult Exist(@RequestParam("name") @ApiParam(name = "name", value = "产品名") String name) {
        Product product = productMapper.SelcetProduct(name);
        if (product!=null){
            return ResponseResult.okResult("该产品已经存在");
        }
        return ResponseResult.okResult("");
    }

    @ApiOperation("添加产品及信息")
    @PostMapping(value = "/addinfo",consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public void AddInfo(@RequestParam("name") String name, @RequestParam("price") Double price,
                        @RequestParam("repertory") Integer repertory, @RequestParam("kind") String kind,
                        @RequestPart("productPhoto") MultipartFile productPhoto,
                        @RequestPart("kindPhoto") MultipartFile kindPhoto) {
        Product product = new Product();
        //查看数据库是否已有该类目
        Kind productKind = productMapper.SelectNameByName(kind);
        //如果数据库没有该品类则添加自定义类目
        if (productKind == null) {
            productMapper.AddKind(kind);
            //上传品类图片
            if (!kindPhoto.isEmpty()) {
                String upload = ossService.upload(kindPhoto);
                productKind.setKiPhoto(upload);
            }
        }
        //上传产品图片
        if (!productPhoto.isEmpty()) {
            String upload = ossService.upload(productPhoto);
            product.setPrPhoto(upload);
        }
        //添加产品信息
        Integer kiId = productKind.getKiId();
        product.setKiId(kiId);
        product.setPrDaysales(0);
        product.setPrSales(0);
        product.setPrName(name);
        product.setPrRepertory(repertory);
        product.setPrPrice(price);
        product.setPrPutaway(1);
        productMapper.AddProduct(product);
        //设置日销量信息
        DaysalesDay daysalesDay = new DaysalesDay();
        daysalesDay.setOneDay(0);
        daysalesDay.setTwoDay(0);
        daysalesDay.setThreeDay(0);
        daysalesDay.setFourDay(0);
        daysalesDay.setFiveDay(0);
        daysalesDay.setSixDay(0);
        daysalesDay.setSevenDay(0);
        daysalesDay.setPrName(name);
        daysalesDay.setPrPutaway(1);
    }
}
