package com.hixtrip.sample.app.api;

import com.hixtrip.sample.client.order.dto.CommandOderCreateDTO;
import com.hixtrip.sample.client.order.dto.CommandPayDTO;
import com.hixtrip.sample.domain.order.model.Order;

/**
 * 订单的service层
 */
public interface OrderService {

    /**
     * 处理请求信息，调用领域层服务
     * @param dto 请求参数
     * @return 返回结果
     */
    public Order createOeder(CommandOderCreateDTO dto);

    /**
     * 支付回调
     * @param dto 请求参数
     * @return 返回结果
     */
    public String payCallBack(CommandPayDTO dto);
}
