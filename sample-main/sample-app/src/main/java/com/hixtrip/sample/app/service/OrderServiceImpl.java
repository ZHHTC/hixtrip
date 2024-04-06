package com.hixtrip.sample.app.service;

import com.hixtrip.sample.app.api.OrderService;
import com.hixtrip.sample.app.convertor.OrderConvertor;
import com.hixtrip.sample.client.order.dto.CommandOderCreateDTO;
import com.hixtrip.sample.client.order.dto.CommandPayDTO;
import com.hixtrip.sample.domain.order.OrderDomainService;
import com.hixtrip.sample.domain.order.model.Order;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

/**
 * app层负责处理request请求，调用领域服务
 */
@Component
public class OrderServiceImpl implements OrderService {


    @Autowired
    private OrderDomainService orderDomainService;

    @Autowired
    private RedisTemplate redisTemplate;

    /**
     * 处理请求信息，调用领域层服务
     * @param commandOderCreateDTO 请求参数
     * @return order
     */
    @Override
    public Order createOeder(CommandOderCreateDTO commandOderCreateDTO) {
        return orderDomainService.createOrder(commandOderCreateDTO);
    }

    /**
     * 支付回调
     * @param commandPayDTO 请求参数
     * @return order
     */
    @Override
    public String payCallBack(CommandPayDTO commandPayDTO) {
        int flag;
        if("1".equals(commandPayDTO.getPayStatus())){
            //订单支付成功
            flag = orderDomainService.orderPaySuccess(commandPayDTO);
        }else{
            //支付失败
            flag = orderDomainService.orderPayFail(commandPayDTO);
        }
        return flag > 0 ? "支付成功":"支付失败";
    }

}
