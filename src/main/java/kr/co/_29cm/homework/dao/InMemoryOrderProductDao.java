package kr.co._29cm.homework.dao;

import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicLong;
import java.util.stream.Collectors;
import kr.co._29cm.homework.domain.OrderProduct;

public class InMemoryOrderProductDao implements OrderProductDao {

    private static final Map<Long, OrderProduct> IN_MEMORY_ORDER_PRODUCT = new ConcurrentHashMap<>();
    private static final AtomicLong ID = new AtomicLong(1);

    @Override
    public Long save(OrderProduct orderProduct) {
        OrderProduct newOrderProduct = new OrderProduct(ID.getAndIncrement(),
                orderProduct.getOrderId(),
                orderProduct.getProductId(),
                orderProduct.getQuantity());
        IN_MEMORY_ORDER_PRODUCT.put(newOrderProduct.getId(), newOrderProduct);
        return newOrderProduct.getId();
    }

    @Override
    public List<OrderProduct> findByOrderId(Long orderId) {
        return IN_MEMORY_ORDER_PRODUCT.values().stream()
                .filter(orderProduct -> orderProduct.getOrderId().equals(orderId))
                .collect(Collectors.toList());
    }
}
