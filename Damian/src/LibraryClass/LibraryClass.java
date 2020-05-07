package LibraryClass;

import java.util.ArrayList;
import java.util.List;

public class LibraryClass {
    private List<Integer> storage;
    public LibraryClass(){
        storage = new ArrayList<Integer>();
    }
    public void input(int val){
        storage.add(val);
    }
    public boolean is_value(int val){
        return storage.contains(val);

    }
}
