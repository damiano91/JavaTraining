package Substring;

public class Substring {
    private String mainStr;
    private String substring;
    private boolean compStrings(int index){
        for(int i = 0; i<substring.length(); i++){
            if(this.mainStr.charAt(index+i) == substring.charAt(i))continue;
            else return false;
        }
        return true;
    }
    public boolean is_substringIn(String mainGiven, String substrGiven){
        mainStr = mainGiven;
        substring = substrGiven;
        boolean result = false;
        for(int i = 0; i <= mainStr.length() - substring.length(); i++){
            if(mainStr.charAt(i) == substring.charAt(0)){
                if (compStrings(i)) return true;
            }
        }
        return result;
    }

}
