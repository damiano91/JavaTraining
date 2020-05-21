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
                if(dictionary.verifyString(pol) && dictionary.verifyString(eng)){
                    dictionary.add(pol, eng);
                }
            }
            reader = new Scanner(System.in);
            while(true){
                System.out.println("Input polish word to get english translation:");
                toCheck = reader.next();
                if(dictionary.verifyString(toCheck)){
                    System.out.println(dictionary.getEng(toCheck));
                }
                else System.out.println("Improper word");
            }

        } catch (FileNotFoundException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }

}
