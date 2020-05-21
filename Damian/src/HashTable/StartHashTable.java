package HashTable;


import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class StartHashTable {
    public static void main(String[] args) {
        String pol,eng,toCheck;
        try {
            File myObj = new File("./Damian/resources/Dictionary/TC1.txt");
            Scanner reader = new Scanner(myObj);
            HashTable hTable = new HashTable();
            while(reader.hasNext()){
                pol = reader.nextLine();
                hTable.addString(pol);
            }
            reader = new Scanner(System.in);
            while(true){
                System.out.println("Input string to check if added to library:");
                toCheck = reader.next();
                System.out.println(hTable.is_value(toCheck));
            }

        } catch (FileNotFoundException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }
}
