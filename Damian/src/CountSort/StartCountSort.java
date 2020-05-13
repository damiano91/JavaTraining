package CountSort;

import MergeSort.MergeSort;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class StartCountSort {
    public static void main(String[] args) {
        CountSort testCount = new CountSort();
        int size;
        int[] intsTable;
        try {
            File myObj = new File("./Damian/resources/CountSort/TC1.txt");
            Scanner reader = new Scanner(myObj);
            size = Integer.parseInt(reader.nextLine());
            intsTable = new int[size];
            for (int i = 0; i < size; i++) {
                intsTable[i] = Integer.parseInt(reader.nextLine());
            }
            testCount.sort(intsTable);
            for (int i = 0; i < size; i++) System.out.println(intsTable[i]);

        } catch (FileNotFoundException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }
}
