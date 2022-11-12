package kr.co._29cm.homework.dao;

import java.util.Optional;
import kr.co._29cm.homework.domain.Order;

public interface OrderDao {

    Long save(Order order);

    Optional<Order> findById(Long orderId);
}
