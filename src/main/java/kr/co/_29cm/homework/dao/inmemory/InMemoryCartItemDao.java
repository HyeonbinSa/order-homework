package kr.co._29cm.homework.dao.inmemory;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicLong;
import java.util.stream.Collectors;
import kr.co._29cm.homework.dao.CartItemDao;
import kr.co._29cm.homework.domain.CartItem;
import kr.co._29cm.homework.domain.CartItems;

public class InMemoryCartItemDao implements CartItemDao {

    private static final Map<Long, CartItem> IN_MEMORY_CART_ITEM = new ConcurrentHashMap<>();
    private static final AtomicLong ID = new AtomicLong(1);

    @Override
    public Long save(CartItem cartItem) {
        CartItem newCartItem = new CartItem(ID.getAndIncrement(),
                cartItem.getCartId(),
                cartItem.getProductId(),
                cartItem.getName(),
                cartItem.getPrice(),
                cartItem.getQuantity());
        IN_MEMORY_CART_ITEM.put(newCartItem.getId(), newCartItem);
        return newCartItem.getId();
    }

    @Override
    public CartItems findByCartId(Long cartId) {
        return new CartItems(IN_MEMORY_CART_ITEM.values().stream()
                .filter(cartItem -> cartItem.getId().equals(cartId))
                .collect(Collectors.toList()));
    }

    @Override
    public CartItems findAll() {
        return new CartItems(new ArrayList<>(IN_MEMORY_CART_ITEM.values()));
    }

    @Override
    public void deleteByCartId(Long cartId) {
        final List<Long> cartItemIds = IN_MEMORY_CART_ITEM.values().stream()
                .filter(cartItem -> cartItem.getCartId().equals(cartId))
                .map(CartItem::getId)
                .collect(Collectors.toList());
        for (Long id : cartItemIds) {
            IN_MEMORY_CART_ITEM.remove(id);
        }
    }
}
