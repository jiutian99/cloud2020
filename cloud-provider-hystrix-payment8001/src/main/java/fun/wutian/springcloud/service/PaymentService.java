package fun.wutian.springcloud.service;

import cn.hutool.core.util.IdUtil;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.concurrent.TimeUnit;

/**
 * @author wuyuetian
 * @create 2020-07-17 14:41
 */
@Service
public class PaymentService {

//    正常 ok
    public String paymentInfo_OK(Integer id){
        return "线程池:" + Thread.currentThread().getName() + "\tpaymentInfo_OK , id :\t" + id + "\tO(∩_∩)O哈哈~";
    }

    @HystrixCommand(fallbackMethod = "paymentInfo_TimeOutHandler", commandProperties = {
            @HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds", value = "5000")
    })
    public String paymentInfo_TimeOut(Integer id){
//        超时测试
        int timeNumber = 3;
        try {
            TimeUnit.SECONDS.sleep(timeNumber);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

//        异常测试
//        int n = 10 / 0;

        return "线程池:" + Thread.currentThread().getName() + "\tpaymentInfo_TimeOut , id :\t" + id + "\tO(∩_∩)O哈哈~\t耗时" + timeNumber + "秒";
    }
    public String paymentInfo_TimeOutHandler(Integer id){
        return "服务繁忙中\t线程池:" + Thread.currentThread().getName() + "\tpaymentInfo_TimeOutHandler , id :\t" + id + "\to(╥﹏╥)o";
    }

//    ============服务熔断
    @HystrixCommand(fallbackMethod = "paymentCircuitBreak_fallback", commandProperties = {
            @HystrixProperty(name = "circuitBreaker.enabled", value = "true"), //是否开启断路器
//            10秒中的窗口期中10次中有6次请求失败，则进行跳闸
            @HystrixProperty(name = "circuitBreaker.requestVolumeThreshold", value = "10"), //请求次数
            @HystrixProperty(name = "circuitBreaker.sleepWindowInMilliseconds", value = "10000"), //时间窗口期
            @HystrixProperty(name = "circuitBreaker.errorThresholdPercentage", value = "60") //失败率达到多少后跳闸 百分比
    })
    public String paymentCircuitBreaker(@PathVariable("id") Integer id){
        if (id < 0){
            throw new RuntimeException("****id 不能为负数");
        }
        String serialNumber = IdUtil.simpleUUID();

        return Thread.currentThread().getName() + "\t调用成功，流水号\t" + serialNumber;
    }
    public String paymentCircuitBreak_fallback(@PathVariable("id") Integer id){
        return "id 不能为负数， 请稍后重试，o(╥﹏╥)o， id ： " + id;
    }

}
