package fun.wutian.springcloud.service.impl;

import fun.wutian.springcloud.service.IMessageProvider;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.messaging.Source;
import org.springframework.integration.support.MessageBuilder;
import org.springframework.messaging.MessageChannel;

import javax.annotation.Resource;
import java.util.UUID;

/**
 * @author wuyuetian
 * @create 2020-07-21 16:20
 */
@EnableBinding(Source.class) //定义消息的推送管道
@Slf4j
public class MessageProvider implements IMessageProvider {

    @Resource
    private MessageChannel output; //消息发送管道

    @Override
    public String send() {
        String serial = UUID.randomUUID().toString();
        output.send(MessageBuilder.withPayload(serial).build());
        log.info("********serial:\t" + serial);
        return null;
    }
}
