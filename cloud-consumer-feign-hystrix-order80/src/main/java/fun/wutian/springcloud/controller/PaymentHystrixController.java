package fun.wutian.springcloud.controller;

import com.netflix.hystrix.contrib.javanica.annotation.DefaultProperties;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import fun.wutian.springcloud.service.PaymentHystrixService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @author wuyuetian
 * @create 2020-07-17 16:06
 */
@RestController
@Slf4j
//可以处理 Whitelabel Error Page
@DefaultProperties(defaultFallback = "payment_Global_FallbackMethod") //80客户端服务异常/错误
public class PaymentHystrixController {
    @Resource
    private PaymentHystrixService hystrixService;

    @GetMapping("/consumer/payment/info_ok/{id}")
//    @HystrixCommand
    public String paymentInfo_OK(@PathVariable("id") Integer id){
        return hystrixService.paymentInfo_OK(id);
    }

    @GetMapping("/consumer/payment/info_timeout/{id}")
//    @HystrixCommand(fallbackMethod = "paymentTimeOutFallBackMethod", commandProperties = {
//            @HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds", value = "1500")
//    }) //定制
//    @HystrixCommand(commandProperties = {
////            设置客户端接收服务端超时时长，如果不进行设置，则服务端执行程序超过3秒，客户端将自己进行降级服务
//            @HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds", value = "6000")
//    })
    @HystrixCommand
    public String paymentInfo_TimeOut(@PathVariable("id") Integer id) {
//        异常测试
//        int a = 10 / 0;

        return hystrixService.paymentInfo_TimeOut(id);
    }

//    定制fallback方法
    public String paymentTimeOutFallBackMethod(@PathVariable("id") Integer id){
        return "我是消费者80，对方支付系统繁忙请10秒后再试，或者运行出错请检查自己,o(╥﹏╥)o";
    }

//    全局fallback方法
    public String payment_Global_FallbackMethod(){
        return "Global异常处理信息，请稍后再试！o(╥﹏╥)o";
    }

}
