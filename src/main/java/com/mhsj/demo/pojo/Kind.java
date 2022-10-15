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
@TableName("kind")
@ApiModel("品类实体类")
public class Kind {
    @ApiModelProperty("品类id")
    @TableId("ki_id")
    private Integer kiId;
    @ApiModelProperty("品类名")
    @TableField("ki_name")
    private String kiName;
    @ApiModelProperty("品类图")
    @TableField("ki_photo")
    private String kiPhoto;
}
