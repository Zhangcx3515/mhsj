package com.mhsj.demo.pojo;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
@TableName("horseman")
public class Horseman {
    @TableId("ho_id")
    private String id;
    @ApiModelProperty("骑手账号")
    @TableField("ho_account")
    private String account;
    @ApiModelProperty("骑手账号密码")
    @TableField("ho_password")
    private String password;
    @ApiModelProperty("骑手电话")
    @TableField("ho_phone")
    private Integer phone;
    @ApiModelProperty("骑手状态")//0为上班状态
    @TableField("ho_state")
    private Integer state;
    @ApiModelProperty("骑手手中单量")
    @TableField("in_quantity")
    private Integer quantity;
    @TableField("in_id")
    private String inId;

}
