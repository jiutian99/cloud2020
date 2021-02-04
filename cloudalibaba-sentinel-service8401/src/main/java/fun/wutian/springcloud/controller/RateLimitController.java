package fun.wutian.springcloud.controller;

import com.alibaba.csp.sentinel.annotation.SentinelResource;
import com.alibaba.csp.sentinel.slots.block.BlockException;
import fun.wutian.springcloud.entities.CommonResult;
import fun.wutian.springcloud.entities.Payment;
import fun.wutian.springcloud.myhandler.CustomerBlockHandler;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author wuyuetian
 * @create 2020-07-30 15:10
 */
@RestController
@Slf4j
public class RateLimitController {

    @GetMapping("/byResouce")
    @SentinelResource(value = "byResouce", blockHandler = "handleException")
    public CommonResult byResouce() {
        return new CommonResult(200, "按资源名称限流测试ok", new Payment(2020L, "serial001"));
    }
    public CommonResult handleException(BlockException exception) {
        return new CommonResult(444, exception.getClass().getCanonicalName()+"\t服务不可用");
    }

//    自定义限流处理逻辑： 为资源名称(byUrl) 而非url(/rateLimit/byUrl)
    @GetMapping("/rateLimit/byUrl")
    @SentinelResource(value = "byUrl", blockHandlerClass = CustomerBlockHandler.class, blockHandler = "handlerException")
    public CommonResult byUrl() {
        return new CommonResult(200, "按url限流测试ok", new Payment(2020L, "serial002"));
    }

    @GetMapping("/rateLimit/customerBlockHandler")
    @SentinelResource(value = "customerBlockHandler", blockHandlerClass = CustomerBlockHandler.class, blockHandler = "handlerException2")
    public CommonResult customerBlockHandler() {
        return new CommonResult(200, "按客户自定义限流测试ok", new Payment(2020L, "serial003"));
    }
}
