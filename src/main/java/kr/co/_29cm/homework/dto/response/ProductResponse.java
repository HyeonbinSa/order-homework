package kr.co._29cm.homework.dto.response;

import kr.co._29cm.homework.domain.Product;

public class ProductResponse {

    private Long id;
    private String name;
    private int price;
    private int stock;

    public ProductResponse(Long id, String name, int price, int stock) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.stock = stock;
    }

    public static ProductResponse from(Product product) {
        return new ProductResponse(product.getId(), product.getName(), product.getPrice(), product.getStock());
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public int getPrice() {
        return price;
    }

    public int getStock() {
        return stock;
    }
}
