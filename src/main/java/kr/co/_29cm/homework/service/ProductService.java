package kr.co._29cm.homework.service;

import java.util.List;
import kr.co._29cm.homework.dao.ProductDao;
import kr.co._29cm.homework.domain.Product;
import kr.co._29cm.homework.dto.request.ProductRequest;

public class ProductService {

    private final ProductDao productDao;

    public ProductService(ProductDao productDao) {
        this.productDao = productDao;
    }

    public Long create(final ProductRequest productRequest) {
        return productDao.save(productRequest.toProduct());
    }

    public Product findById(Long id) {
        return productDao.findById(id);
    }

    public List<Product> findAll() {
        return productDao.findAll();
    }
}
