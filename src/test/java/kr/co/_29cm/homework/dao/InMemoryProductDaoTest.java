package kr.co._29cm.homework.dao;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.List;
import kr.co._29cm.homework.domain.Product;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class InMemoryProductDaoTest {

    @DisplayName("상품을 저장한다.")
    @Test
    void save() {
        // given
        final ProductDao productDao = new FakeProductDao();
        final Product 파버카스텔_연필1자루 = new Product(760709L, "파버카스텔 연필1자루", 200, 50);

        // when & then
        assertDoesNotThrow(() -> productDao.save(파버카스텔_연필1자루));
    }

    @DisplayName("상품 번호로 상품을 조회한다.")
    @Test
    void findById() {
        // given
        final ProductDao productDao = new FakeProductDao();
        final Product 파버카스텔_연필1자루 = new Product(760709L, "파버카스텔 연필1자루", 200, 50);
        productDao.save(파버카스텔_연필1자루);

        // when
        Product product = productDao.findById(파버카스텔_연필1자루.getId());

        // then
        assertAll(
                () -> assertEquals(product.getId(), 파버카스텔_연필1자루.getId()),
                () -> assertEquals(product.getName(), 파버카스텔_연필1자루.getName()),
                () -> assertEquals(product.getPrice(), 파버카스텔_연필1자루.getPrice()),
                () -> assertEquals(product.getStock(), 파버카스텔_연필1자루.getStock())
        );
    }

    @DisplayName("상품 목록을 조회한다.")
    @Test
    void findAll() {
        // given
        final ProductDao productDao = new FakeProductDao();
        final Product 파버카스텔_연필1자루 = new Product(760709L, "파버카스텔 연필1자루", 200, 50);
        final Product 캠핑덕_우드롤테이블 = new Product(778422L, "캠핑덕 우드롤테이블", 45000, 7);
        productDao.save(파버카스텔_연필1자루);
        productDao.save(캠핑덕_우드롤테이블);

        // when
        List<Product> products = productDao.findAll();

        // then
        assertEquals(products.size(), 2);
    }
}
