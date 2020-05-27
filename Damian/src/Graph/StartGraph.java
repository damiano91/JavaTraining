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
                values = getInts(reader.nextLine());
                graph.addEdge(values[0],values[1]);
            }
            graph.printNodesNotConnectedWith(1);
        }
        catch (Exception e){
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }


    public static int[] getInts(String line){
        int[] values = new int[2];
        String[] intsStr = line.split(",", 2);
        values[0] = Integer.parseInt(intsStr[0]);
        values[1] = Integer.parseInt(intsStr[1]);
        return values;
    }
}
