package CountSort;

import java.lang.reflect.Array;
import java.util.Arrays;

public class CountSort {
    private int[] helpTable;
    private int max,min;

    public void sort(int[] inputTable){
        int iter=0;
        findMinMax(inputTable);
        helpTable = new int[max - min + 1];
        Arrays.fill(helpTable, 0);
        for(int i = 0; i<inputTable.length; i++){
            helpTable[inputTable[i] - min]++;
        }
        for(int i = 0; i<helpTable.length; i++){
            while(helpTable[i] != 0){
                inputTable[iter++] = i + min;
                helpTable[i]--;
            }
        }
    }

    private void findMinMax(int[] inputTable){
        min = max = inputTable[0];
        for(int i=1; i<inputTable.length; i++){
            if(inputTable[i]<min) min = inputTable[i];
            else if(inputTable[i] > max) max = inputTable[i];
        }
    }
}
