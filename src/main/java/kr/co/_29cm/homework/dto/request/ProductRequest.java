package kr.co._29cm.homework.dto.request;

import kr.co._29cm.homework.domain.Product;

public class ProductRequest {

    private final Long id;
    private final String name;
    private final int price;
    private final int stock;

    public ProductRequest(Long id, String name, int price, int stock) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.stock = stock;
    }

    public Product toProduct() {
        return new Product(id, name, price, stock);
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
