package MyImage;

import java.awt.image.BufferedImage;

public class GaussFilter {
    BufferedImage imgOriginal;
    BufferedImage imgWithGaussFilter;
    int[][] gaussMatrix;
    int diameter;
    int gaussMatrixSize;
    int gaussMatrixCount;
    int width, height;

    GaussFilter(int diameter, BufferedImage img){
        this.imgOriginal = img;
        this.diameter = diameter;
        width = imgOriginal.getWidth();
        height = imgOriginal.getHeight();
        imgWithGaussFilter = new BufferedImage(imgOriginal.getWidth(), imgOriginal.getHeight(), BufferedImage.TYPE_INT_ARGB);
        gaussMatrixSize = diameter*2+1;
        gaussMatrix = new int[gaussMatrixSize][gaussMatrixSize];
        gaussMatrixCount = 0;
    }

    public BufferedImage getImgWithGaussFilter(){
        assembleMatrix();
        gaussFilterWithoutBoundaries();
        calculateBoundries();
        return imgWithGaussFilter;
    }

    private int[] getPascalVals(){
        int[] pascalVals = new int[gaussMatrixSize];
        int[] temp = new int [gaussMatrixSize];
        for(int i =0; i<pascalVals.length; i++){
            for(int j=0; j<=i; j++){
                if(j==0 || j ==i) temp[j] = 1;
                else temp[j] = pascalVals[j] + pascalVals[j-1];
            }
            System.arraycopy(temp,0,pascalVals,0,temp.length);
        }
        return pascalVals;
    }

    private void assembleMatrix(){
        int[] pascalVals = getPascalVals();
        for(int i = 0; i<gaussMatrixSize;i++){
            gaussMatrix[i][0] = pascalVals[i];
            gaussMatrix[0][i] = pascalVals[i];
            gaussMatrixCount += pascalVals[i]*2;
        }
        for(int i=1; i< gaussMatrixSize;i++){
            for(int j =1; j< gaussMatrixSize; j++){
                gaussMatrix[i][j] = gaussMatrix[i][0] * gaussMatrix[0][j];
                gaussMatrixCount += gaussMatrix[i][j];
            }
        }
    }
    private void gaussFilterWithoutBoundaries(){
        int[] allNeighboursVal = new int[4];
        int x;
        int y;
        for(x =diameter; x< width -diameter;x++){
            for (y=diameter; y< height - diameter; y++){
                allNeighboursVal = calculateNeighboursColor(x,y);
                setPixelValColor(allNeighboursVal,x,y);
            }
        }
    }

    private int[] calculateNeighboursColor(int xIn, int yIn){
        int[] val = new int[4];
        for (int x = xIn - diameter; x <= xIn + diameter; x++) {
            for (int y = yIn - diameter; y <= yIn + diameter; y++) {
                val[0] += (imgOriginal.getRGB(x, y) >> 24 & 0xff) * gaussMatrix[xIn + diameter - x][yIn + diameter - y];
                val[1] += (imgOriginal.getRGB(x, y) >> 16 & 0xff) * gaussMatrix[xIn + diameter - x][yIn + diameter - y];
                val[2] += (imgOriginal.getRGB(x, y) >> 8 & 0xff) * gaussMatrix[xIn + diameter - x][yIn + diameter - y];
                val[3] += (imgOriginal.getRGB(x, y) & 0xff) * gaussMatrix[xIn + diameter - x][yIn + diameter - y];
            }
        }
        for(int i=0; i<val.length; i++) val[i] /=gaussMatrixCount;
        return val;
    }

    private void setPixelValColor(int[] val, int x, int y){
        int pixelVal = val[0];
        for(int i =1; i< 4; i++){
            pixelVal <<= 8;
            pixelVal += val[i];
        }
        imgWithGaussFilter.setRGB(x,y, pixelVal);
    }
    private void calculateBoundries() {
        int[] boundryVal;
        int x;
        int y;
        for(x=0; x<width;x++){
            for(y=0; y<diameter;y++){
                boundryVal = getBoundryVal(x,y);
                setPixelValColor(boundryVal,x,y);
                boundryVal = getBoundryVal(x,height-y-1);
                setPixelValColor(boundryVal,x,height-y-1);
            }
        }
        for(y=diameter; y<height-diameter;y++){
            for(x=0; x<diameter;x++){
                boundryVal = getBoundryVal(x,y);
                setPixelValColor(boundryVal,x,y);
                boundryVal = getBoundryVal(width-x -1,y);
                setPixelValColor(boundryVal,width-x -1,y);
            }
        }
    }

    private int[] getBoundryVal(int xIn, int yIn){
        int val[] = new int[4];
        int xB, yB;
        for(int x = xIn - diameter ; x<= xIn + diameter; x++){
            for(int y = yIn -diameter; y<= yIn +diameter;y++){
                if(x<0) xB=0;
                else if(x>=width) xB= width-1;
                else xB =x;
                if(y<0) yB=0;
                else if(y>= height) yB= height-1;
                else yB = y;
                val[0] += (imgOriginal.getRGB(xB, yB) >> 24 & 0xff) * gaussMatrix[xIn + diameter - x][yIn + diameter - y];
                val[1] += (imgOriginal.getRGB(xB, yB) >> 16 & 0xff) * gaussMatrix[xIn + diameter - x][yIn + diameter - y];
                val[2] += (imgOriginal.getRGB(xB, yB) >> 8 & 0xff) * gaussMatrix[xIn + diameter - x][yIn + diameter - y];
                val[3] += (imgOriginal.getRGB(xB, yB) & 0xff) * gaussMatrix[xIn + diameter - x][yIn + diameter - y];
            }
        }
        for(int i=0; i<val.length; i++) val[i] /=gaussMatrixCount;
        return val;
    }

}
