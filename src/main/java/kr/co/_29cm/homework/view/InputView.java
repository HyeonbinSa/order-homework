package kr.co._29cm.homework.view;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import kr.co._29cm.homework.dto.request.CartRequest;

public class InputView {

    private static final Scanner SCANNER = new Scanner(System.in);
    public static final String EMPTY = "";

    public static boolean inputStartCommand() {
        try {
            return inputCommand();
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
            return inputCommand();
        }
    }

    private static boolean inputCommand() {
        System.out.print("입력(o[order]: 주문, q[quit]: 종료) : ");
        String command = SCANNER.nextLine();
        if (command.equals("o") || command.equals("order")) {
            return true;
        }
        if (command.equals("q") || command.equals("quit")) {
            return false;
        }
        throw new IllegalArgumentException("명령어는 o[order], q[quit]만 사용할 수 있습니다.");
    }

    public static CartRequest inputCartInformation() {
        Map<Long, Integer> cartRequest = new HashMap<>();
        while (true) {
            Long productId = InputView.inputProductId();
            Integer stock = InputView.inputStock();
            if (productId == null || stock == null) {
                return new CartRequest(cartRequest);
            }
            if (cartRequest.containsKey(productId)) {
                cartRequest.put(productId, cartRequest.get(productId) + stock);
                continue;
            }
            cartRequest.put(productId, stock);
        }
    }

    private static Long inputProductId() {
        System.out.print("상품 번호 : ");
        String input = SCANNER.nextLine();
        if (input.equals(EMPTY)) {
            return null;
        }
        try {
            return Long.parseLong(input);
        } catch (NumberFormatException e) {
            System.out.println("상품 번호는 숫자만 입력 가능합니다.");
            return inputProductId();
        }
    }

    private static Integer inputStock() {
        System.out.print("수량 : ");
        String input = SCANNER.nextLine();
        if (input.equals(EMPTY)) {
            return null;
        }
        try {
            return Integer.parseInt(input);
        } catch (NumberFormatException e) {
            System.out.println("수량은 숫자만 입력 가능합니다.");
            return inputStock();
        }
    }
}
