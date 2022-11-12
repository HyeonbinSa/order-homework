package kr.co._29cm.homework.service;

import java.util.List;
import java.util.stream.Collectors;
import kr.co._29cm.homework.dao.CartDao;
import kr.co._29cm.homework.dao.CartItemDao;
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
    private final CartDao cartDao;

    public OrderService(final ProductDao productDao,
                        final OrderDao orderDao,
                        final OrderProductDao orderProductDao,
                        final CartItemDao cartItemDao,
                        final CartDao cartDao) {
        this.productDao = productDao;
        this.orderDao = orderDao;
        this.orderProductDao = orderProductDao;
        this.cartItemDao = cartItemDao;
        this.cartDao = cartDao;
    }

    public Long create(Long cartId) {
        CartItems cartItems = cartItemDao.findByCartId(cartId);
        validateProductStock(cartItems);
        Order order = Order.from(cartItems.calculateTotalPrice());
        Long orderId = orderDao.save(order);
        orderCartItems(cartItems, orderId);
        deleteCart(cartId);
        return orderId;
    }

    private void validateProductStock(CartItems cartItems) {
        for (CartItem cartItem : cartItems.getValue()) {
            Product product = productDao.findById(cartItem.getProductId());
            product.validateOrderStock(cartItem.getQuantity());
        }
    }

    private void orderCartItems(CartItems cartItems, Long orderId) {
        for (CartItem cartItem : cartItems.getValue()) {
            OrderProduct newOrderProduct = new OrderProduct(orderId, cartItem.getProductId(), cartItem.getQuantity());
            orderProductDao.save(newOrderProduct);
            calculateProductStock(cartItem);
        }
    }

    private void calculateProductStock(CartItem cartItem) {
        productDao.findById(cartItem.getProductId()).sell(cartItem.getQuantity());
    }

    private void deleteCart(Long cartId) {
        cartItemDao.deleteByCartId(cartId);
        cartDao.deleteById(cartId);
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
