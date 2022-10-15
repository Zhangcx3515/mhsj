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
@TableName("merchant")
@ApiModel("商家实体类")
public class Merchant {
    @ApiModelProperty("商家id")
    @TableId("me_id")
    private String meId;
    @ApiModelProperty("商家账号")
    @TableField("me_account")
    private String meAccount;
    @ApiModelProperty("商家密码")
    @TableField("me_password")
    private String mePassword;
}