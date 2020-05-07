package StringToHash;

public class StringToHash {
    public static long getHashVal(String inputStr){
        long hashVal = 0;
        for(int i=0; i<inputStr.length(); i++){
            hashVal += Character.getNumericValue(inputStr.charAt(i));
        }
        return hashVal;
    }
}
