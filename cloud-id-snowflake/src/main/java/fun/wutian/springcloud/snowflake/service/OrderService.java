package fun.wutian.springcloud.snowflake.service;

import fun.wutian.springcloud.snowflake.util.IdGeneratorSnowflake;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @author wuyuetian
 * @create 2020-08-05 11:42
 */
@Service
@Slf4j
public class OrderService {
    @Resource
    private IdGeneratorSnowflake snowflake;

    public String getIDBySnowflake(){
        ExecutorService threadPool = Executors.newFixedThreadPool(5);

        for (int i = 0; i < 20; i++) {
            threadPool.submit(()->{
                System.out.println(snowflake.snowflakeId());
            });
        }

        threadPool.shutdown();

        return "hello snowflake";
    }
}
