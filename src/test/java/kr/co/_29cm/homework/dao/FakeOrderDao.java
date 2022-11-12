package kr.co._29cm.homework.dao;

import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicLong;
import kr.co._29cm.homework.domain.Order;

public class FakeOrderDao implements OrderDao {

    private final Map<Long, Order> IN_MEMORY_ORDER;
    private final AtomicLong ID;

    public FakeOrderDao() {
        IN_MEMORY_ORDER = new ConcurrentHashMap<>();
        ID = new AtomicLong(1);
    }

    @Override
    public Long save(Order order) {
        Order newOrder = new Order(ID.getAndIncrement(),
                order.getTotalPrice(),
                order.getDeliveryFare(),
                order.getOrderTime());
        IN_MEMORY_ORDER.put(newOrder.getId(), newOrder);
        return newOrder.getId();
    }

    @Override
    public Optional<Order> findById(Long orderId) {
        return Optional.ofNullable(IN_MEMORY_ORDER.get(orderId));
    }
}
