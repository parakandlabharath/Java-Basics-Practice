package com.java.basics;

public class Sample {
    private final int a;
    private final int b;

    public Sample() {
        this.a = 10;
        this.b = 20;
        System.out.println("Constructor of Sample class");
    }

    public void display() {
        System.out.println(a);
        System.out.println(b);
    }
}
