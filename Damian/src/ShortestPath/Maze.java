package ShortestPath;

import java.io.File;
import java.util.Scanner;

public class Maze {

    int[][] mazeTable;
    int widthSize, heightSize;  //assume width same for each line
    XYpoint start, end;

    Maze(String path){
        String line;
        File file;
        widthSize = heightSize = 0;

        try{
            file = new File(path);
            Scanner reader = new Scanner(file);
            getWidthHeight(file, reader);
            reader = new Scanner(file);
            mazeTable = new int[widthSize][heightSize];
            int hCounter=0;
            int val;
            while(reader.hasNextLine()){
                line = reader.nextLine();
                for(int i = 0; i<line.length();i++){
                    val = Integer.parseInt(String.valueOf(line.charAt(i)));
                    mazeTable[i][hCounter] = val;
                    if(val == 2){
                        start = new XYpoint(i, hCounter);
                    }
                    else if(val== 3){
                        end = new XYpoint(i, hCounter);
                    }
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
