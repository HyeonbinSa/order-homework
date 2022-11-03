package kr.co._29cm.homework.domain;

import java.time.LocalDateTime;

public class Order {

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
        this(null, totalPrice, totalPrice, LocalDateTime.now());
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
