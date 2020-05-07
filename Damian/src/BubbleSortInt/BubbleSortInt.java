package BubbleSortInt;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class BubbleSortInt {
    public static int[] bubbleSort(int[] intTable){
        int temp;
        for(int i=1; i<intTable.length; i++){
            for(int j=i; j>= 1; j--){
                if(intTable[j]< intTable[j-1]) {
                    temp = intTable[j];
                    intTable[j] = intTable[j - 1];
                    intTable[j - 1] = temp;
                }
                else break;
            }
        }
        return intTable;
    }


    public static void main(String[] args){
        int size;
        int[] intsTable;
        try {
            File myObj = new File("./Damian/resources/BubbleSortInt/TC1.txt");
            Scanner reader = new Scanner(myObj);
            size = Integer.parseInt(reader.nextLine());
            intsTable = new int[size];
            for(int i =0; i<size; i++) {
                intsTable[i] = Integer.parseInt(reader.nextLine());
            }
            intsTable = bubbleSort(intsTable);
            for(int i =0; i<size; i++) System.out.println(intsTable[i]);

        }catch (FileNotFoundException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }

}
