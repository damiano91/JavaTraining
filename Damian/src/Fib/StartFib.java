package Fib;

import Fib.Fib;
import java.util.Scanner;

public class StartFib {
    public static void main(String[] args){
        Fib fib = new Fib();
        Scanner keyboard = new Scanner(System.in);
        System.out.println("enter an Fibonacci element");
        int FibElement = keyboard.nextInt();
        System.out.println(fib.calculate(FibElement));
    }
}
