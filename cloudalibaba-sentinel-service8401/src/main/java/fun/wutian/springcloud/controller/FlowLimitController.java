package fun.wutian.springcloud.controller;

import com.alibaba.csp.sentinel.annotation.SentinelResource;
import com.alibaba.csp.sentinel.slots.block.BlockException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author wuyuetian
 * @create 2020-07-30 9:50
 */
@RestController
@Slf4j
public class FlowLimitController {

    @GetMapping("/testA")
    public String testA() {
//        try {
//            TimeUnit.MILLISECONDS.sleep(00);
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }
        return "-----------testA";
    }


    @GetMapping("/testB")
    public String testB(){
        log.info(Thread.currentThread().getName() + "\t....testB");
        return "-----------testB";
    }

    @GetMapping("/testD")
    public String testD(){
//        tr测试    并发测试中 1秒并发10个请求>官方要求的1秒并发5个请求；处理1个业务超过1秒 > sentinel要求的处理1个业务不可以超过0.2秒， 因此sentinel将服务降级1秒
//        try {
//            TimeUni.MILLISECONDS.sleep(1000);
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }
//        log.info(Thread.currentThread().getName() + "\t....testD");
        log.info("testD 异常比例");
        int n = 1 / 0;
        return "-----------testD";
    }

    @GetMapping("/testHotKey")
//    value: 随意，但要求唯一   注解属性修改需要重启，热部署对注解属性不敏感
    @SentinelResource(value = "testHotKey", blockHandler = "deal_testHotKey")
    public String testHotKey(
            @RequestParam(value = "p1", required = false) String p1,
            @RequestParam(value = "p2", required = false) String p2) {

//        sentinel不对RuntimeException不处理，只对控制面板相关配置处理
//        int n = 1 / 0;
        return "......testHotKey";
    }
//     降级服务，声明为public由降级调用
    public String deal_testHotKey(String p1, String p2, BlockException exception) {
//        sentinel系统默认提示：Blocked by Sentinel (flow limiting)
        return "........deal_testHotKey o(╥﹏╥)o";
    }

}
