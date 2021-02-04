package fun.wutian.springcloud.domain;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * @author wuyuetian
 * @create 2020-08-04 11:24
 */
@Getter
@Setter
@NoArgsConstructor
public class Storage {
    private Long id;
    //    产品id
    private Long productId;
    //    总数
    private Integer total;
    //    已用
    private Integer used;
    //    剩余
    private Integer residue;
}
