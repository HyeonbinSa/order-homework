package kr.co._29cm.homework.dao;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicLong;
import kr.co._29cm.homework.domain.Cart;

public class InMemoryCartDao implements CartDao {

    private static final Map<Long, Cart> IN_MEMORY_CART = new ConcurrentHashMap<>();
    private static final AtomicLong ID = new AtomicLong(1);

    @Override
    public Long save() {
        Cart newCart = new Cart(ID.getAndIncrement());
        IN_MEMORY_CART.put(newCart.getId(), newCart);
        return newCart.getId();
    }

    @Override
    public Cart findById(Long id) {
        return IN_MEMORY_CART.get(id);
    }

    @Override
    public List<Cart> findAll() {
        return new ArrayList<>(IN_MEMORY_CART.values());
    }

    @Override
    public void deleteById(Long id) {
        IN_MEMORY_CART.remove(id);
    }
}
