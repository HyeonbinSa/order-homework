package kr.co._29cm.homework.controller;

import java.util.List;
import kr.co._29cm.homework.dao.CartDao;
import kr.co._29cm.homework.dao.CartItemDao;
import kr.co._29cm.homework.dao.inmemory.InMemoryCartDao;
import kr.co._29cm.homework.dao.inmemory.InMemoryCartItemDao;
import kr.co._29cm.homework.dao.inmemory.InMemoryOrderDao;
import kr.co._29cm.homework.dao.inmemory.InMemoryOrderProductDao;
import kr.co._29cm.homework.dao.inmemory.InMemoryProductDao;
import kr.co._29cm.homework.dao.OrderDao;
import kr.co._29cm.homework.dao.OrderProductDao;
import kr.co._29cm.homework.dao.ProductDao;
import kr.co._29cm.homework.dto.request.CartRequest;
import kr.co._29cm.homework.dto.response.OrderResponse;
import kr.co._29cm.homework.dto.request.ProductRequest;
import kr.co._29cm.homework.dto.response.ProductResponse;
import kr.co._29cm.homework.service.CartService;
import kr.co._29cm.homework.service.OrderService;
import kr.co._29cm.homework.service.ProductService;
import kr.co._29cm.homework.support.ProductInitializer;
import kr.co._29cm.homework.view.InputView;
import kr.co._29cm.homework.view.OutputView;

public class OrderController {

    private final ProductDao productDao;
    private final OrderDao orderDao;
    private final OrderProductDao orderProductDao;
    private final CartItemDao cartItemDao;
    private final CartDao cartDao;
    private final ProductService productService;
    private final CartService cartService;
    private final OrderService orderService;

    public OrderController() {
        this.productDao = new InMemoryProductDao();
        this.orderDao = new InMemoryOrderDao();
        this.orderProductDao = new InMemoryOrderProductDao();
        this.cartItemDao = new InMemoryCartItemDao();
        this.cartDao = new InMemoryCartDao();
        this.productService = new ProductService(productDao);
        this.cartService = new CartService(cartDao, cartItemDao, productDao);
        this.orderService = new OrderService(productDao, orderDao, orderProductDao, cartItemDao, cartDao);
    }

    public void init(){
        List<ProductRequest> productRequests = ProductInitializer.init();
        for(ProductRequest productRequest: productRequests){
            productService.create(productRequest);
        }
    }

    public void run() {
        if (!InputView.inputStartCommand()) {
            OutputView.printQuitMessage();
            return;
        }
        try {
            List<ProductResponse> products = productService.findAll();
            OutputView.printProducts(products);
            CartRequest cartRequest = InputView.inputCartInformation();
            Long cartId = cartService.create(cartRequest);
            Long orderId = orderService.create(cartId);
            OrderResponse orderResponse = orderService.find(orderId);
            OutputView.printOrderInformation(orderResponse);
        }catch(RuntimeException e){
            System.out.println(e.getMessage());
        }
        run();
    }
}
