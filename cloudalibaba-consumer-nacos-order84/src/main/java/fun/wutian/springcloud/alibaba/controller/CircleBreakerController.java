package fun.wutian.springcloud.alibaba.controller;

import com.alibaba.csp.sentinel.annotation.SentinelResource;
import com.alibaba.csp.sentinel.slots.block.BlockException;
import fun.wutian.springcloud.alibaba.service.PaymentService;
import fun.wutian.springcloud.entities.CommonResult;
import fun.wutian.springcloud.entities.Payment;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import javax.annotation.Resource;

/**
 * @author wuyuetian
 * @create 2020-07-31 9:09
 */
@RestController
@Slf4j
public class CircleBreakerController {
    @Value("${service-url.nacos-user-service}")
    private String serverUrl;

    @Resource
    private RestTemplate restTemplate;

    @GetMapping("/consumer/fallback/{id}")
//    @SentinelResource(value = "fallback") //没有配置
    //fallback只负责业务异常
//    @SentinelResource(value = "fallback", fallback = "handlerFallback")
//    @SentinelResource(value = "fallback", blockHandler = "blockHandler") //blockHandler只负责sentinel控制台配置违规
    //blockHandler只负责sentinel控制台配置违规
//    @SentinelResource(value = "fallback", blockHandler = "blockHandler", fallback = "handlerFallback")
    //如果fallback、blockHandler都进行了配置，则被限流降级而抛出BlockException时只会进入 blockException处理逻辑
    @SentinelResource(value = "fallback", blockHandler = "blockHandler", fallback = "handlerFallback",
    exceptionsToIgnore = {IllegalAccessException.class})
    public CommonResult<Payment> fallback(@PathVariable("id") Long id) throws IllegalAccessException {
        CommonResult result = restTemplate.getForObject(serverUrl + "/paymentSQL/" + id, CommonResult.class);

        if(id == 4) {
            throw new IllegalAccessException("IllegalAccessException，非法参数异常");
        } else if(result.getData() == null) {
            throw new NullPointerException("NullPointerException， 该id没有对应记录，空指针异常");
        }

        return result;
    }
    public CommonResult handlerFallback(@PathVariable("id") Long id, Throwable e){
        Payment payment = new Payment(id, "null");
        return new CommonResult(444, "兜底异常处理handlerFallback， Exception内容：" + e.getMessage(), payment);
    }

    public CommonResult blockHandler(@PathVariable("id") Long id, BlockException blockException){
        Payment payment = new Payment(id, "null");
        return new CommonResult(445, "blockHandler-sentinel限流， 无此流水：" + blockException.getMessage(), payment);
    }

    //    =========================================openfeign==============================
    @Resource
    private PaymentService paymentService;

    @GetMapping("/consumer/paymentSQL/{id}")
    public CommonResult<Payment> paymentSQL(@PathVariable("id") Long id){
        return paymentService.paymentSQL(id);
    }
}
