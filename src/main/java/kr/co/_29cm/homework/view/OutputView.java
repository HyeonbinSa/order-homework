package kr.co._29cm.homework.view;

import java.util.List;
import kr.co._29cm.homework.domain.Product;
import kr.co._29cm.homework.dto.OrderProductResponse;
import kr.co._29cm.homework.dto.OrderResponse;

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

    public static void printOrderInformation(OrderResponse orderResponse) {
        System.out.println("주문내역 : ");
        System.out.println("-------------------------------------------");
        List<OrderProductResponse> orderProductResponses = orderResponse.getOrderProductResponses();
        for (OrderProductResponse orderProductResponse : orderProductResponses) {
            System.out.println(orderProductResponse.getName() + " - " + orderProductResponse.getQuantity() + "개");
        }
        System.out.println("-------------------------------------------");
        System.out.print("주문금액: ");
        System.out.println(orderResponse.getPrice() + "원");
        if (orderResponse.getDeliveryFare() != 0) {
            System.out.print("배송비: ");
            System.out.println(orderResponse.getDeliveryFare() + "원");
        }
        System.out.println("-------------------------------------------");
        System.out.print("지불금액: ");
        System.out.println(orderResponse.getTotalPrice() + "원");
    }
}
