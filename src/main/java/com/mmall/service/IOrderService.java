package com.mmall.service;

import com.mmall.common.ServerResponse;

import java.util.Map;

/**
 * @author Created by bameirilyo
 * @date 18-5-17 下午1:28
 */
public interface IOrderService {

    ServerResponse pay(Long orserNo, Integer userId, String path);

    ServerResponse aliCallback(Map<String,String> params);

    ServerResponse querryOrderPayStatus(Integer userId,Long orderNo);
}
