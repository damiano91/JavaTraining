package Substring;

import java.util.Scanner;

public class StartSubstring {
    public static void main(String[] args){
        String s1,s2;
        Scanner keyboard = new Scanner(System.in);
        System.out.println("Enter main string:");
        s1 = keyboard.nextLine();
        System.out.println("Enter substring to find :");
        s2 = keyboard.nextLine();
        Substring checkStrings = new Substring();
        System.out.println(checkStrings.is_substringIn(s1,s2));
    }
}
