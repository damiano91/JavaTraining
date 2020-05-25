package Graph;


import java.io.File;
import java.util.Scanner;

public class StartGraph {

    public static void main(String[] args) {
        int[] values = new int[2];
        try {
            String tcPatch;
            Graph graph;
            tcPatch = "./Damian/resources/Graph/TC1.txt";
            File file = new File(tcPatch);
            Scanner reader = new Scanner(file);
            graph = new Graph();
            while(reader.hasNextLine()){
                getInts(reader.nextLine(), values);
                graph.addEdge(values[0],values[1]);
            }
            graph.printNodesNotConnectedWith(1);
        }
        catch (Exception e){
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }


    public static void getInts(String line, int[] values){
        for(int i =0; i<line.length(); i++){
            if(line.charAt(i) == ','){
                values[0] = Integer.parseInt(line.substring(0,i));
                values[1] = Integer.parseInt(line.substring(i+1,line.length()));
                return;
            }
        }
    }
}
