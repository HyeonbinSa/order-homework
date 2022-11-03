package kr.co._29cm.homework.dao;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

import kr.co._29cm.homework.domain.OrderProduct;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class InMemoryOrderProductDaoTest {

    @DisplayName("주문 상품을 저장한다.")
    @Test
    void save() {
        // given
        final InMemoryOrderProductDao inMemoryOrderProductDao = new InMemoryOrderProductDao();
        final OrderProduct orderProduct = new OrderProduct(1L, 1L, 10);

        // when & then
        assertDoesNotThrow(() -> inMemoryOrderProductDao.save(orderProduct));
    }
}
