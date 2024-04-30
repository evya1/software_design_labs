package org.example.lab1;
import java.util.Arrays;
import java.util.Scanner;

ValidityException;

interface Operation {
    int doOperation(int a, int b);
}

class Add implements Operation {
    @Override
    public int doOperation(int a, int b) {
        return a + b;
    }
}
class Subtract implements Operation {
    @Override
    public int doOperation(int a, int b) {
        return a - b;
    }
}

class Multiply implements Operation {
    @Override
    public int doOperation(int a, int b) {
        return a * b;
    }
}

class Divide implements Operation {
    @Override
    public int doOperation(int a, int b) {
        if (b == 0) {
            throw new ArithmeticException("Cannot divide by zero");
        }
        return a / b;
    }
}


public class ArithmeticApp {

    public static final int BINARY_BASE = 2;
    public static final int OCTAL_BASE = 8;
    public static final int DECIMAL_BASE = 10;
    public static final int HEX_BASE = 16;

    public static final int[] supportedBases = {BINARY_BASE, OCTAL_BASE, DECIMAL_BASE, HEX_BASE};


    private static int getBaseInput() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter base (2/8/10/16):");
        return scanner.nextInt();
    }

    private static boolean isValidBase(int base) {
        return Arrays.asList(supportedBases).contains(base) ;
    }

    public static void main(String[] args) {

        int base = getBaseInput();
        isValidBase(base);

        String expression = getExpressionInput();
        isValidExpression(expression);

        solve(expression); // factory....


    }

}
