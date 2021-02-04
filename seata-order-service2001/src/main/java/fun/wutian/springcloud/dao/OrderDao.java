package fun.wutian.springcloud.dao;

import fun.wutian.springcloud.domain.Order;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;


@Mapper
public interface OrderDao {

    /**
     * 创建初始订单
     * @param order
     */
    void create(Order order);

    /**
     * 修改订单状态
     * @param id
     * @param status
     */
    void update(@Param("id") Long id, @Param("status") Integer status);
}
