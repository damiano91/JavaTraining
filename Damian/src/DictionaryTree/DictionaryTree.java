package DictionaryTree;

public class DictionaryTree {
    Node[] root;
    int dictSize;

    DictionaryTree(){
        dictSize = 35;
        root = new Node[dictSize];
    }
    public void add(String pol, String eng){
        pol = pol.toLowerCase();
        int index = getIndexBasedOnChar(pol.charAt(0));
        verifyAndCreateNode(root, index);
        Node pointer = root[index];
        for(int i =1; i < pol.length();i++) {
            index = getIndexBasedOnChar(pol.charAt(i));
            verifyAndCreateNode(pointer.nextChar, index);
            pointer = pointer.nextChar[index];
        }
        pointer.eng = eng;
    }

    private void verifyAndCreateNode(Node[] nodeTable, int index){
        if(nodeTable[index] == null){
            nodeTable[index] = new Node(dictSize);
        }
    }

    public String getEng(String pol){
        pol = pol.toLowerCase();
        int index = getIndexBasedOnChar(pol.charAt(0));
        Node pointer = root[index];

        for(int i =1; i < pol.length();i++) {
            index = getIndexBasedOnChar(pol.charAt(i));
            pointer = pointer.nextChar[index];
            if(pointer == null) return "No word in dictionary!";
        }
        if(pointer == null || pointer.eng == null) return "No word in dictionary!";
        else return pointer.eng;
    }


    private static int getIndexBasedOnChar(char c){
        switch (c){
            case 'a': return 0;
            case 'ą': return 1;
            case 'b': return 2;
            case 'c': return 3;
            case 'ć': return 4;
            case 'd': return 5;
            case 'e': return 6;
            case 'ę': return 7;
            case 'f': return 8;
            case 'g': return 9;
            case 'h': return 10;
            case 'i': return 11;
            case 'j': return 12;
            case 'k': return 13;
            case 'l': return 14;
            case 'ł': return 15;
            case 'm': return 16;
            case 'n': return 17;
            case 'ń': return 18;
            case 'o': return 19;
            case 'ó': return 20;
            case 'p': return 21;
            case 'q': return 22;
            case 'r': return 23;
            case 's': return 24;
            case 'ś': return 25;
            case 't': return 26;
            case 'u': return 27;
            case 'v': return 28;
            case 'w': return 29;
            case 'x': return 30;
            case 'y': return 31;
            case 'z': return 32;
            case 'ż': return 33;
            case 'ź': return 34;
            default: return -1;
        }
    }
    public static boolean verifyString(String input){
        input = input.toLowerCase();
        for(int i = 0; i < input.length(); i++){
            if(getIndexBasedOnChar(input.charAt(i)) == -1) return false;
        }
        return true;
    }
}
