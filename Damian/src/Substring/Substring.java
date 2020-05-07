package Substring;

public class Substring {
    private String mainStr;
    private String substring;
    private int mainInLen;
    private int substrInLen;
    public Substring(String mainInput, String substrInput){
        this.mainStr = mainInput;
        this.substring = substrInput;
        this.mainInLen = mainInput.length();
        this.substrInLen = substrInput.length();
    }
    public boolean compStrings(int index){
        for(int i = 0; i<substrInLen; i++){
            if(this.mainStr.charAt(index+i) == substring.charAt(i))continue;
            else return false;
        }
        return true;
    }
    public boolean is_substringIn(){
        boolean result = false;
        for(int i = 0; i <= mainInLen - substrInLen; i++){
            if(mainStr.charAt(i) == substring.charAt(0)){
                if (compStrings(i)) return true;
            }
        }
        return result;
    }

}
