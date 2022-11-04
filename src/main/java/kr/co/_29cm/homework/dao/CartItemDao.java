package kr.co._29cm.homework.dao;

import java.util.List;
import kr.co._29cm.homework.domain.CartItem;
import kr.co._29cm.homework.domain.CartItems;

public interface CartItemDao {

    Long save(CartItem cartItem);

    CartItems findByCartId(final Long cartId);

    CartItems findAll();
}
