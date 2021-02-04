package fun.wutian.springcloud.domain;

import java.math.BigDecimal;

/**
 * @author wuyuetian
 * @create 2020-08-04 14:10
 */
public class Account {
    private Long id;
    /**
     * 用户id
     */
    private Long userId;
    /**
     * 总金额
     */
    private BigDecimal total;
    /**
     * 已用金额
     */
    private BigDecimal used;
    /**
     * 剩余金额
     */
    private BigDecimal residue;
}
