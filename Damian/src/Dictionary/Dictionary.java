package Dictionary;

public class Dictionary {
    String[][] dict;
    int count;
    int size;

    Dictionary(){
        this.size = 2;
        count = 0;
        dict = new String[size][2];
    }

    public void add(String pol, String eng){
        if(count==size-1) {
            extendDictionary();
        }
        dict[count][0] = pol;
        dict[count++][1] = eng;
    }
    private void extendDictionary(){
        String[][] temp = new String[size*2][2];
        for(int i = 0; i < count; i++){
            temp[i][0] = dict[i][0];
            temp[i][1] = dict[i][1];
        }
        dict = temp;
        size*=2;
    }

    public String get(String pol){
        for(int i = 0; i<count; i++){
            if(dict[i][0].equals(pol)) return dict[i][1];
        }
        return "No word in dictionary!";
    }

}
