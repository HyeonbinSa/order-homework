package kr.co._29cm.homework.view;

import java.util.List;
import kr.co._29cm.homework.domain.Product;

public class OutputView {

    private static final String PRODUCT_INFORMATION_HEADER = "%-7s %-35s %-10s %-4s\n";
    private static final String PRODUCT_INFORMATION = "%-9d %-35s %-10d  %-4d\n";

    public static void printProducts(List<Product> products) {
        System.out.printf(PRODUCT_INFORMATION_HEADER, "상품번호", "상품명", "판매가격", "재고 수");
        for (Product product : products) {
            System.out.printf(PRODUCT_INFORMATION,
                    product.getId(),
                    product.getName(),
                    product.getPrice(),
                    product.getStock());
        }
    }

    public static void printQuitMessage() {
        System.out.println("고객님의 주문 감사합니다.");
    }
}
