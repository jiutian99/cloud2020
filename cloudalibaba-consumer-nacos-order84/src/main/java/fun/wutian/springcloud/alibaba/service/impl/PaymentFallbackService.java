package fun.wutian.springcloud.alibaba.service.impl;

import fun.wutian.springcloud.alibaba.service.PaymentService;
import fun.wutian.springcloud.entities.CommonResult;
import fun.wutian.springcloud.entities.Payment;
import org.springframework.stereotype.Service;

/**
 * @author wuyuetian
 * @create 2020-07-31 10:22
 */
@Service
public class PaymentFallbackService implements PaymentService {
    @Override
    public CommonResult<Payment> paymentSQL(Long id) {
        return new CommonResult<>(444, "服务降级返回， ---PaymentFallbackService", new Payment(id, "errorSerial"));
    }
}
