package Equation;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class StartEquation {
    public static void main(String[] args){
        Equation testEq = new Equation();
        try {
            File myObj = new File("./Damian/resources/Equation/TC1.txt");
            Scanner reader = new Scanner(myObj);
            while (reader.hasNextLine()) {
                testEq.readAndSplitEquation(reader.nextLine());
                System.out.println(testEq.solveEquation());
            }
        }catch (FileNotFoundException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }
}
