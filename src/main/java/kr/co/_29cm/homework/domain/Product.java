package kr.co._29cm.homework.domain;

import kr.co._29cm.homework.exception.SoldOutException;

public class Product {

    private static final String EMPTY_NAME = "";
    private static final int MINIMUM_PRICE = 0;
    private static final int MINIMUM_STOCK = 0;

    private Long id;
    private String name;
    private int price;
    private int stock;

    public Product(final Long id, final String name, final int price, final int stock) {
        validateName(name);
        validatePrice(price);
        validateStock(stock);
        this.id = id;
        this.name = name;
        this.price = price;
        this.stock = stock;
    }

    private void validateName(String name) {
        if (name == null || name.equals(EMPTY_NAME)) {
            throw new IllegalArgumentException("상품명은 필수 입력 사항입니다.");
        }
    }

    private void validatePrice(int price) {
        if (price < MINIMUM_PRICE) {
            throw new IllegalArgumentException("상품 금액은 0보다 작을 수 없습니다.");
        }
    }

    private void validateStock(int stock) {
        if (stock < MINIMUM_STOCK) {
            throw new IllegalArgumentException("상품 재고는 0보다 작을 수 없습니다.");
        }
    }

    public void sell(int quantity) {
        validateOrderStock(quantity);
        this.stock -= quantity;
    }

    public void validateOrderStock(int quantity) {
        if (stock < quantity) {
            throw new SoldOutException();
        }
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
