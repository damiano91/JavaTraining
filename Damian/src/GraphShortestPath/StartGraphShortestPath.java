package GraphShortestPath;

import java.io.File;
import java.util.Scanner;

public class StartGraphShortestPath {
    public static void main(String[] args) {
        int[] values = new int[2];
        double[] cost = new double[1];
        try {
            String tcPatch;
            GraphShortestPath graph;
            tcPatch = "./Damian/resources/GraphShortestPath/TC2.txt";
            File file = new File(tcPatch);
            Scanner reader = new Scanner(file);
            graph = new GraphShortestPath();
            while(reader.hasNextLine()){
                getValues(reader.nextLine(), values, cost);
                graph.addEdge(values[0],values[1], cost[0]);
            }
            System.out.println(graph.get_cost(1,10));
        }
        catch (Exception e){
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }


    public static void getValues(String line, int[] values, double[] cost){
        String[] valuesStr = line.split(",", 3);
        values[0] = Integer.parseInt(valuesStr[0]);
        values[1] = Integer.parseInt(valuesStr[1]);
        cost[0] = Double.parseDouble(valuesStr[2]);
    }
}
