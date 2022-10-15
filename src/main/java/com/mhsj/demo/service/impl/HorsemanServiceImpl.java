package com.mhsj.demo.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.mhsj.demo.mapper.HorsemanMapper;
import com.mhsj.demo.pojo.Horseman;
import com.mhsj.demo.service.HorsemanService;
import org.springframework.stereotype.Service;

@Service
public class HorsemanServiceImpl extends ServiceImpl<HorsemanMapper, Horseman> implements HorsemanService {
}
