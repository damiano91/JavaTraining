package MergeSort;

public class MergeSort {
    private int[] inputNumbers;
    private int[] tempNumbers;
    private int inuptLength;

    public void sort(int[] values){
        this.inputNumbers = values;
        inuptLength = values.length;
        this.tempNumbers = new int[inuptLength];
        mergesort(0, inuptLength-1);
    }

    private void mergesort(int low, int high){
        if (low < high) {
            int middle = low + (high - low) / 2;
            mergesort(low, middle);
            mergesort(middle + 1, high);
            merge(low, middle, high);
        }
    }
    private void merge(int low, int middle, int high) {
        for (int i = low; i <= high; i++) {
            tempNumbers[i] = inputNumbers[i];
        }

        int i = low;
        int j = middle + 1;
        int k = low;
        while (i <= middle && j <= high) {
            if (tempNumbers[i] <= tempNumbers[j]) {
                inputNumbers[k] = tempNumbers[i];
                i++;
            } else {
                inputNumbers[k] = tempNumbers[j];
                j++;
            }
            k++;
        }
        while (i <= middle) {
            inputNumbers[k] = tempNumbers[i];
            k++;
            i++;
        }
    }
}
