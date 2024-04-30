package org.example.lab1;

import java.util.Scanner;

public class Mul {
    public static void main(String[] Args) {
        // Allow user input
        Scanner input = new Scanner(System.in);
        System.out.println("Please enter 2 integers that you want to multiply up"); System.out.println("First ");
        int first = input.nextInt();
        System.out.println(first);
        System.out.println("Second ");
        int second = input.nextInt();
        System.out.println(second);
        int product = first * second;
        System.out.println("The product of " + first + " and " + second + " is " + product); input.close();
    }
}