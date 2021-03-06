package com.mmall.service;

import com.mmall.common.ServerResponse;
import com.mmall.vo.CartVo;

/**
 * @author Created by bameirilyo
 * @date 18-5-14 上午9:34
 */
public interface ICartService {

    ServerResponse<CartVo> add(Integer userId, Integer productId, Integer count);

    ServerResponse<CartVo> update(Integer userId, Integer productId,Integer count);

    ServerResponse<CartVo> deleteProuct(Integer userId, String productIds);

    ServerResponse<CartVo> list(Integer userId);

    ServerResponse<CartVo> selectOrUnselect(Integer userId,Integer productId, Integer checked);

    ServerResponse<Integer> getCartProductCount(Integer userId);
}
