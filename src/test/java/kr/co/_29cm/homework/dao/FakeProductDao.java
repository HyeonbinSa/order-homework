package kr.co._29cm.homework.dao;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicLong;
import kr.co._29cm.homework.domain.Product;

public class FakeProductDao implements ProductDao {

    private final Map<Long, Product> IN_MEMORY_PRODUCTS;
    private final AtomicLong ID;

    public FakeProductDao() {
        IN_MEMORY_PRODUCTS = new ConcurrentHashMap<>();
        ID = new AtomicLong(1);
    }

    @Override
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

    @Override
    public Optional<Product> findById(Long id) {
        return Optional.ofNullable(IN_MEMORY_PRODUCTS.get(id));
    }

    @Override
    public List<Product> findAll() {
        return new ArrayList<>(IN_MEMORY_PRODUCTS.values());
    }
}
