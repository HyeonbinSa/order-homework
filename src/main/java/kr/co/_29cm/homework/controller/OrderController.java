package kr.co._29cm.homework.controller;

import java.util.List;
import kr.co._29cm.homework.domain.Product;
import kr.co._29cm.homework.service.ProductService;
import kr.co._29cm.homework.view.InputView;
import kr.co._29cm.homework.view.OutputView;

public class OrderController {
    final ProductService productService = new ProductService();

    public void run() {
        if (!inputCommand()) {
            OutputView.printQuitMessage();
            return;
        }
        productService.init();
        List<Product> products = productService.findAll();
        OutputView.printProducts(products);
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
