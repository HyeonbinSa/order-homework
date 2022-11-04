package kr.co._29cm.homework.domain;

public class CartItem {

    private Long id;
    private Long cartId;
    private Long productId;
    private String name;
    private int price;
    private int quantity;

    public CartItem(Long id, Long cartId, Long productId, String name, int price, int quantity) {
        this.id = id;
        this.cartId = cartId;
        this.productId = productId;
        this.name = name;
        this.price = price;
        this.quantity = quantity;
    }

    public CartItem(Long cartId, Long productId, String name, int price, int quantity) {
        this(null, cartId, productId, name, price, quantity);
    }

    public Long getId() {
        return id;
    }

    public Long getCartId() {
        return cartId;
    }

    public Long getProductId() {
        return productId;
    }

    public int getQuantity() {
        return quantity;
    }

    public String getName() {
        return name;
    }

    public int getPrice() {
        return price;
    }
}
