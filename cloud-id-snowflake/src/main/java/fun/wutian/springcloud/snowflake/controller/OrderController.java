package fun.wutian.springcloud.snowflake.controller;

import fun.wutian.springcloud.snowflake.service.OrderService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @author wuyuetian
 * @create 2020-08-05 11:47
 */
@RestController
@Slf4j
public class OrderController {
    @Resource
    private OrderService orderService;

    @GetMapping("/snowflake")
    public String getIDBySnowflake(){
        return orderService.getIDBySnowflake();
    }
}
