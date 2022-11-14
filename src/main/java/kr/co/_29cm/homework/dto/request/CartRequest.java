package kr.co._29cm.homework.dto.request;

import java.util.Map;

public class CartRequest {

    private final Map<Long, Integer> cartRequests;

    public CartRequest(Map<Long, Integer> cartRequests) {
        this.cartRequests = cartRequests;
    }

    public Map<Long, Integer> getCartRequests() {
        return cartRequests;
    }
}
