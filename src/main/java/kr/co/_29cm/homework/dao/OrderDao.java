package kr.co._29cm.homework.dao;

import kr.co._29cm.homework.domain.Order;

public interface OrderDao {

    Long save(Order order);

    Order findById(Long orderId);
}
