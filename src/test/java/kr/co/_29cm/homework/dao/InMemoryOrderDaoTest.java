package kr.co._29cm.homework.dao;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

import kr.co._29cm.homework.domain.Order;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class InMemoryOrderDaoTest {

    @DisplayName("주문을 저장한다.")
    @Test
    void save() {
        // given
        final OrderDao inMemoryOrderDao = new FakeOrderDao();
        final Order order = new Order(5000, 0);

        // when & then
        assertDoesNotThrow(() -> inMemoryOrderDao.save(order));
    }
}
