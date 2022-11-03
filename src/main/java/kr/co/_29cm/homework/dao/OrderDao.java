package kr.co._29cm.homework.dao;

import java.util.HashMap;
import java.util.Map;
import kr.co._29cm.homework.domain.Order;

public class OrderDao {

    private static Map<Long, Order> IN_MEMORY_ORDER = new HashMap<>();
    private static Long id = 1L;

    public Long save(Order order) {
        Order newOrder = new Order(id++, order.getTotalPrice(), order.getDeliveryFare(), order.getOrderTime());
        IN_MEMORY_ORDER.put(newOrder.getId(), newOrder);
        return newOrder.getId();
    }
}
