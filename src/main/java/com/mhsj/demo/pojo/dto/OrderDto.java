package com.mhsj.demo.pojo.dto;

import com.mhsj.demo.pojo.Indent;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class OrderDto {
    private String userId;
    private Long amount;
    private List<Indent> list;
    private Integer phone;
    private String address;

}
