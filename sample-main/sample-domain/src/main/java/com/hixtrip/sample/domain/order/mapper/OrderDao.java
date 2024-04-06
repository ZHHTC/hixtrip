package com.hixtrip.sample.domain.order.mapper;

import com.hixtrip.sample.domain.order.model.Order;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface OrderDao {

    /**
     * 新增订单
     * @param order 订餐数据
     * @return count
     */
     int save(Order order);

    /**
     * 查询订单信息
     * @param id 订单id
     * @return 订单详情
     */
    Order selectById(String id);

    /**
     * 修改订单信息
     * @param order 订单详情
     */
    int updateOrder(Order order);
}
