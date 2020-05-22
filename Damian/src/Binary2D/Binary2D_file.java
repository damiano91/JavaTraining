package Binary2D;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Binary2D_file{
    boolean[][] fileBits;
    int widthSize, heightSize;  //assume width same for each line

    Binary2D_file(String path){
        String line;
        File file;
        widthSize = heightSize = 0;

        try{
            file = new File(path);
            Scanner reader = new Scanner(file);
            getWidthHeight(file, reader);
            reader = new Scanner(file);
            fileBits = new boolean[widthSize][heightSize];
            int hCounter=0;
            while(reader.hasNextLine()){
                line = reader.nextLine();
                for(int i = 0; i<line.length();i++){
                    if(line.charAt(i) == '1') fileBits[i][hCounter] = true;
                    else fileBits[i][hCounter] = false;
                }
                hCounter++;
            }

        }
        catch (Exception e){
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }

    private void getWidthHeight(File file, Scanner reader){
        String line;
        try{
            line = reader.nextLine();
            heightSize++;
            widthSize = line.length();
            while(reader.hasNextLine()){
                heightSize++;
                reader.nextLine();
            }
        }
        catch (Exception e){
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }
}
