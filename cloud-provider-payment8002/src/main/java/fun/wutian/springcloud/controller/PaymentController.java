package fun.wutian.springcloud.controller;

import fun.wutian.springcloud.entities.CommonResult;
import fun.wutian.springcloud.entities.Payment;
import fun.wutian.springcloud.server.PaymentService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * @author wuyuetian
 * @create 2020-07-14 11:12
 */
@Slf4j
@RestController
public class PaymentController {
    @Resource
    private PaymentService paymentService;

    @Value("${server.port}")
    private String serverPort;

    @PostMapping("/payment/create")
    public CommonResult create(@RequestBody Payment payment){
        int result = paymentService.create(payment);
        log.info("***********插入结果：" + result);
        if(result > 0){
            return new CommonResult(200, "插入数据成功，serverPort : " + serverPort, result);
        } else {
            return new CommonResult(444, "插入数据库失败，serverPort : " + serverPort, null);
        }
    }

    @GetMapping("/payment/get/{id}")
    public CommonResult<Payment> getPaymentById(@PathVariable("id") Long id){
        Payment payment = paymentService.getPaymentById(id);
        log.info("***********查询结果：" + payment);
        if(payment != null){
            return new CommonResult<>(200, "查询成功,serverPort:" + serverPort, payment);
        } else {
            return new CommonResult<>(444, "没有 ID:" + id + " 对应记录,serverPort:" + serverPort, null);
        }
    }

    @GetMapping("/payment/lb")
    public String getPaymentLB(){
        return serverPort;
    }
}
