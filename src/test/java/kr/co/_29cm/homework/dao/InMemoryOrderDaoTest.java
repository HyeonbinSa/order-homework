package kr.co._29cm.homework.dao;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;

import kr.co._29cm.homework.domain.Order;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class InMemoryOrderDaoTest {

    @DisplayName("주문을 저장한다.")
    @Test
    void save() {
        // given
        final OrderDao inMemoryOrderDao = new FakeOrderDao();
        final Order order = Order.from(5000);

        // when & then
        assertDoesNotThrow(() -> inMemoryOrderDao.save(order));
    }

    @DisplayName("주문을 조회한다.")
    @Test
    void findById() {
        // given
        final OrderDao inMemoryOrderDao = new FakeOrderDao();
        final Order order = Order.from(5000);
        final Long id = inMemoryOrderDao.save(order);

        // when
        Order findOrder = inMemoryOrderDao.findById(id).get();

        // then
        assertEquals(findOrder.getTotalPrice(), 5000);
    }

    @DisplayName("주문을 저장 시 50000원 미만이라면 배송비가 추가된다.")
    @Test
    void findById_Under50000() {
        // given
        final OrderDao inMemoryOrderDao = new FakeOrderDao();
        final Order order = Order.from(5000);
        final Long id = inMemoryOrderDao.save(order);

        // when
        Order findOrder = inMemoryOrderDao.findById(id).get();

        // then
        assertAll(
                () -> assertEquals(findOrder.getTotalPrice(), 5000),
                () -> assertEquals(findOrder.getDeliveryFare(), 2500)
        );
    }

    @DisplayName("주문을 저장 시 50000원 이상이라면 배송비가 무료다.")
    @Test
    void findById_Over50000() {
        // given
        final OrderDao inMemoryOrderDao = new FakeOrderDao();
        final Order order = Order.from(60000);
        final Long id = inMemoryOrderDao.save(order);

        // when
        Order findOrder = inMemoryOrderDao.findById(id).get();

        // then
        assertAll(
                () -> assertEquals(findOrder.getTotalPrice(), 60000),
                () -> assertEquals(findOrder.getDeliveryFare(), 0)
        );
    }
}
