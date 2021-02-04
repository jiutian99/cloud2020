package fun.wutian.springcloud.controller;

import fun.wutian.springcloud.domain.Order;
import fun.wutian.springcloud.entities.CommonResult;
import fun.wutian.springcloud.service.OrderService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @author wuyuetian
 * @create 2020-08-03 10:10
 */
@RestController
@Slf4j
public class OrderController {
    @Resource
    private OrderService orderService;

    @GetMapping("/order/create")
    public CommonResult create(Order order){
        orderService.create(order);

        return new CommonResult(200, "订单创建成功");
    }

}
