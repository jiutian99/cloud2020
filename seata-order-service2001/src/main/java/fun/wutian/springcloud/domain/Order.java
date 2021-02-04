package fun.wutian.springcloud.domain;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

/**
 * @author wuyuetian
 * @create 2020-08-01 11:16
 */
@Getter
@Setter
@NoArgsConstructor
public class Order {
    private Long id;
    private Long userId;
    private Long productId;
    private Integer count;
    private BigDecimal money;
    /**
     * 订单状态：0：创建中；1：已创建
     */
    private Integer status;
}
