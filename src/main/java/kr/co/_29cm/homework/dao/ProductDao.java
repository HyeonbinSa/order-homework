package kr.co._29cm.homework.dao;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicLong;
import kr.co._29cm.homework.domain.Product;

public class ProductDao {

    private static final Map<Long, Product> IN_MEMORY_PRODUCTS = new ConcurrentHashMap<>();
    private static final AtomicLong ID = new AtomicLong(1);

    public Long save(Product product) {
        if (product.getId() == null) {
            while (IN_MEMORY_PRODUCTS.get(ID.get()) != null) {
                ID.incrementAndGet();
            }
            Product saveProduct = new Product(ID.get(), product.getName(), product.getPrice(), product.getStock());
            IN_MEMORY_PRODUCTS.put(ID.getAndIncrement(), saveProduct);
            return saveProduct.getId();
        }
        IN_MEMORY_PRODUCTS.put(product.getId(), product);
        return product.getId();
    }

    public Product findById(Long id) {
        return IN_MEMORY_PRODUCTS.get(id);
    }

    public List<Product> findAll() {
        return new ArrayList<>(IN_MEMORY_PRODUCTS.values());
    }
}
