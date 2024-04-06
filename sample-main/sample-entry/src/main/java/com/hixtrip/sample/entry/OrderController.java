package com.hixtrip.sample.entry;

import com.hixtrip.sample.app.api.OrderService;
import com.hixtrip.sample.client.order.dto.CommandOderCreateDTO;
import com.hixtrip.sample.client.order.dto.CommandPayDTO;
import com.hixtrip.sample.domain.order.model.Order;
import io.micrometer.common.util.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * todo 这是你要实现的
 */
@RestController
public class OrderController {


    @Autowired
    private OrderService orderService;
    /**
     * todo 这是你要实现的接口
     *
     * @param commandOderCreateDTO 入参对象
     * @return 请修改出参对象
     */
    @PostMapping(path = "/command/order/create")
    public Order order(@RequestBody CommandOderCreateDTO commandOderCreateDTO) throws Exception {
        //登录信息可以在这里模拟
        var userId = "123";
        if(StringUtils.isEmpty(commandOderCreateDTO.getUserId())){
            //项目中没有定义异常，就直接抛出去了
            throw new Exception("请登录后重试");
        }else if(!userId.equals(commandOderCreateDTO.getUserId())){
            throw new Exception("下单用户与登录用户不一致");
        }
        //登录校验通过
        return orderService.createOeder(commandOderCreateDTO);
    }

    /**
     * todo 这是模拟创建订单后，支付结果的回调通知
     * 【中、高级要求】需要使用策略模式处理至少三种场景：支付成功、支付失败、重复支付(自行设计回调报文进行重复判定)
     *
     * @param commandPayDTO 入参对象
     * @return 请修改出参对象
     */
    @PostMapping(path = "/command/order/pay/callback")
    public String payCallback(@RequestBody CommandPayDTO commandPayDTO) {
        return orderService.payCallBack(commandPayDTO);
    }

}
