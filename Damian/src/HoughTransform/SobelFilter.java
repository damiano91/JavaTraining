package HoughTransform;

import java.awt.image.BufferedImage;

public class SobelFilter {
    BufferedImage greyScaleImg;
    BufferedImage sobelImg;
    int[][] sobelValMatrix;
    int width, height;
    int threshold;

    public SobelFilter(BufferedImage img){
        greyScaleImg = img;
        width = img.getWidth();
        height = img.getHeight();
        sobelValMatrix = new int [width][height];
        sobelImg = new BufferedImage(width, height, BufferedImage.TYPE_BYTE_GRAY);
        makeSobelMatrix();
        createImg();
    }

    public BufferedImage getImage(){
        return sobelImg;
    }

    private void makeSobelMatrix(){
        for(int x =1; x< width-1; x++){
            for(int y = 1; y< height -1; y++){
                sobelValMatrix[x][y] = getSobelVal(x,y);
            }
        }
    }
    private void createImg(){
        //calculateThreshodlBasedOnMaxVal();
        calculateThresholdBasedOnAvgIlluminance();
        for(int x =0; x< width; x++){
            for(int y = 0; y< height; y++){
                if(sobelValMatrix[x][y] > threshold) sobelValMatrix[x][y]=255;
                else sobelValMatrix[x][y] =0;
                sobelImg.setRGB(x,y,HoughTransform.composeGreyPixel(sobelValMatrix[x][y]));
            }
        }
    }

    private int getSobelVal(int xIn, int yIn){
        int sobelVal=0, sobelXval=0, sobelYval=0;
        int[][] sobelXMatrix = {{1,0,-1},{2,0,-2},{1,0,-1}};
        int[][] sobelYMatrix = {{1,2,1},{0,0,0},{-1,-2,-1}};
        for(int i = 0; i<3; i++){
            for(int j = 0; j<3; j++){
                sobelXval += (greyScaleImg.getRGB(xIn+i-1,yIn+j-1)&0xff) * sobelXMatrix[i][j];
                sobelYval += (greyScaleImg.getRGB(xIn+i-1,yIn+j-1)&0xff) * sobelYMatrix[i][j];
            }
        }
        sobelVal = (int) Math.sqrt(sobelXval*sobelXval + sobelYval*sobelYval);
        //if(sobelVal>400) sobelVal=255;
        //else sobelVal = 0;

        return sobelVal;
    }

    private void calculateThreshodlBasedOnMaxVal(){
        float modifier = 0.5f;
        int max=0;
        for(int i =0; i< width;i++){
            for(int j=0; j<height;j++){
                if(sobelValMatrix[i][j] > max) max = sobelValMatrix[i][j];
            }
        }
        threshold = (int) (max*modifier);
    }

    private void calculateThresholdBasedOnAvgIlluminance(){
        float modifier = 2.0f;
        int sum=0;
        for(int i =0; i< width;i++){
            for(int j=0; j<height;j++){
                sum += sobelValMatrix[i][j];
            }
        }
        threshold = (int) ((sum/(width*height))*modifier);

    }
}
