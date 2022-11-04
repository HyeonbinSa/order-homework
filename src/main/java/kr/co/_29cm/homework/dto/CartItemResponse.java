package kr.co._29cm.homework.dto;

import kr.co._29cm.homework.domain.CartItem;

public class CartItemResponse {

    private Long id;
    private Long productId;
    private String name;
    private int price;
    private int quantity;

    public CartItemResponse(Long id, Long productId, String name, int price, int quantity) {
        this.id = id;
        this.productId = productId;
        this.name = name;
        this.price = price;
        this.quantity = quantity;
    }

    public static CartItemResponse from(CartItem cartItem) {
        return new CartItemResponse(cartItem.getId(), cartItem.getProductId(), cartItem.getName(), cartItem.getPrice(),
                cartItem.getQuantity());
    }

    public Long getId() {
        return id;
    }

    public Long getProductId() {
        return productId;
    }

    public String getName() {
        return name;
    }

    public int getPrice() {
        return price;
    }

    public int getQuantity() {
        return quantity;
    }
}
