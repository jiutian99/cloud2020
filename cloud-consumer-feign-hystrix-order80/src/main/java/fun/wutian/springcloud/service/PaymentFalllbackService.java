package fun.wutian.springcloud.service;

import org.springframework.stereotype.Component;

/**
 * @author wuyuetian
 * @create 2020-07-18 9:21
 */
@Component
public class PaymentFalllbackService implements PaymentHystrixService {
    @Override
    public String paymentInfo_OK(Integer id) {
        return "=========PaymentFallbackService fall back paymentInfo_OK , o(╥﹏╥)o";
    }

    @Override
    public String paymentInfo_TimeOut(Integer id) {
        return "=========PaymentFallbackService fall back paymentInfo_TimeOut , o(╥﹏╥)o";
    }
}
