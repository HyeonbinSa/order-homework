package kr.co._29cm.homework.dao;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

import kr.co._29cm.homework.domain.OrderProduct;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class OrderProductDaoTest {

    @DisplayName("주문 상품을 저장한다.")
    @Test
    void save() {
        // given
        final OrderProductDao orderProductDao = new OrderProductDao();
        final OrderProduct orderProduct = new OrderProduct(1L, 1L, 10);

        // when & then
        assertDoesNotThrow(() -> orderProductDao.save(orderProduct));
    }
}
