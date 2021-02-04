package fun.wutian.springcloud.alibaba.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

/**
 * @author wuyuetian
 * @create 2020-07-15 9:08
 */
@Configuration
public class ApplicationContextConfig {

//    跨域访问对象

//    @LoadBalanced //赋予restTemplate具有负载均衡配置能力
    @Bean
    public RestTemplate getRestTemplate() {
        return new RestTemplate();
    }
}
