package kr.co._29cm.homework.service;

import java.util.List;
import java.util.stream.Collectors;
import kr.co._29cm.homework.dao.ProductDao;
import kr.co._29cm.homework.domain.Product;
import kr.co._29cm.homework.support.CsvReader;

public class ProductService {

    private final ProductDao productDao;

    public ProductService(ProductDao productDao) {
        this.productDao = productDao;
    }

    public void init() {
        final List<String[]> lines = CsvReader.read("items.csv");
        final List<Product> products = generateProducts(lines);
        for (Product product : products) {
            productDao.save(product);
        }
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

    private List<Product> generateProducts(List<String[]> lines) {
        return lines.stream().map(this::toProduct)
                .collect(Collectors.toList());
    }

    private Product toProduct(final String[] line) {
        final Long id = Long.parseLong(line[0]);
        final String name = line[1];
        final int price = Integer.parseInt(line[2]);
        final int stock = Integer.parseInt(line[3]);
        return new Product(id, name, price, stock);
    }
}
