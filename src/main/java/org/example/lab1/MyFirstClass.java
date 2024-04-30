package org.example.lab1;

public class MyFirstClass {
    public static void main(String[] args) {
        System.out.println("Name: Name1");
        System.out.println("ID: ...");
        System.out.println("Name: Name2");
        System.out.println("ID: ...");
        System.out.println();
        int a = 3;
        int b = 0;
        int c;
        int d = 0;
        int e = 0;
        int i;
        for (i = 0; i <= 5; i++) {
            b += i * 2;
            System.out.println(a + " " + b + " " + i);
        }
        a = (int) 9.0;
        int f = 0;
        if (a < b) {
            f = 5;
            a = a + f;
        } else {
            a = a - f;
        }
        System.out.println(e);
        System.out.println(b);
        System.out.println(i);
        System.out.println(a);
    }
}
