package com.back;

public class Calc {
    public static int run(String expression) {
        String exp = expression.trim();

        if (isWrappedInParentheses(exp)) {
            return run(exp.substring(1, exp.length() - 1));
        }

        // 괄호 밖에서 가장 마지막 + / - 를 기준으로 나누면 왼쪽부터 계산된다 (좌결합)
        // + / - 로 먼저 나누고 * 는 더 깊은 재귀에서 계산하므로 곱셈이 먼저 계산된다
        int opIndex = findLastTopLevelOperator(exp, "+-");

        if (opIndex != -1) {
            int left = run(exp.substring(0, opIndex));
            int right = run(exp.substring(opIndex + 2));

            return exp.charAt(opIndex + 1) == '+' ? left + right : left - right;
        }

        int multiplyIndex = findLastTopLevelOperator(exp, "*");

        if (multiplyIndex != -1) {
            return run(exp.substring(0, multiplyIndex)) * run(exp.substring(multiplyIndex + 2));
        }

        return Integer.parseInt(exp);
    }

    // 괄호 밖에 있는 " op " 중 가장 마지막 것의 시작 위치(앞 공백)를 돌려준다. 없으면 -1
    private static int findLastTopLevelOperator(String exp, String operators) {
        int depth = 0;
        int found = -1;

        for (int i = 0; i < exp.length(); i++) {
            char c = exp.charAt(i);

            if (c == '(') depth++;
            else if (c == ')') depth--;
            else if (depth == 0 && isBinaryOperatorAt(exp, i, operators)) found = i - 1;
        }

        return found;
    }

    // 양옆이 공백인 연산자만 이항 연산자로 본다 ("10 * -10" 의 '-' 는 음수 부호)
    private static boolean isBinaryOperatorAt(String exp, int i, String operators) {
        return operators.indexOf(exp.charAt(i)) != -1
                && i > 0 && exp.charAt(i - 1) == ' '
                && i < exp.length() - 1 && exp.charAt(i + 1) == ' ';
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
