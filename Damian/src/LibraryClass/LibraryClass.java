package LibraryClass;

import java.util.ArrayList;
import java.util.List;

public class LibraryClass {
    private int[] storage;
    private int limit;
    private int currentDataAmount;

    LibraryClass(){
        limit = 10;
        currentDataAmount = 0;
        storage = new int[limit];
    }

    public void input(int val){
        if(currentDataAmount<limit){
            storage[currentDataAmount++] = val;
        }
        else{
            extendLimit();
            storage[currentDataAmount++] = val;
        }
    }
    private void extendLimit(){
        limit += 10;
        int[] temp = new int[limit];
        System.arraycopy(storage, 0, temp, 0, storage.length);
        storage = temp;
    }
    public boolean is_value(int val){
        for(int i = 0; i< currentDataAmount; i++){
            if (storage[i] == val) return true;
        }
        return false;
    }
}
