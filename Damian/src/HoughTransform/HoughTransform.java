package HoughTransform;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.URL;

public class HoughTransform {
    BufferedImage img;
    BufferedImage greyScaleImg;
    BufferedImage sobelImg;
    BufferedImage hughImg;
    SobelFilter sobelFilter;
    int width, height;
    int thetaMax;
    int diagonal ;
    int[][] houghMatrix;



    HoughTransform(String urlPatch){
        img = null;
        try {
            URL url = new URL(urlPatch);
            img = ImageIO.read(url);
            width = img.getWidth();
            height = img.getHeight();
        }
        catch (IOException e) {
            System.out.println(e);
        }
        greyScaleImg = new BufferedImage(width, height, BufferedImage.TYPE_BYTE_GRAY);
        makeGreyscale();
        sobelFilter = new SobelFilter(greyScaleImg);
        sobelImg = sobelFilter.getImage();
        thetaMax = 180;
        diagonal = (int) Math.sqrt(width*width + height*height)*2;
        hughImg = new BufferedImage(diagonal,thetaMax,BufferedImage.TYPE_BYTE_GRAY);
        houghMatrix = new int[thetaMax][diagonal];
    }

    private void makeGreyscale(){
        int point, avg;
        int[] argb = new int[4];
        for(int x=0;x<width;x++){
            for(int y=0; y<height;y++){
                point = img.getRGB(x,y);
                argb[0] = (point>>24) & 0xff;
                argb[1] = (point>>16) & 0xff;
                argb[2] = (point>>8) & 0xff;
                argb[3] = (point) & 0xff;
                avg = (argb[1]+argb[2]+argb[3])/3;
                greyScaleImg.setRGB(x,y,composeGreyPixel(avg));
            }
        }
    }

    public static int composeGreyPixel(int val){
        int pixelVal = 255;
        for(int i =0; i< 3; i++){
            pixelVal <<= 8;
            pixelVal += val;
        }
        return pixelVal;
    }

    public void makeHughTransform(){
        for(int x=0; x< width;x++) {
            for (int y = 0; y < height; y++) {
                if(sobelFilter.sobelValMatrix[x][y] == 255){
                    addPoints(x,y);
                }
            }
        }
        printHoughMatrix();
    }

    private void printHoughMatrix(){
        int max =0;
        for(int x=0; x< thetaMax;x++) {
            for (int y = 0; y < diagonal; y++) {
                if(houghMatrix[x][y] > max) max = houghMatrix[x][y];
            }
        }
        for(int x=0; x< thetaMax;x++) {
            for (int y = 0; y < diagonal; y++) {
                hughImg.setRGB(y,x,HoughTransform.composeGreyPixel((int)(((float) (houghMatrix[x][y])/max) * 255)));
            }
        }

    }
    private void addPoints(int x, int y){
        int r=0;
        for(int theta=0; theta<thetaMax;theta++){
            r = (int)(x*Math.cos(Math.toRadians(theta)) + y*Math.sin(Math.toRadians(theta))) + diagonal/2;
            houghMatrix[theta][r]++;
        }
    }

    private void drawLine(int mtheta, int mdiagonal){
        int y;
        int normalizedDiagonal =mdiagonal -diagonal/2;
        for(int x = 0; x< width;x++){
            y = (int) ((normalizedDiagonal - x*Math.cos(Math.toRadians(mtheta)))/ Math.sin(Math.toRadians(mtheta)));
            if(y<height && y >0) img.setRGB(x,y,255<<16);
        }
    }

    public void print2mostProminentLines(){
        XYPoints maximaPoints = findLocalMaxima();
        int[] max1, max2;
        max1 = new int[3];
        max2 = new int[3];
        for(int i = 0; i<maximaPoints.count; i++){
            if(houghMatrix[maximaPoints.x[i]][maximaPoints.y[i]] > max1[2]){
                System.arraycopy(max1,0,max2,0,3);
                max1[0] = maximaPoints.x[i];
                max1[1] = maximaPoints.y[i];
                max1[2] = houghMatrix[maximaPoints.x[i]][maximaPoints.y[i]];
            }
            else if(houghMatrix[maximaPoints.x[i]][maximaPoints.y[i]] > max2[2]) {
                max2[0] = maximaPoints.x[i];
                max2[1] = maximaPoints.y[i];
                max2[2] = houghMatrix[maximaPoints.x[i]][maximaPoints.y[i]];
            }
        }
        drawLine(max1[0], max1[1]);
        drawLine(max2[0], max2[1]);
        System.out.println(max1[0] + " " + (max1[1] - diagonal/2));
        System.out.println(max2[0] + " " + (max2[1] - diagonal/2));
    }

    private XYPoints findLocalMaxima(){
        XYPoints maxXYPoints = new XYPoints();
        for(int x =1; x<houghMatrix.length-1; x++){
            for(int y =1; y< houghMatrix[0].length-1; y++){
                if(houghMatrix[x][y] > houghMatrix[x-1][y-1]
                && houghMatrix[x][y] > houghMatrix[x-1][y]
                && houghMatrix[x][y] > houghMatrix[x][y-1]
                && houghMatrix[x][y] > houghMatrix[x+1][y-1]
                && houghMatrix[x][y] > houghMatrix[x-1][y+1]
                && houghMatrix[x][y] > houghMatrix[x+1][y+1]
                && houghMatrix[x][y] > houghMatrix[x+1][y]
                && houghMatrix[x][y] > houghMatrix[x][y+1]){
                    maxXYPoints.addPoint(x,y);
                }
            }
        }
        return maxXYPoints;
    }
}
