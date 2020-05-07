package StringToHash;

import java.util.Scanner;

public class StartStringToHash {
    public static void main(String[] args){
        Scanner keyboard = new Scanner(System.in);
        System.out.println("enter string to be changed to hash value");
        String inputStr = keyboard.nextLine();
        System.out.println(StringToHash.getHashVal(inputStr));
    }
}
