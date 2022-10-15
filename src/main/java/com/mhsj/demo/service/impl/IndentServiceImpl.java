package com.mhsj.demo.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.mhsj.demo.mapper.IndentMapper;
import com.mhsj.demo.pojo.Indent;
import com.mhsj.demo.service.IndentService;
import org.springframework.stereotype.Service;

@Service
public class IndentServiceImpl extends ServiceImpl<IndentMapper, Indent> implements IndentService {
}
