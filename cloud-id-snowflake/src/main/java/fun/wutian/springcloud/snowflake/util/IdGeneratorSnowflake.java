package fun.wutian.springcloud.snowflake.util;

import cn.hutool.core.lang.Snowflake;
import cn.hutool.core.net.NetUtil;
import cn.hutool.core.util.IdUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

/**
 * @author wuyuetian
 * @create 2020-08-05 11:26
 */
@Slf4j
@Component
public class IdGeneratorSnowflake {
    private long workId = 0;
    private long dataCenterId = 1;
    private Snowflake snowflake = IdUtil.createSnowflake(workId, dataCenterId);

    @PostConstruct //作用，此类加载入容器中时，可以初始化执行
    public void init(){
        try {
            workId = NetUtil.ipv4ToLong(NetUtil.getLocalhostStr());
            log.info("当期机器的workId：{}", workId);
        } catch (Exception e) {
            e.printStackTrace();
            log.info("当期机器的workId获取失败", e);
            workId = NetUtil.getLocalhostStr().hashCode();
        }
    }

    public synchronized long snowflakeId(){
        return snowflake.nextId();
    }

    public synchronized long snowflakeId(long workId, long dataCenterId) {
        Snowflake snowflake = IdUtil.createSnowflake(workId, dataCenterId);
        return snowflake.nextId();
    }
}
