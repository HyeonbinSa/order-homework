package kr.co._29cm.homework.service;

import java.util.List;
import kr.co._29cm.homework.dao.ProductDao;
import kr.co._29cm.homework.domain.Product;

public class ProductService {

    private final ProductDao productDao;

    public ProductService() {
        this.productDao = new ProductDao();
    }

    public Long create(final Product product) {
        return productDao.save(product);
    }

    public Product findById(Long id) {
        return productDao.findById(id);
    }

    public List<Product> findAll() {
        return productDao.findAll();
    }
}
