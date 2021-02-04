package fun.wutian.springcloud.service;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Component
//没有进行默认fallback的方法（方法上没有@HystrixCommand），可以再子类中进行重写，
//如果存在@HystrixCommand注解的方法，则优先使用默认fallback方法，所以即使重写了也没有用
@FeignClient(value = "CLOUD-PROVIDER-HYSTRIX-PAYMENT", fallback = PaymentFalllbackService.class) //80客户端检查服务端异常/错误
public interface PaymentHystrixService {

    @GetMapping("/payment/info_ok/{id}")
    String paymentInfo_OK(@PathVariable("id") Integer id);

    @GetMapping("/payment/info_timeout/{id}")
    String paymentInfo_TimeOut(@PathVariable("id") Integer id);
}
