package com.mmall.service;

import com.mmall.common.ServerResponse;
import com.mmall.vo.CartVo;

/**
 * @author Created by bameirilyo
 * @date 18-5-14 上午9:34
 */
public interface ICartService {
    ServerResponse<CartVo> add(Integer userId, Integer productId, Integer count);
}
