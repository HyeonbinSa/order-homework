package kr.co._29cm.homework.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import kr.co._29cm.homework.dao.OrderDao;
import kr.co._29cm.homework.dao.ProductDao;
import kr.co._29cm.homework.domain.Order;
import kr.co._29cm.homework.domain.OrderProduct;
import kr.co._29cm.homework.domain.Product;

public class OrderService {

    private final ProductDao productDao;
    private final OrderDao orderDao;

    public OrderService(ProductDao productDao, OrderDao orderDao) {
        this.productDao = productDao;
        this.orderDao = orderDao;
    }

    public void order(Map<Long, Integer> orderRequests) {
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
    }
}
