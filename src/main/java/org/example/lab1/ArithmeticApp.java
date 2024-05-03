package org.example.lab1;

import java.util.*;

public class ArithmeticApp {

    public static final int BINARY_BASE = 2;
    public static final int OCTAL_BASE = 8;
    public static final int DECIMAL_BASE = 10;
    public static final int HEX_BASE = 16;
    public static final char ADDITION = '+';
    public static final char SUBTRACTION = '-';
    public static final char MULTIPLICATION = '*';
    public static final char DIVISION = '/';

    public static final int[] supportedBases = {BINARY_BASE, OCTAL_BASE, DECIMAL_BASE, HEX_BASE};
    public static final List<Character> supportedOperators = new ArrayList<>(List.of(ADDITION, SUBTRACTION, MULTIPLICATION, DIVISION));
    public static Scanner scanner = new Scanner(System.in);

    /**
     * Validates if the given base is supported.
     *
     * @param base The base to validate.
     * @return {@code true} if the base is supported, {@code false} otherwise.
     * @throws ValidityException If the base is not supported.
     */
    private static boolean isValidBase(int base) throws ValidityException {
        for (int supportedBase : supportedBases) {
            if (base == supportedBase) {
                return true;
            }
        }
        throw new ValidityException("Error – this base isn’t supported.");
    }


    /**
     * Checks if the given string representation is valid for the specified base.
     *
     * @param str The string representation to check.
     * @return {@code true} if the string representation is valid for the given base, {@code false} otherwise.
     */
    public static boolean isInGivenBase(String str, int base) {
        try {
            Integer.parseInt(str, base);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    /**
     * Validates an arithmetic expression and extracts its parts (operands and operators).
     * <p>
     * This method checks whether the given arithmetic expression is valid for evaluation in the specified base.
     * It removes any whitespace characters from the expression and checks for operators at the beginning
     * and end of the expression. Then, it splits the expression into parts (operands and operators)
     * and adds them to the provided list. If any part of the expression contains an invalid character,
     * it throws an {@code ArithmeticException}.
     * </p>
     *
     * @param expression The arithmetic expression to validate and extract parts from.
     * @param base       The base of the numeric operands in the expression.
     * @param parts      The list to store the extracted expression parts (operands and operators).
     * @throws ArithmeticException If the expression contains an invalid character or operator at the beginning or end.
     */
    private static void isValidExpression(String expression, int base, ArrayList<String> parts) throws ArithmeticException {
        expression = expression.replaceAll(" ", "");
        final char firstCharInExpression = expression.charAt(0);
        final char lastCharInExpression = expression.charAt(expression.length() - 1);

        if (supportedOperators.contains(firstCharInExpression) || supportedOperators.contains(lastCharInExpression)) {
            throw new ArithmeticException();
        }

        String[] expressionParts = expression.split("(?=[-+*/])|(?<=[-+*/])");

        for (String part : expressionParts) {
            if (isInGivenBase(part, base)) {
                parts.add(part);
            } else if (supportedOperators.contains(part.charAt(0))) {
                parts.add(part);
            } else {
                throw new ArithmeticException();
            }
        }
    }

    /**
     * Evaluates an arithmetic expression represented by a list of expression parts and returns the result.
     * <p>
     * This method evaluates the given arithmetic expression in the specified base. It uses two stacks:
     * one for operands and another for operators. It iterates through each part of the expression,
     * pushing operands onto the operands stack and performing operations when encountering operators.
     * After processing all parts of the expression, it applies any remaining operators to the operands
     * and returns the final result as a string representation in the specified base.
     * </p>
     *
     * @param expressionParts The list of expression parts (operands and operators).
     * @return The result of evaluating the expression as a string representation in the specified base.
     */
    private static double solve(ArrayList<String> expressionParts, int base) {
        Stack<Double> operandsStack = new Stack<>();
        Stack<Character> operatorsStack = new Stack<>();

        for (String part : expressionParts) {
            if (supportedOperators.contains(part.charAt(0))) {
                char operator = part.charAt(0);
                while (!operatorsStack.isEmpty() && hasPriorityOver(operator, operatorsStack.peek())) {
                    operandsStack.push((double) applyOperator(operatorsStack.pop(), operandsStack.pop(), operandsStack.pop()));
                }
                operatorsStack.push(operator);
            } else {
                operandsStack.push(Double.parseDouble(part));
            }
        }

        while (!operatorsStack.isEmpty()) {
            operandsStack.push((double) applyOperator(operatorsStack.pop(), operandsStack.pop(), operandsStack.pop()));
        }
        return operandsStack.pop();
    }


    /**
     * Determines whether the second operator has higher precedence than or equal precedence to the first operator.
     *
     * @param firstOperand  The first operator.
     * @param secondOperand The second operator.
     * @return {@code true} if the second operator has higher or equal precedence compared to the first operator; {@code false} otherwise.
     */
    private static boolean hasPriorityOver(char firstOperand, char secondOperand) {
        return (firstOperand != MULTIPLICATION && firstOperand != DIVISION) || (secondOperand != ADDITION && secondOperand != SUBTRACTION);
    }

    /**
     * Applies the specified operator on the given operands and returns the result.
     *
     * @param operator The operator to apply. It can be one of the following: '+', '-', '*', or '/'.
     * @param operandB The second operand.
     * @param operandA The first operand.
     * @return The result of applying the operator on the operands.
     * @throws UnsupportedOperationException If attempting to divide by zero when the operator is '/'.
     */
    public static double applyOperator(char operator, Double operandB, Double operandA) {
        return switch (operator) {
            case ADDITION -> operandA + operandB;
            case SUBTRACTION -> operandA - operandB;
            case MULTIPLICATION -> operandA * operandB;
            case DIVISION -> {
                if (operandB == 0) throw new UnsupportedOperationException();
                yield operandA / operandB;
            }
            default -> 0;
        };
    }

    private static int getBaseInput(String s) throws ValidityException{
        System.out.println(s);
        while (!scanner.hasNextInt()) {
            scanner.next(); // Clear the invalid input
            throw new ValidityException("Error – this base isn’t supported.");
        }
        return scanner.nextInt();

    }

    private static String getExpressionInput() {
        System.out.println("Enter expression:");
        return scanner.nextLine();
    }

    public static void session() {
        int base = 0;
        boolean invalidBase;
        String solution;
        try {
            base = getBaseInput("Enter base (2/8/10/16):");
            scanner.nextLine();
            invalidBase = isValidBase(base);
        } catch (ValidityException e) {
            System.out.println(e.getMessage());
            do {
                try {
                    base = getBaseInput("Please enter a base (2/8/10/16):");
                    invalidBase = isValidBase(base);
                    scanner.nextLine();
                    break;

                } catch (ValidityException exc) {
                    invalidBase = true;
                    System.out.println(exc.getMessage());
                }
            } while (invalidBase);
        }
        if (invalidBase) {
            // Create a mutable object to hold the expression, allowing for modification if necessary
            String expression = getExpressionInput();
            ArrayList<String> expressionParts = new ArrayList<>();
            try {
                isValidExpression(expression, base, expressionParts);
                solution = String.valueOf((int) solve(expressionParts, base));
                String msg = "The value of expression " + expression + " is : " + solution;
                System.out.println(msg);
            } catch (ArithmeticException e) {
                System.out.println("invalid expression: " + expression);
            } catch (UnsupportedOperationException e) {
                System.out.println("Error: trying to divide by 0 (evaluated: \"0\")");
            } catch (EmptyStackException e) {
                System.out.println("Error: invalid expression: \"\"");
            }
        }
        scanner.close();
    }


    public static void main(String[] args) {
        ArithmeticApp.session();
    }

    static class ValidityException extends Exception {
        public ValidityException(String msg) {
            super(msg);
        }
    }

}

