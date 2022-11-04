package kr.co._29cm.homework.service;

import java.util.List;
import java.util.stream.Collectors;
import kr.co._29cm.homework.dao.CartItemDao;
import kr.co._29cm.homework.dao.InMemoryCartItemDao;
import kr.co._29cm.homework.dao.InMemoryOrderDao;
import kr.co._29cm.homework.dao.InMemoryOrderProductDao;
import kr.co._29cm.homework.dao.InMemoryProductDao;
import kr.co._29cm.homework.dao.OrderDao;
import kr.co._29cm.homework.dao.OrderProductDao;
import kr.co._29cm.homework.dao.ProductDao;
import kr.co._29cm.homework.domain.CartItem;
import kr.co._29cm.homework.domain.CartItems;
import kr.co._29cm.homework.domain.Order;
import kr.co._29cm.homework.domain.OrderProduct;
import kr.co._29cm.homework.domain.Product;
import kr.co._29cm.homework.dto.OrderProductResponse;
import kr.co._29cm.homework.dto.OrderResponse;

public class OrderService {

    private final ProductDao productDao;
    private final OrderDao orderDao;
    private final OrderProductDao orderProductDao;
    private final CartItemDao cartItemDao;

    public OrderService() {
        this.productDao = new InMemoryProductDao();
        this.orderDao = new InMemoryOrderDao();
        this.orderProductDao = new InMemoryOrderProductDao();
        this.cartItemDao = new InMemoryCartItemDao();
    }

    public Long create(Long cartId) {
        CartItems cartItems = cartItemDao.findByCartId(cartId);
        validateProductStock(cartItems);
        int totalPrice = cartItems.calculateTotalPrice();
        int deliveryFare = calculateDeliveryFare(totalPrice);
        Order order = new Order(totalPrice, deliveryFare);
        Long orderId = orderDao.save(order);
        for (CartItem cartItem : cartItems.getValue()) {
            OrderProduct newOrderProduct = new OrderProduct(orderId, cartItem.getProductId(), cartItem.getQuantity());
            orderProductDao.save(newOrderProduct);
        }
        return orderId;
    }

    private int calculateDeliveryFare(int totalPrice) {
        if (totalPrice < 50000) {
            return 2500;
        }
        return 0;
    }

    private void validateProductStock(CartItems cartItems) {
        for (CartItem cartItem : cartItems.getValue()) {
            Product product = productDao.findById(cartItem.getProductId());
            if (product.getStock() < cartItem.getQuantity()) {
                throw new IllegalArgumentException("개수 초과");
            }
        }
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
