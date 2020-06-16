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
        makeGreyscale();
        sobelFilter = new SobelFilter(greyScaleImg);
        sobelImg = sobelFilter.getImage();
        thetaMax = 360;
        diagonal = (int) Math.sqrt(width*width + height*height);
        houghMatrix = new int[thetaMax][diagonal];
    }

    private void makeGreyscale(){
        greyScaleImg = new BufferedImage(width, height, BufferedImage.TYPE_BYTE_GRAY);
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
        hughImg = new BufferedImage(diagonal,thetaMax,BufferedImage.TYPE_BYTE_GRAY);
        for(int x=0; x< width;x++) {
            for (int y = 0; y < height; y++) {
                if((sobelImg.getRGB(x,y) & 0xff) == 255){
                    addPoints(x,y);
                }
            }
        }
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
            r = (int)(x*Math.cos(Math.toRadians(theta)) + y*Math.sin(Math.toRadians(theta)));
            if(r>0)houghMatrix[theta][r]++;
            else houghMatrix[theta][r*(-1)]++;

        }
    }

    public void print2MostProminentPoints(){
        int[] max1, max2;
        max1 = new int[3];
        max2 = new int[3];
        for(int x=0; x< thetaMax;x++) {
            for (int y = 0; y < diagonal; y++) {
                if(houghMatrix[x][y] > max1[2]){
                    System.arraycopy(max1,0,max2,0,3);
                    max1[0] = x;
                    max1[1] = y;
                    max1[2] = houghMatrix[x][y];
                }
                else if (houghMatrix[x][y] > max2[2]){
                    max2[0] = x;
                    max2[1] = y;
                    max2[2] = houghMatrix[x][y];

                }
            }
        }
        drawLine(max1[0], max1[1]);
        drawLine(max2[0], max2[1]);
        System.out.println(max1[0] + " " + (max1[1]));
        System.out.println(max2[0] + " " + (max2[1]));
    }

    private void drawLine(int mtheta, int mdiagonal){
        int y;
        for(int x = 0; x< width;x++){
            y = (int) ((mdiagonal - x*Math.cos(Math.toRadians(mtheta)))/ Math.sin(Math.toRadians(mtheta)));
            if(y<height && y >0) img.setRGB(x,y,255<<16);
        }
    }
}
