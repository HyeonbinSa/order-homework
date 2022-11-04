package kr.co._29cm.homework.domain;

import java.util.List;
import java.util.stream.Collectors;

public class CartItems {

    private final List<CartItem> value;

    public CartItems(List<CartItem> value) {
        this.value = value;
    }

    public List<CartItem> getValue() {
        return value;
    }

    public int calculateTotalPrice() {
        List<Integer> prices = value.stream()
                .map(cartItem -> cartItem.getPrice() * cartItem.getQuantity())
                .collect(Collectors.toList());
        int sum = 0;
        for (int price : prices) {
            sum += price;
        }
        return sum;
    }
}
