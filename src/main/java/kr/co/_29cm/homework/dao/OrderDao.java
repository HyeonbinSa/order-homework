package kr.co._29cm.homework.dao;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicLong;
import kr.co._29cm.homework.domain.Order;

public class OrderDao {

    private static final Map<Long, Order> IN_MEMORY_ORDER = new ConcurrentHashMap<>();
    private static final AtomicLong ID = new AtomicLong(1);

    public Long save(Order order) {
        Order newOrder = new Order(ID.getAndIncrement(),
                order.getTotalPrice(),
                order.getDeliveryFare(),
                order.getOrderTime());
        IN_MEMORY_ORDER.put(newOrder.getId(), newOrder);
        return newOrder.getId();
    }

    public Order findById(Long orderId) {
        return IN_MEMORY_ORDER.get(orderId);
    }
}
