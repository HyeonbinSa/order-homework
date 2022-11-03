package kr.co._29cm.homework.dao;

import java.util.List;
import kr.co._29cm.homework.domain.Product;

public interface ProductDao {

    Long save(Product product);

    Product findById(Long id);

    List<Product> findAll();
}
