package org.example.lab1;

import java.util.Arrays;
import java.util.Scanner;


interface Operation {
    int doOperation(int a, int b);
}

// class representing custom exception
class ValidityException extends Exception {
    public ValidityException(String msg) {
        // calling the constructor of parent Exception
        super(msg);

    }
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


    private static int getBaseInput(String s) {
        Scanner scanner = new Scanner(System.in);
        System.out.println(s);
        return scanner.nextInt();
    }

    private static String getExpressionInput() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter expression:");
        return scanner.nextLine();
    }

    private static boolean isValidBase(int base) throws ValidityException {
        boolean validBase = Arrays.asList(supportedBases).contains(base);   // BUG: doesn't assign correct boolean to validBase
        if (!validBase) {
            String msg = "Error – this base isn’t supported.";
            throw new ValidityException(msg);
        }
        return true;
    }

    private static void isValidExpression(String expression) {
//       Whatever Logic should be here...
    }

    private static void solve(String expression) {

        String msg = "The value of expression";
        System.out.println(msg + expression);

    }

    public static void session() {
        int base = getBaseInput("Enter base (2/8/10/16):");
        boolean valid = false;
        try {
            valid = isValidBase(base);
        } catch (ValidityException e) {
            System.out.println(e.getMessage());
            base = getBaseInput("Please enter a base (2/8/10/16):");
            try {
                valid = isValidBase(base);
            } catch (ValidityException exc) {
                throw new RuntimeException(exc);
            }
        }
        if (valid) {
            String expression = getExpressionInput();
            isValidExpression(expression);
            solve(expression); // factory....
        }

    }

    public static void main (String[]args){
        ArithmeticApp.session();
    }
}
