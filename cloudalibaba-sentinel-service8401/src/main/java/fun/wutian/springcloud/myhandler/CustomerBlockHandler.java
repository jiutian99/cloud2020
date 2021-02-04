package fun.wutian.springcloud.myhandler;

import com.alibaba.csp.sentinel.slots.block.BlockException;
import fun.wutian.springcloud.entities.CommonResult;

/**
 * @author wuyuetian
 * @create 2020-07-30 15:36
 */
public class CustomerBlockHandler {
//    必须为public static
    public static CommonResult handlerException(BlockException exception){
        return new CommonResult(444, "按客户自定义，global handlerException--------1");
    }

    public static CommonResult handlerException2(BlockException exception){
        return new CommonResult(444, "按客户自定义，global handlerException--------2");
    }
}
