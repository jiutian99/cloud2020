package fun.wutian.springcloud.alibaba;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

/**
 * @author wuyuetian
 * @create 2020-07-30 16:29
 */
@SpringBootApplication
@EnableDiscoveryClient  //服务注册nacos
@EnableFeignClients     //维持 controller调用service 层
public class OrderMain84 {

    public static void main(String[] args) {
        SpringApplication.run(OrderMain84.class, args);
    }

}
