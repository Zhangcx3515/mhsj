package com.mhsj.demo.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.mhsj.demo.pojo.DaysalesDay;
import com.mhsj.demo.pojo.Kind;
import com.mhsj.demo.pojo.Product;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface ProductMapper extends BaseMapper<Product> {
    @Select("SELECT pr_name,pr_price,pr_repertory,ki_id,pr_photo FROM product WHERE pr_name LIKE CONCAT('%',#{name},'%')")
    List<Product> SelcetProducts(@Param("name") String name);//根据名称查相关产品
    @Select("SELECT pr_name,pr_price,pr_repertory,ki_id,pr_photo FROM product WHERE pr_name=#{name}")
    Product SelcetProduct(@Param("name") String name);//根据名称查产品
    @Insert("insert into product (pr_name,pr_price,pr_daysales,pr_sales,pr_repertory,ki_id,pr_photo,pr_putaway) " +
            "values (#{prName},#{prPrice},#{prDaysales},#{prSales},#{prRepertory},#{kiId},#{prPhoto},#{prPutaway})")
    void AddProduct(Product product);//添加产品
    @Select("SELECT ki_name FROM kind WHERE ki_id=#{id}")
    String SelectNameById(@Param("id") Integer id);//根据品类id查询品类名字
    @Select("SELECT * FROM kind WHERE ki_name=#{name}")
    Kind SelectNameByName(@Param("name") String name);
    @Insert("insert into kind (ki_name) values (#{name})")
    void AddKind(@Param(("name")) String name);//添加品类
    @Insert("INSERT INTO daysales_day (one_day,two_day,three_day,four_day,five_day,six_day,seven_day,pr_name,pr_putaway)\n" +
            "VALUES (#{oneDay},#{twoDay},#{threeDay},#{fourDay},#{fiveDay},#{sixDay},#{sevenDay},#{prName},#{prPutaway})")
    void AddDaysales(DaysalesDay daysalesDay);

}
