package com.mmall.service.impl;

import com.google.common.collect.Maps;
import com.mmall.common.ServerResponse;
import com.mmall.dao.ShippingMapper;
import com.mmall.pojo.Shipping;
import com.mmall.service.IShippingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;

/**
 * @author Created by bameirilyo
 * @date 18-5-15 上午10:19
 */
@Service("iShippingService")
public class ShippingServiceImpl implements IShippingService{

    @Autowired
    private ShippingMapper shippingMapper;

    /**
     * 新建地址
     * @param userId
     * @param shipping
     * @return
     */
    public ServerResponse add(Integer userId, Shipping shipping){
        int rowCount = shippingMapper.insert(shipping);
        if (rowCount > 0){
            Map result = Maps.newHashMap();
            result.put("shippingId",shipping.getId());
            return ServerResponse.createBySuccess("新建地址成功",result);
        }
        return ServerResponse.createByErrorMessage("新建地址失败");
    }


    public ServerResponse<String> del(Integer userId, Integer shippingId){
        //注意横向越权，用户登陆后，删除地址时要与userId关联，防止删除其他shippingId地址
        int  resultCount = shippingMapper.deleteByShippingIdUserId(userId,shippingId);
        if (resultCount > 0){
            return ServerResponse.createBySuccess("删除地址成功");
        }
        return ServerResponse.createByErrorMessage("删除地址失败");
    }
}
