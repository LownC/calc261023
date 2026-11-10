package com.back;

public class Calc {
    public static int run(String expression) {
        String exp = expression.trim();

        if (isWrappedInParentheses(exp)) {
            return run(exp.substring(1, exp.length() - 1));
        }

        // 가장 마지막 + / - 를 기준으로 나누면 왼쪽부터 계산된다 (좌결합)
        int plusIndex = exp.lastIndexOf(" + ");
        int minusIndex = exp.lastIndexOf(" - ");
        int opIndex = Math.max(plusIndex, minusIndex);

        if (opIndex != -1) {
            int left = run(exp.substring(0, opIndex));
            int right = run(exp.substring(opIndex + 3));

            return opIndex == plusIndex ? left + right : left - right;
        }

        int multiplyIndex = exp.lastIndexOf(" * ");

        if (multiplyIndex != -1) {
            return run(exp.substring(0, multiplyIndex)) * run(exp.substring(multiplyIndex + 3));
        }

        return Integer.parseInt(exp);
    }

    // 첫 글자의 '(' 가 마지막 글자의 ')' 와 짝일 때만 식 전체를 감싼 괄호다
    private static boolean isWrappedInParentheses(String exp) {
        if (!exp.startsWith("(") || !exp.endsWith(")")) return false;

        int depth = 0;

        for (int i = 0; i < exp.length(); i++) {
            char c = exp.charAt(i);

            if (c == '(') depth++;
            else if (c == ')') depth--;

            if (depth == 0 && i < exp.length() - 1) return false;
        }

        return true;
    }
}
