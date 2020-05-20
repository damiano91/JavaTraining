package LibBinaryTreeLoop;


import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class StartLibBinaryTreeLoop {
    public static void main(String[] args) {
        LibBinaryTreeLoop lib = new LibBinaryTreeLoop();
        Scanner keyboard = new Scanner(System.in);
        int size;
        int keyInput;
        try {
            File myObj = new File("./Damian/resources/LibBinaryTree/TC1.txt");
            Scanner reader = new Scanner(myObj);
            size = Integer.parseInt(reader.nextLine());
            for (int i = 0; i < size; i++) {
                lib.add(Integer.parseInt(reader.nextLine()));
            }

        } catch (FileNotFoundException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
        while(true){
            System.out.println("Input integer to chef if exists in library:");
            keyInput = keyboard.nextInt();
            System.out.println(lib.checkValue(keyInput));
        }

    }
}
