package kr.co._29cm.homework.domain;

import java.time.LocalDateTime;

public class Order {

    private static final int MINIMUM_ORDER_PRICE = 50000;
    private static final int DELIVERY_FARE = 2500;
    private static final int FREE_DELIVERY_FARE = 0;

    private Long id;
    private int totalPrice;
    private int deliveryFare;
    private LocalDateTime orderTime;

    public Order(Long id, int totalPrice, int deliveryFare, LocalDateTime orderTime) {
        this.id = id;
        this.totalPrice = totalPrice;
        this.deliveryFare = deliveryFare;
        this.orderTime = orderTime;
    }

    public Order(int totalPrice, int deliveryFare) {
        this(null, totalPrice, deliveryFare, LocalDateTime.now());
    }

    public static Order from(int totalPrice) {
        return new Order(null, totalPrice, calculateDeliveryFare(totalPrice), LocalDateTime.now());
    }

    private static int calculateDeliveryFare(int totalPrice) {
        if (totalPrice < MINIMUM_ORDER_PRICE) {
            return DELIVERY_FARE;
        }
        return FREE_DELIVERY_FARE;
    }

    public Long getId() {
        return id;
    }

    public int getTotalPrice() {
        return totalPrice;
    }

    public int getDeliveryFare() {
        return deliveryFare;
    }

    public LocalDateTime getOrderTime() {
        return orderTime;
    }
}
