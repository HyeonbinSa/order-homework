package kr.co._29cm.homework.view;

import java.util.Scanner;

public class InputView {

    private static final Scanner scanner = new Scanner(System.in);

    public static boolean inputStartCommand() {
        System.out.print("입력(o[order]: 주문, q[quit]: 종료) : ");
        String command = scanner.next();
        if (command.equals("o") || command.equals("order")) {
            return true;
        }
        if (command.equals("q") || command.equals("quit")) {
            return false;
        }
        throw new IllegalArgumentException("명령어는 o[order], q[quit]만 사용할 수 있습니다.");
    }
}
