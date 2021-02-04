package fun.wutian.springcloud.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import javax.annotation.Resource;

/**
 * @author wuyuetian
 * @create 2020-07-15 9:04
 */
@RestController
@Slf4j
public class OrderController {

//    private static final String INVOKE_URL = "http://consul-provider-payment"; //consul-provider-payment 由于在远程服务器
    private static final String INVOKE_URL = "http://localhost:8006";

    @Resource
    private RestTemplate restTemplate;

    @GetMapping("/consumer/payment/consul")
    public String paymentInfo(){
        return restTemplate.getForObject(INVOKE_URL + "/payment/consul", String.class);
    }
}
