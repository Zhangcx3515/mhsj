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
@TableName("product")
@ApiModel("产品实体类")
public class Product {
    @ApiModelProperty("产品id")
    @TableId("pr_id")
    private String prId;
    @ApiModelProperty("产品名")
    @TableField("pr_name")
    private String prName;
    @ApiModelProperty("产品数量")
    @TableField("pr_quantity")
    private Integer prQuantity;
    @ApiModelProperty("产品价格")
    @TableField("pr_price")
    private Double prPrice;
    @ApiModelProperty("产品日销量")
    @TableField("pr_daysales")
    private Integer prDaysales;
    @ApiModelProperty("产品总销量")
    @TableField("pr_sales")
    private Integer prSales;
    @ApiModelProperty("产品库存")
    @TableField("pr_repertpry")
    private Integer prRepertory;
    @ApiModelProperty("产品图")
    @TableField("pr_photo")
    private String prPhoto;
    @ApiModelProperty("品类id")
    @TableField("ki_id")
    private Integer kiId;
    @TableField(exist = false)
    @ApiModelProperty("品类名称")
    private String KiName;
    @TableField("pr_putaway")
    @ApiModelProperty("产品上线")//1 上线  0 下线
    private  Integer prPutaway;
}
