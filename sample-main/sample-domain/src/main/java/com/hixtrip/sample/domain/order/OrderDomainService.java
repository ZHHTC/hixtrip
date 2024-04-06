package com.hixtrip.sample.domain.order;

import com.hixtrip.sample.app.convertor.OrderConvertor;
import com.hixtrip.sample.client.order.dto.CommandOderCreateDTO;
import com.hixtrip.sample.client.order.dto.CommandPayDTO;
import com.hixtrip.sample.domain.commodity.CommodityDomainService;
import com.hixtrip.sample.domain.order.mapper.OrderDao;
import com.hixtrip.sample.domain.order.model.Order;
import com.hixtrip.sample.domain.pay.model.CommandPay;
import com.sun.org.apache.xerces.internal.impl.dtd.models.CMAny;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.time.LocalDateTime;

/**
 * 订单领域服务
 * todo 只需要实现创建订单即可
 */
@Component
public class OrderDomainService {


    @Autowired
    private CommodityDomainService commodityDomainService;

    @Autowired
    private RedisTemplate redisTemplate;

    @Autowired
    private OrderConvertor convertor;

    @Autowired
    private OrderDao orderDao;
    /**
     * todo 需要实现
     * 创建待付款订单
     */
    public Order createOrder(CommandOderCreateDTO commandOderCreateDTO) {
        Order order = convertor.dtoToOrder(commandOderCreateDTO);
        //查询价格
        BigDecimal skuPrice = commodityDomainService.getSkuPrice(order.getSkuId());
        //创建订单
        order.setId(order.getUserId()+ LocalDateTime.now());
        order.setMoney(skuPrice.multiply (new BigDecimal(order.getAmount())));
        order.setDelFlag(0L);
        order.setCreateBy(order.getUserId());
        order.setCreateTime(LocalDateTime.now());
        orderDao.save(order);
        //扣减库存
        redisTemplate.opsForValue().set("orderCache","库存-锁定库存");
        return order;
    }

    /**
     * todo 需要实现
     * 待付款订单支付成功
     */
    public int orderPaySuccess(CommandPayDTO commandPayDTO) {
        //查询订单信息
        Order order = orderDao.selectById(commandPayDTO.getOrderId());
        order.setPayStatus(commandPayDTO.getPayStatus());
        order.setPayTime(LocalDateTime.now());
        return orderDao.updateOrder(order);
    }

    /**
     * todo 需要实现
     * 待付款订单支付失败
     */
    public int orderPayFail(CommandPayDTO commandPayDTO) {
        //需要你在infra实现, 自行定义出入参
        Order order = orderDao.selectById(commandPayDTO.getOrderId());
        order.setPayStatus(commandPayDTO.getPayStatus());
        order.setPayTime(LocalDateTime.now());
        orderDao.updateOrder(order);
        return -1;
    }
}
