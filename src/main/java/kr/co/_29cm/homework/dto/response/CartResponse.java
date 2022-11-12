package kr.co._29cm.homework.dto.response;

import java.util.List;
import java.util.stream.Collectors;
import kr.co._29cm.homework.domain.Cart;
import kr.co._29cm.homework.domain.CartItem;

public class CartResponse {

    private final Long cartId;
    private final List<CartItemResponse> cartItemResponses;

    public CartResponse(final Long cartId, final List<CartItemResponse> cartItemResponses) {
        this.cartId = cartId;
        this.cartItemResponses = cartItemResponses;
    }

    public static CartResponse of(final Cart cart, final List<CartItem> cartItems) {
        final List<CartItemResponse> cartItemResponses = cartItems.stream()
                .map(CartItemResponse::from)
                .collect(Collectors.toList());
        return new CartResponse(cart.getId(), cartItemResponses);
    }

    public Long getCartId() {
        return cartId;
    }

    public List<CartItemResponse> getCartItemResponses() {
        return cartItemResponses;
    }
}
