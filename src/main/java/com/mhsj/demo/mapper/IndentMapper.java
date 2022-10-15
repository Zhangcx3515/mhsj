package com.mhsj.demo.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.mhsj.demo.pojo.Indent;
import com.mhsj.demo.pojo.Kind;
import com.mhsj.demo.pojo.Product;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface IndentMapper extends BaseMapper<Indent> {
    //根据用户id查询用户地址
    String selectAddress(String userId);
    //根据品类id查询品类
    List<Kind> selectKind(Integer kiId);
    //根据点击的商品类目查询商品
    List<Product> selectProduct(Integer kiId);
    //通过关键字查询商品(用户输入的是品类)
    List<Product> search(String keyword);
    //通过关键字查询商品（用户输入的是商品）
    List<Product> search1(String keyword);
    //将没查到的商品写入数据库做提示补货
    void noProduct(String keyword);

//    @Select(" SELECT MAX(or_id) FROM userorder;")
//    int getMaxid();
//
//    @Select(" SELECT COUNT(*) FROM userorder WHERE ho_id=#{n};")
//    int getCount(int n);

    //    @Select(" SELECT * FROM userorder WHERE user_id=#{userId};")


    //    @Update("UPDATE userorder SET or_state=#{state} WHERE user_id=#{userId};")
//    void changeState( String state ,String userId);


//    @Delete("DELETE FROM userorder WHERE user_id=#{userId};")
//    void deleteUserOrder(int userId);

    //    @Select("SELECT * FROM userorder WHERE user_id=#{id};")
    List<Indent> getOrders(@Param("id") String id);

    List<Indent> getUserOrder(@Param("userId") String userId,@Param("indentId") String indentId);
    List<Indent> getUserOrder1(@Param("userId") String userId);
    List<Indent> getOrders1(@Param("id") String id);
    /*查询订单状态码为2的订单个数*/
    int indentNumByState(@Param("hoId") String hoId,@Param("inState") int state);
    /*查询订单中的商品个数*/
    int quantityByInId(@Param("hoId") String  hoId,@Param("inId") String indentId);

    void setTime(@Param("end_2")String  end_2,@Param("inId") String inId);

    void setTime2(@Param("end_1")String  end_1,@Param("inId") String inId);


    @Select("SELECT COUNT(*)+1 FROM indent")
    String indentCount();

    @Select("SELECT pr_photo FROM product WHERE pr_name=#{name}")
    String selPhoto(@Param("name") String name);

    @Select("SELECT pr_daysales FROM product WHERE pr_id=#{prId}")
    Integer selDaysales(@Param("prId") Integer prId);

    @Select("SELECT pr_quantity FROM product WHERE pr_id=#{prId}")
    Integer selTotal(@Param("prId") Integer prId);

    @Update("UPDATE product SET pr_daysales=#{prDaySales},pr_total=#{prTotal},pr_repertory=#{prRepertory} WHERE pr_id=#{prId};")
    void updateSales(@Param("prDaySales") Integer prDaySales, @Param("prTotal") Integer prTotal, @Param("prRepertory") Integer prRepertory, @Param("prId") Integer prId);

    @Select("SELECT pr_repertory FROM product WHERE pr_id=#{prId}")
    Integer selRepertory(@Param("prId") Integer prId);

//    @Update("UPDATE product SET pr_daysales=0")
//    void taskScheduler();
//
//    @Select("SELECT or_name FROM userorder WHERE or_id=#{id};")
//    String selName(String id);
//
//    @Update("UPDATE product SET pr_daysales=pr_daysales+1 WHERE pr_name=#{name};")
//    void addDaySalay(String name);

    @Update("UPDATE indent SET ho_id=#{hoId},in_state=#{state} WHERE user_id=#{userId}")
    void updatePro1(@Param("hoId") int hoId, @Param("state") Integer state, @Param("userId") String userId);

    @Update("UPDATE indent SET in_state=#{state} WHERE user_id=#{userId}")
    void updatePro2(@Param("state") Integer state, @Param("userId") String userId);

    @Select("SELECT in_quantity FROM horseman WHERE ho_id=#{hoId};")
    int selNum(@Param("hoId") int hoId);

    @Update("UPDATE horseman SET in_quantity=#{num} WHERE ho_id=#{hoId};")
    void addNum(@Param("num") int num, @Param("hoId") int hoId);

    @Select("SELECT ho_id FROM horseman WHERE in_quantity=(SELECT MIN(in_quantity) FROM horseman) AND ho_state=0;")
    int[] selRider();
}
