package Binary2D;

import HashTable.HashTable;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class StartBinary2D {
    public static void main(String[] args) {
        String pathMain, pathPattern;
        Binary2D compareImages;
        pathMain = "./Damian/resources/Binary2D/TC1.txt";
        pathPattern = "./Damian/resources/Binary2D/TC1pattern.txt";
        compareImages = new Binary2D(pathMain, pathPattern);
        System.out.println(compareImages.checkIfPatternExists());
    }
}
