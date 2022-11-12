package kr.co._29cm.homework.service;

import java.util.List;
import java.util.Map.Entry;
import java.util.stream.Collectors;
import kr.co._29cm.homework.dao.CartDao;
import kr.co._29cm.homework.dao.CartItemDao;
import kr.co._29cm.homework.dao.ProductDao;
import kr.co._29cm.homework.domain.Cart;
import kr.co._29cm.homework.domain.CartItem;
import kr.co._29cm.homework.domain.CartItems;
import kr.co._29cm.homework.domain.Product;
import kr.co._29cm.homework.dto.request.CartRequest;
import kr.co._29cm.homework.dto.response.CartResponse;

public class CartService {

    private final CartDao cartDao;
    private final CartItemDao cartItemDao;
    private final ProductDao productDao;

    public CartService(final CartDao cartDao,
                       final CartItemDao cartItemDao,
                       final ProductDao productDao) {
        this.cartDao = cartDao;
        this.cartItemDao = cartItemDao;
        this.productDao = productDao;
    }

    public Long create(CartRequest cartRequest) {
        validateEmptyCart(cartRequest);
        Long createdCartId = cartDao.save();
        List<CartItem> cartItems = cartRequest.getCartRequests().entrySet().stream()
                .map(entry -> generateCartItem(entry, createdCartId))
                .collect(Collectors.toList());
        for (CartItem cartItem : cartItems) {
            cartItemDao.save(cartItem);
        }
        return createdCartId;
    }

    public CartResponse findByCartId(Long cartId) {
        Cart cart = cartDao.findById(cartId);
        CartItems cartItems = cartItemDao.findByCartId(cart.getId());
        return CartResponse.of(cart, cartItems.getValue());
    }

    private CartItem generateCartItem(Entry<Long, Integer> orderRequest, Long cartId) {
        Product product = productDao.findById(orderRequest.getKey());
        return new CartItem(cartId, product.getId(), product.getName(), product.getPrice(), orderRequest.getValue());
    }

    private void validateEmptyCart(CartRequest cartRequest) {
        if (cartRequest.getCartRequests().size() == 0) {
            throw new IllegalArgumentException("장바구니가 비어있습니다.");
        }
    }
}
