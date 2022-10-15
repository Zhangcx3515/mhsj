package com.mhsj.demo.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.mhsj.demo.mapper.UserMapper;
import com.mhsj.demo.mapper.UserPwdMapper;
import com.mhsj.demo.pojo.User;
import com.mhsj.demo.pojo.UserPwd;
import com.mhsj.demo.pojo.entity.ResponseResult;
import com.mhsj.demo.service.UserPwdService;
import com.mhsj.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Objects;

/**
 * (User)表服务实现类
 *
 * @author hdka
 * @since 2022-08-10 21:06:49
 */
@Service("userService")
public class UserPwdServiceImpl extends ServiceImpl<UserPwdMapper, UserPwd> implements UserPwdService {

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private UserService userService;

    @Override
    public ResponseResult login(String openid, String user_address) {
        LambdaQueryWrapper<User> userLambdaQueryWrapper = new LambdaQueryWrapper<>();
        userLambdaQueryWrapper.eq(User::getId, openid);
        if (Objects.isNull(userService.getOne(userLambdaQueryWrapper))) {
            User user = new User();
            user.setId(openid);
            user.setAddress(user_address);
            userMapper.insert(user);
            return ResponseResult.okResult(openid);
        } else {
            return ResponseResult.okResult(userService.getOne(userLambdaQueryWrapper));
        }
    }
}