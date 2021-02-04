package fun.wutian.springcloud.service.impl;

import fun.wutian.springcloud.dao.AccountDao;
import fun.wutian.springcloud.service.AccountService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.util.concurrent.TimeUnit;

/**
 * @author wuyuetian
 * @create 2020-08-04 14:24
 */
@Service
@Slf4j
public class AccountServiceImpl implements AccountService {

    @Resource
    private AccountDao accountDao;

    @Override
    public void decrease(Long userId, BigDecimal money) {
        log.info("------->account-service 中扣减账户余额开始");
//        模拟超时异常，全局事务回滚
        try{
            TimeUnit.SECONDS.sleep(20);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        accountDao.decrease(userId, money);
        log.info("------->account-service 中扣减账户余额结束");
    }
}
