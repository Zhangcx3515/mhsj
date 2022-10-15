package com.mhsj.demo.pojo;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
@TableName("indent")
@ApiModel("订单实体类")
public class Indent {
    @ApiModelProperty("订单id")
    @TableId("in_id")
    private String inId;
    @ApiModelProperty("订单名")
    @TableField("in_name")
    private String inName;
    @ApiModelProperty("订单数量")
    @TableField("in_quantity")
    private Integer inQuantity;
    @ApiModelProperty("订单价格")
    @TableField("in_money")
    private Double inMoney;
    @ApiModelProperty("订单地址")
    @TableField("in_address")
    private String inAddress;
    @ApiModelProperty("订单状态")
    @TableField("in_state")
    private Integer inState;
    @ApiModelProperty("订单号码")
    @TableField("in_phone")
    private Integer inPhone;
    @ApiModelProperty("用户id")
    @TableField("user_id")
    private String userId;
    @ApiModelProperty("骑手id")
    @TableField("ho_id")
    private String hoId;
    @ApiModelProperty("产品图片")
    @TableField("in_photo")
    private String inPhoto;
    @ApiModelProperty("下单时间")
    @TableField("start")
    private String start;
    @ApiModelProperty("商家规定结束")
    @TableField("end_1")
    private String end1;
    @ApiModelProperty("骑手规定结束")
    @TableField("end_2")
    private String end2;
    @ApiModelProperty("商家结束的真实时间")
    @TableField("actual_1")
    private String actual1;
    @ApiModelProperty("骑手结束的真实时间")
    @TableField("actual_2")
    private String actual2;
    @ApiModelProperty("")
    @TableField("now_time")
    private String nowTime;
    @ApiModelProperty("商品id")
    @TableField(exist = false)
    private Integer prId;
    @ApiModelProperty("骑手电话")
    @TableField(exist = false)
    private String hoPhone;
    @ApiModelProperty("商品单价")
    @TableField(exist = false)
    private Double price;

    public Indent(String inId, String inName, Integer inQuantity, Double inMoney, String inAddress, Integer inState, Integer inPhone, String userId, String hoId, String inPhoto) {
        this.inId = inId;
        this.inName = inName;
        this.inQuantity = inQuantity;
        this.inMoney = inMoney;
        this.inAddress = inAddress;
        this.inState = inState;
        this.inPhone = inPhone;
        this.userId = userId;
        this.hoId = hoId;
        this.inPhoto = inPhoto;
    }
}
