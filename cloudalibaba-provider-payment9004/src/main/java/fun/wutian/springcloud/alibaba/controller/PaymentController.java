package fun.wutian.springcloud.alibaba.controller;

import fun.wutian.springcloud.entities.CommonResult;
import fun.wutian.springcloud.entities.Payment;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

/**
 * @author wuyuetian
 * @create 2020-07-30 16:31
 */
@RestController
@Slf4j
public class PaymentController {
    @Value("${server.port}")
    private String serverPort;

    private static Map<Long, Payment> hashMap = new HashMap<>();

    static {
        hashMap.put(1L, new Payment(1L, "12789sdsjdoiwejpsjdp"));
        hashMap.put(2L, new Payment(2L, "sdis4w6e4w846sd5s456d"));
        hashMap.put(3L, new Payment(3L, "s4d6s46d8sw8e9s4d56s41s56"));
    }

    @GetMapping("/paymentSQL/{id}")
    public CommonResult<Payment> paymentSQL(@PathVariable("id") Long id){
        Payment payment = hashMap.get(id);
        return new CommonResult(200, "from mysql, server port : " + serverPort, payment);
    }
}
