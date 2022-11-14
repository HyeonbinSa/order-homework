package kr.co._29cm.homework.dao;

import java.util.List;
import java.util.Optional;
import kr.co._29cm.homework.domain.Product;

public interface ProductDao {

    Long save(Product product);

    Optional<Product> findById(Long id);

    List<Product> findAll();
}
