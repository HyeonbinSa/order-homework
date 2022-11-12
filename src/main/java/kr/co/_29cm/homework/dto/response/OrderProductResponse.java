package kr.co._29cm.homework.dto.response;

public class OrderProductResponse {

    private final String name;
    private final int quantity;

    public OrderProductResponse(String name, int quantity) {
        this.name = name;
        this.quantity = quantity;
    }

    public String getName() {
        return name;
    }

    public int getQuantity() {
        return quantity;
    }
}
