package kr.co._29cm.homework.domain;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;

import kr.co._29cm.homework.domain.Product;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class ProductTest {

    @DisplayName("상품을 생성한다.")
    @Test
    void create() {
        assertDoesNotThrow(() -> new Product(760709L, "파버카스텔 연필1자루", 200, 50));
    }

    @DisplayName("상품 생성 시 상품명이 없다면 예외를 반환한다.")
    @Test
    void create_WhenNullProductName() {
        assertThrows(IllegalArgumentException.class,
                () -> new Product(760709L, null, 200, 50),
                "상품명은 필수 입력 사항입니다.");
    }

    @DisplayName("상품 생성 시 상품명이 공백이라면 예외를 반환한다.")
    @Test
    void create_WhenEmptyProductName() {
        assertThrows(IllegalArgumentException.class,
                () -> new Product(760709L, "", 200, 50),
                "상품명은 필수 입력 사항입니다.");
    }

    @DisplayName("상품 생성 시 상품 금액이 0보다 작다면 예외를 반환한다.")
    @Test
    void create_WhenUnderZeroPrice() {
        assertThrows(IllegalArgumentException.class,
                () -> new Product(760709L, "파버카스텔 연필1자루", -1, 50),
                "상품 금액은 0보다 작을 수 없습니다.");
    }

    @DisplayName("상품 생성 시 상품 재고가 0보다 작다면 예외를 반환한다.")
    @Test
    void create_WhenUnderZeroStock() {
        assertThrows(IllegalArgumentException.class,
                () -> new Product(760709L, "파버카스텔 연필1자루", 200, -1),
                "상품 재고는 0보다 작을 수 없습니다.");
    }
}