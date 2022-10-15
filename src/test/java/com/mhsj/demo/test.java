package com.mhsj.demo;

import com.mhsj.demo.mapper.IndentMapper;
import com.mhsj.demo.mapper.ProductMapper;
import com.mhsj.demo.mapper.UserMapper;
import com.mhsj.demo.pojo.DaysalesDay;
import com.mhsj.demo.pojo.Indent;
import com.mhsj.demo.pojo.Product;
import com.mhsj.demo.pojo.User;
import com.mhsj.demo.service.IndentService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@SpringBootTest
public class test {

    @Autowired
    private UserMapper userMapper;
    @Autowired
    private IndentMapper indentMapper;
    @Autowired
    private ProductMapper productMapper;
    @Autowired
    private IndentService indentService;
    @Test
    public void qwe() {
        Indent indent = new Indent("5", "杯子", 5, 20.0,
                "中国",0, 0, "333", "1",  "");
        indentService.save(indent);
    }
}
