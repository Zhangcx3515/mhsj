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
@TableName("daysales_day")
@ApiModel("销量表")
public class DaysalesDay {
    @TableId("id")
    @ApiModelProperty("id")
    private String id;

    @TableField("one_day")
    @ApiModelProperty("前一天的日销量")
    private Integer oneDay;

    @TableField("two_day")
    @ApiModelProperty("前两天天的日销量")
    private Integer twoDay;

    @TableField("three_day")
    @ApiModelProperty("前三天的日销量")
    private Integer threeDay;

    @TableField("four_day")
    @ApiModelProperty("前四天的日销量")
    private Integer fourDay;

    @TableField("five_day")
    @ApiModelProperty("前五天的日销量")
    private Integer fiveDay;

    @TableField("six_day")
    @ApiModelProperty("前六天的日销量")
    private Integer sixDay;

    @TableField("seven_day")
    @ApiModelProperty("前七天的日销量")
    private Integer sevenDay;

    @TableField("pr_name")
    @ApiModelProperty("产品名")
    private String prName;

    @TableField("pr_putaway")
    @ApiModelProperty("产品上线")//1 上线  0 下线
    private Integer prPutaway;



}
