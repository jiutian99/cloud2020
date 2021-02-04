package fun.wutian.springcloud.controller;

import fun.wutian.springcloud.entities.CommonResult;
import fun.wutian.springcloud.service.StorageService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @author wuyuetian
 * @create 2020-08-04 11:49
 */
@RestController
@Slf4j
public class StorageController {
    @Resource
    private StorageService storageService;

    @PostMapping("/storage/decrease")
    public CommonResult decrease(@RequestParam("productId") Long productId, @RequestParam("count") Integer count) {
        log.info("开始创建订单");
        storageService.decrease(productId, count);
        log.info("创建订单结束");

        return new CommonResult(200, "扣减库存成功");
    }

}
