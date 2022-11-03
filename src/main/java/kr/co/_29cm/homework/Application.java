package kr.co._29cm.homework;

import kr.co._29cm.homework.controller.OrderController;

public class Application {

    public static void main(String[] args) {
        OrderController orderController = new OrderController();
        orderController.run();
    }
}
