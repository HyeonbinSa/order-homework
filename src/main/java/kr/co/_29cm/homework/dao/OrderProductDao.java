package kr.co._29cm.homework.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import kr.co._29cm.homework.domain.OrderProduct;

public class OrderProductDao {

    private static final Map<Long, OrderProduct> IN_MEMORY_ORDER_PRODUCT = new HashMap<>();
    private static Long id = 1L;

    public Long save(OrderProduct orderProduct) {
        OrderProduct newOrderProduct = new OrderProduct(id++, orderProduct.getOrderId(), orderProduct.getProductId(),
                orderProduct.getQuantity());
        IN_MEMORY_ORDER_PRODUCT.put(newOrderProduct.getId(), newOrderProduct);
        return newOrderProduct.getId();
    }

    public List<OrderProduct> findByOrderId(Long orderId) {
        return IN_MEMORY_ORDER_PRODUCT.values().stream()
                .filter(orderProduct -> orderProduct.getOrderId().equals(orderId))
                .collect(Collectors.toList());
    }
}
