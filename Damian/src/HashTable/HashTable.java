package HashTable;

public class HashTable {
    Entry[] hashTable;
    int size;

    HashTable(){
        size = 1000;
        hashTable = new Entry[size];
    }

    public void addString(String input){
        int key = getHashVal(input);
        if(hashTable[key] == null){
            hashTable[key] = new Entry();
        }
        hashTable[key].addEntry(key, input);
    }

    private int getHashVal(String input){
        int val = 0;
        for (int i = 0; i<input.length(); i++){
            val += (int)(input.charAt(i)) * (i+1);
        }
        return val%size;
    }

    public boolean is_value(String input){
        int key = getHashVal(input);
        if(hashTable[key] == null) return false;
        return hashTable[key].entryExists(input);
    }
}
