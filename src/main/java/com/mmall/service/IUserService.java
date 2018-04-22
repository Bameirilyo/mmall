package com.mmall.service;


import com.mmall.common.ServerResponse;
import com.mmall.pojo.User;

/**
 * @author Created by bameirilyo
 * @date 18-4-21 下午9:46
 */
public interface IUserService {
    ServerResponse<User> login(String username, String password);
}
