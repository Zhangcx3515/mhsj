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
@TableName("users")
public class User {
    @TableId("user_id")
    private String id;
    @ApiModelProperty("用户地址")
    @TableField("user_address")
    private String address;
    @ApiModelProperty("用户电话")
    @TableField("user_phone")
    private Integer phone;
}
