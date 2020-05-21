package HashTable;

public class Entry {
    public int[] key;
    public String[] value;
    private int count;
    private int size;

    Entry(){
        size = 2;
        count = 0;
        key = new int[size];
        value = new String[size];
    }

    public void addEntry(int k, String val){
        if(entryExists(val)) return;
        if(count == size) resize();
        key[count] = k;
        value[count++] = val;
    }

    private void resize(){
        int newSize = size*2;
        int[] newKey = new int[newSize];
        String[] newVal = new String[newSize];

        for(int i = 0; i<size; i++){
            newKey[i] = key[i];
            newVal[i] = value[i];
        }
        key = newKey;
        value = newVal;
        size = newSize;
    }

    public boolean entryExists(String input){
        for(int i = 0; i < count; i++){
            if(input.equals(value[i])) return true;
        }
        return false;
    }
}
