package com.back;

public class Calc {
    public static int run(String exp) {
//        if(exp.equals("1 + 1"))
//            return 2;
//        if(exp.equals("2 + 1"))
//            return 3;
//
//        if(exp.equals("2 + 2"))
//            return 4;
//
//        return -1;

        String[] numbers = exp.split(" \\+ ");
        int first = Integer.parseInt(numbers[0]);
        int last = Integer.parseInt(numbers[1]);
        return first+last;
    }
}
