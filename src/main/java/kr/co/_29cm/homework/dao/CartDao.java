package kr.co._29cm.homework.dao;

import java.util.List;
import kr.co._29cm.homework.domain.Cart;

public interface CartDao {

    Long save();

    Cart findById(final Long id);

    List<Cart> findAll();
}
