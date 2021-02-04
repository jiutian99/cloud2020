package fun.wutian.springcloud;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

/**
 * @author wuyuetian
 * @create 2020-08-01 11:15
 */

@EnableFeignClients
@EnableDiscoveryClient
@SpringBootApplication(exclude = DataSourceAutoConfiguration.class) //排除自动配置数据源，引用自定义seata数据源
public class StorageServiceMain2002 {

    public static void main(String[] args) {
        SpringApplication.run(StorageServiceMain2002.class, args);
    }

}
