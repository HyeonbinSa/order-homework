package kr.co._29cm.homework.dto;

import java.util.List;
import kr.co._29cm.homework.domain.Order;

public class OrderResponse {

    private final int price;
    private final int deliveryFare;
    private final int totalPrice;
    private final List<OrderProductResponse> orderProductResponses;

    public OrderResponse(int price, int deliveryFare, int totalPrice,
                         List<OrderProductResponse> orderProductResponses) {
        this.price = price;
        this.deliveryFare = deliveryFare;
        this.totalPrice = totalPrice;
        this.orderProductResponses = orderProductResponses;
    }

    public static OrderResponse of(Order order, List<OrderProductResponse> orderProductResponses) {
        int price = order.getTotalPrice();
        int deliveryFare = order.getDeliveryFare();
        int totalPrice = price + deliveryFare;
        return new OrderResponse(price, deliveryFare, totalPrice, orderProductResponses);
    }

    public int getPrice() {
        return price;
    }

    public int getDeliveryFare() {
        return deliveryFare;
    }

    public int getTotalPrice() {
        return totalPrice;
    }

    public List<OrderProductResponse> getOrderProductResponses() {
        return orderProductResponses;
    }
}
