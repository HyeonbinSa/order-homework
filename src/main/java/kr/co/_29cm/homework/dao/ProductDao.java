package kr.co._29cm.homework.dao;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import kr.co._29cm.homework.domain.Product;

public class ProductDao {

    private final Map<Long, Product> products;

    public ProductDao() {
        this.products = new LinkedHashMap<>();
    }

    public Long save(Product product) {
        products.put(product.getId(), product);
        return product.getId();
    }

    public Product findById(Long id) {
        return products.get(id);
    }

    public List<Product> findAll() {
        return new ArrayList<>(products.values());
    }
}