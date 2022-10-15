package com.mhsj.demo.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.mhsj.demo.pojo.Horseman;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public  interface HorsemanMapper extends BaseMapper<Horseman> {
}
