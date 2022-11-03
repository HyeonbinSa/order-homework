package kr.co._29cm.homework.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import kr.co._29cm.homework.dao.InMemoryOrderDao;
import kr.co._29cm.homework.dao.InMemoryOrderProductDao;
import kr.co._29cm.homework.dao.InMemoryProductDao;
import kr.co._29cm.homework.dao.OrderDao;
import kr.co._29cm.homework.dao.OrderProductDao;
import kr.co._29cm.homework.dao.ProductDao;
import kr.co._29cm.homework.domain.Order;
import kr.co._29cm.homework.domain.OrderProduct;
import kr.co._29cm.homework.domain.Product;
import kr.co._29cm.homework.dto.OrderProductResponse;
import kr.co._29cm.homework.dto.OrderResponse;

public class OrderService {

    private final ProductDao productDao;
    private final OrderDao orderDao;
    private final OrderProductDao orderProductDao;

    public OrderService() {
        this.productDao = new InMemoryProductDao();
        this.orderDao = new InMemoryOrderDao();
        this.orderProductDao = new InMemoryOrderProductDao();
    }

    public Long create(Map<Long, Integer> orderRequests) {
        int sum = 0;
        List<OrderProduct> orderProducts = new ArrayList<>();
        for (Long productId : orderRequests.keySet()) {
            // TODO: 존재하지 않는 상품일 경우 예외 발생
            Product product = productDao.findById(productId);
            int quantity = orderRequests.get(productId);
            orderProducts.add(new OrderProduct(productId, quantity));
            sum += product.getPrice() * quantity;
        }
        int deliveryFare = 0;
        if (sum < 50000) {
            deliveryFare = 2500;
        }
        Order order = new Order(sum, deliveryFare);
        Long orderId = orderDao.save(order);
        for (OrderProduct orderProduct : orderProducts) {
            OrderProduct newOrderProduct = new OrderProduct(orderId, orderProduct.getProductId(),
                    orderProduct.getQuantity());
            orderProductDao.save(newOrderProduct);
        }
        return orderId;
    }

    public OrderResponse find(Long orderId) {
        Order order = orderDao.findById(orderId);
        List<OrderProduct> orderProducts = orderProductDao.findByOrderId(orderId);
        List<OrderProductResponse> orderProductResponses = orderProducts.stream()
                .map(orderProduct -> new OrderProductResponse(
                        productDao.findById(orderProduct.getProductId()).getName(),
                        orderProduct.getQuantity()))
                .collect(Collectors.toList());
        return OrderResponse.of(order, orderProductResponses);
    }
}
