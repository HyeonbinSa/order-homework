package kr.co._29cm.homework.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import kr.co._29cm.homework.domain.Product;
import kr.co._29cm.homework.service.ProductService;
import kr.co._29cm.homework.view.InputView;
import kr.co._29cm.homework.view.OutputView;

public class OrderController {
    public static final String EMPTY = "";
    final ProductService productService = new ProductService();

    public void run() {
        if (!inputCommand()) {
            OutputView.printQuitMessage();
            return;
        }
        productService.init();
        List<Product> products = productService.findAll();
        OutputView.printProducts(products);
        // 주문 시작
        Map<Long, Integer> orderRequests = generateOrderRequest();
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
            orderRequests.put(productId, 0);
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
