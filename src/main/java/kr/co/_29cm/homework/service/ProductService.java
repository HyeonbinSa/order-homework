package kr.co._29cm.homework.service;

import java.util.List;
import java.util.stream.Collectors;
import kr.co._29cm.homework.dao.ProductDao;
import kr.co._29cm.homework.domain.Product;
import kr.co._29cm.homework.dto.request.ProductRequest;
import kr.co._29cm.homework.dto.response.ProductResponse;

public class ProductService {

    private final ProductDao productDao;

    public ProductService(ProductDao productDao) {
        this.productDao = productDao;
    }

    public Long create(final ProductRequest productRequest) {
        return productDao.save(productRequest.toProduct());
    }

    public ProductResponse findById(final Long id) {
        final Product product = productDao.findById(id);
        return ProductResponse.from(product);
    }

    public List<ProductResponse> findAll() {
        return productDao.findAll().stream()
                .map(ProductResponse::from)
                .collect(Collectors.toList());
    }
}
