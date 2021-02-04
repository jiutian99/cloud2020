package fun.wutian.springcloud.config;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Configuration;

/**
 * @author wuyuetian
 * @create 2020-08-03 10:41
 */
@Configuration
@MapperScan({"fun.wutian.springcloud.dao"})
public class MybatisConfig {
}
