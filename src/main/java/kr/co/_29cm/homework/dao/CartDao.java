package kr.co._29cm.homework.dao;

import java.util.List;
import java.util.Optional;
import kr.co._29cm.homework.domain.Cart;

public interface CartDao {

    Long save();

    Optional<Cart> findById(final Long id);

    List<Cart> findAll();

    void deleteById(Long id);
}
