package LibraryClass;

import java.util.Scanner;

public class StartLibraryClass {
    public static void main(String[] args){
        LibraryClass lib = new LibraryClass();
        Scanner keyboard = new Scanner(System.in);
        int keyInput;
        while(true){
            System.out.println("Press:\n1. To add element to list\n2. Check if element is on list\n3. Quit program");
            keyInput = keyboard.nextInt();
            switch (keyInput){
                case 1:
                    System.out.println("Input integer to add to list:");
                    keyInput = keyboard.nextInt();
                    lib.input(keyInput);
                    System.out.printf("'%d' added to list\n", keyInput);
                    break;
                case 2:
                    System.out.println("Input integer to check if is on the list:");
                    keyInput = keyboard.nextInt();
                    if(lib.is_value(keyInput)) System.out.printf("%d integer is on the list\n", keyInput);
                    else System.out.printf("'%d' integer is not on the list\n", keyInput);
                    break;
                case 3:
                    System.exit(0);
                default:
                    System.out.println("improper input");
                    break;
            }
        }
    }
}
