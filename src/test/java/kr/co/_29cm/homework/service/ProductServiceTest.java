package kr.co._29cm.homework.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.List;
import kr.co._29cm.homework.dao.FakeProductDao;
import kr.co._29cm.homework.dto.request.ProductRequest;
import kr.co._29cm.homework.dto.response.ProductResponse;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class ProductServiceTest {

    private ProductService productService;

    @BeforeEach
    void beforeEach() {
        productService = new ProductService(new FakeProductDao());
    }

    @DisplayName("상품을 저장한다.")
    @Test
    void save() {
        // given
        final ProductRequest 파버카스텔_연필1자루 = new ProductRequest(760709L, "파버카스텔 연필1자루", 200, 50);

        // when
        final Long id = productService.create(파버카스텔_연필1자루);

        // then
        assertEquals(id, 파버카스텔_연필1자루.getId());
    }

    @DisplayName("상품을 id로 조회한다.")
    @Test
    void findById() {
        // given
        final ProductRequest 파버카스텔_연필1자루 = new ProductRequest(760709L, "파버카스텔 연필1자루", 200, 50);
        final Long id = productService.create(파버카스텔_연필1자루);

        // when
        final ProductResponse product = productService.findById(id);
        // then
        assertEquals(product.getId(), 파버카스텔_연필1자루.getId());
    }

    @DisplayName("상품을 id로 조회 시 존재하지 않는 상품일 경우 예외를 반환한다.")
    @Test
    void findById_WhenNotExistProduct() {
        // given
        final ProductRequest 파버카스텔_연필1자루 = new ProductRequest(760709L, "파버카스텔 연필1자루", 200, 50);
        final Long id = productService.create(파버카스텔_연필1자루);

        // when
        assertThrows(IllegalArgumentException.class,
                () -> productService.findById(-1L)
                , "존재하지 않는 상품입니다.");
    }

    @DisplayName("상품 목록을 조회한다.")
    @Test
    void findAll() {
        // given
        final ProductRequest 파버카스텔_연필1자루 = new ProductRequest(760709L, "파버카스텔 연필1자루", 200, 50);
        final ProductRequest 캠핑덕_우드롤테이블 = new ProductRequest(778422L, "캠핑덕 우드롤테이블", 45000, 7);
        productService.create(파버카스텔_연필1자루);
        productService.create(캠핑덕_우드롤테이블);

        // when
        final List<ProductResponse> product = productService.findAll();

        // then
        assertEquals(product.size(), 2);
    }
}
