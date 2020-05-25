package Binary2D;

import java.io.File;

public class Binary2D {
    Binary2D_file mainImage;
    Binary2D_file pattern;


    Binary2D(String mainImagePath, String patternPath){
        mainImage = new Binary2D_file(mainImagePath);
        pattern = new Binary2D_file(patternPath);
    }

    public boolean checkIfPatternExists(){
        for (int i =0; i<=mainImage.widthSize - pattern.widthSize; i++){
            for(int j=0; j<=mainImage.heightSize - pattern.heightSize; j++){
                if(compareImage(i,j)) return true;
            }
        }
        return false;
    }

    private boolean compareImage(int startX, int startY){
        for(int i =0; i<pattern.widthSize;i++){
            for(int j=0; j<pattern.heightSize; j++){
                if(mainImage.fileBits[startX + i][startY + j] != pattern.fileBits[i][j]) return false;
            }
        }
        return true;
    }
}
