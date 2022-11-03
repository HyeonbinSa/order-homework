package kr.co._29cm.homework.domain;

public class OrderProduct {

    private Long id;
    private Long orderId;
    private Long productId;
    private int quantity;

    public OrderProduct(Long id, Long orderId, Long productId, int quantity) {
        this.id = id;
        this.orderId = orderId;
        this.productId = productId;
        this.quantity = quantity;
    }

    public OrderProduct(Long orderId, Long productId, int quantity) {
        this(null, orderId, productId, quantity);
    }

    public OrderProduct(Long productId, int quantity) {
        this(null, null, productId, quantity);
    }

    public Long getId() {
        return id;
    }

    public Long getOrderId() {
        return orderId;
    }

    public Long getProductId() {
        return productId;
    }

    public int getQuantity() {
        return quantity;
    }
}
