package com.back;

public class Calc {
    public static int run(String expression) {
        String exp = expression.trim();

        // 가장 마지막 + / - 를 기준으로 나누면 왼쪽부터 계산된다 (좌결합)
        int plusIndex = exp.lastIndexOf(" + ");
        int minusIndex = exp.lastIndexOf(" - ");
        int opIndex = Math.max(plusIndex, minusIndex);

        if (opIndex != -1) {
            int left = run(exp.substring(0, opIndex));
            int right = run(exp.substring(opIndex + 3));

            return opIndex == plusIndex ? left + right : left - right;
        }

        return Integer.parseInt(exp);
    }
}
