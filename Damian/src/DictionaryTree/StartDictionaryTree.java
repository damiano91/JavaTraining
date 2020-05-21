package DictionaryTree;


import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class StartDictionaryTree {
    public static void main(String[] args) {
        String pol,eng,toCheck;
        try {
            File myObj = new File("./Damian/resources/Dictionary/TC1.txt");
            Scanner reader = new Scanner(myObj);
            DictionaryTree dictionary = new DictionaryTree();
            while(reader.hasNext()){
                pol = reader.nextLine();
                eng = reader.nextLine();
                dictionary.add(pol, eng);
            }
            reader = new Scanner(System.in);
            System.out.println('c');
            /*while(true){
                System.out.println("Input polish word to check if exists in dictionary:");
                toCheck = reader.next();
                System.out.println(dictionary.get(toCheck));
            }*/

        } catch (FileNotFoundException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }
}
