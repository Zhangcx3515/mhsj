package com.mhsj.demo.service;
import com.baomidou.mybatisplus.extension.service.IService;
import com.mhsj.demo.pojo.UserPwd;
import com.mhsj.demo.pojo.entity.ResponseResult;



/**
 * (User)表服务接口
 *
 * @author makejava
 * @since 2022-08-10 21:06:49
 */
public interface UserPwdService extends IService<UserPwd> {

    ResponseResult login(String openid, String user_address);
}