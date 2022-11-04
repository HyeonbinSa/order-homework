package kr.co._29cm.homework.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import kr.co._29cm.homework.dao.InMemoryProductDao;
import kr.co._29cm.homework.domain.Product;
import kr.co._29cm.homework.dto.CartResponse;
import kr.co._29cm.homework.dto.OrderResponse;
import kr.co._29cm.homework.service.CartService;
import kr.co._29cm.homework.service.OrderService;
import kr.co._29cm.homework.service.ProductService;
import kr.co._29cm.homework.view.InputView;
import kr.co._29cm.homework.view.OutputView;

public class OrderController {

    final ProductService productService = new ProductService(new InMemoryProductDao());
    final OrderService orderService = new OrderService();
    final CartService cartService = new CartService();

    public void run() {
        if (!inputCommand()) {
            OutputView.printQuitMessage();
            return;
        }
        productService.init();
        List<Product> products = productService.findAll();
        OutputView.printProducts(products);
        Map<Long, Integer> orderRequests = generateOrderRequest();
        Long cartId = cartService.create(orderRequests);
        Long orderId = orderService.create(cartId);
        OrderResponse orderResponse = orderService.find(orderId);
        OutputView.printOrderInformation(orderResponse);
    }

    private Map<Long, Integer> generateOrderRequest() {
        Map<Long, Integer> orderRequests = new HashMap<>();
        while (true) {
            Long productId = InputView.inputProductId();
            Integer stock = InputView.inputStock();
            if (productId == null || stock == null) {
                return orderRequests;
            }
            if (orderRequests.containsKey(productId)) {
                orderRequests.put(productId, orderRequests.get(productId) + stock);
                continue;
            }
            orderRequests.put(productId, stock);
        }
    }

    private boolean inputCommand() {
        try {
            return InputView.inputStartCommand();
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
            return inputCommand();
        }
    }
}
