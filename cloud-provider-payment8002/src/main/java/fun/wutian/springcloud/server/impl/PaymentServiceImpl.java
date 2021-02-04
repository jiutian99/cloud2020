package fun.wutian.springcloud.server.impl;

import fun.wutian.springcloud.dao.PaymentDao;
import fun.wutian.springcloud.entities.Payment;
import fun.wutian.springcloud.server.PaymentService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @author wuyuetian
 * @create 2020-07-14 11:09
 */
@Service
public class PaymentServiceImpl implements PaymentService {

    @Resource
    private PaymentDao paymentDao;

    @Override
    public int create(Payment payment) {
        return paymentDao.create(payment);
    }

    @Override
    public Payment getPaymentById(Long id) {
        return paymentDao.getPaymentById(id);
    }
}
