package com.mhsj.demo.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.mhsj.demo.mapper.UserMapper;
import com.mhsj.demo.pojo.User;
import com.mhsj.demo.service.UserService;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {
}
