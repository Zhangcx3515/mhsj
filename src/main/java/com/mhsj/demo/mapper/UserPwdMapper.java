package com.mhsj.demo.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.mhsj.demo.pojo.UserPwd;
import org.apache.ibatis.annotations.Mapper;

/**
 * (User)表数据库访问层
 *
 * @author makejava
 * @since 2022-08-10 21:06:46
 */
@Mapper
public interface UserPwdMapper extends BaseMapper<UserPwd> {

}

