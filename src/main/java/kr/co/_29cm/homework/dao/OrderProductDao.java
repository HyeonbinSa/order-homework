package kr.co._29cm.homework.dao;

import java.util.List;
import kr.co._29cm.homework.domain.OrderProduct;

public interface OrderProductDao {

    Long save(OrderProduct orderProduct);

    List<OrderProduct> findByOrderId(Long orderId);
}
