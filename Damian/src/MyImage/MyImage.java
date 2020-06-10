package MyImage;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URL;

public class MyImage {
    BufferedImage img;
    BufferedImage recreatedImg;
    BufferedImage greysScaleWithAverageFilter;
    BufferedImage colorWithAverageFilter;
    String txtPatch;
    int width, height;
    int diameter;

    MyImage(String urlPatch){
        img = null;
        txtPatch = "./Damian/resources/MyImage/txtImage.txt";
        try {
            URL url = new URL(urlPatch);
            img = ImageIO.read(url);
            width = img.getWidth();
            height = img.getHeight();
        }
        catch (IOException e) {
            System.out.println(e);
        }
    }

    public void createTxt(){
        byte[] myBytes = new byte[4];
        int pixelVal;
        try {
            FileOutputStream imgTxt = new FileOutputStream(txtPatch);
            for (int x = 0; x < width; x++) {
                for (int y = 0; y < height; y++) {
                    pixelVal = img.getRGB(x, y);
                    myBytes[0] = (byte) ((pixelVal >> 24) & 0xff);  //alpha
                    myBytes[1] = (byte) ((pixelVal >> 16) & 0xff);  //red
                    myBytes[2] = (byte) ((pixelVal >> 8) & 0xff);   //green
                    myBytes[3] = (byte) ((pixelVal) & 0xff);        //blue
                    imgTxt.write(myBytes);
                }
            }
        }
        catch (IOException e){
            System.out.println(e);
        }
    }

    public void makeGreyscale(){
        byte[] bytes;
        int pixelVal;
        recreatedImg = new BufferedImage(width, height, BufferedImage.TYPE_BYTE_GRAY);
        try{
            FileInputStream fis = new FileInputStream(txtPatch);
            for (int x = 0; x < width; x++) {
                for (int y = 0; y < height; y++) {
                    bytes = fis.readNBytes(4);
                    pixelVal = composePixelVal(bytes);
                    recreatedImg.setRGB(x,y,pixelVal);
                }
            }

            File ouptut = new File("./Damian/resources/MyImage/grayscale.jpg");
            ImageIO.write(recreatedImg, "jpg", ouptut);
        }
        catch (IOException e){
            System.out.println(e);
        }

    }

    private int composePixelVal(byte[] bytes){
        int[] argb = new int[4];
        int pixelVal, greyscaleVal;
        argb[0] = (int) bytes[0] & 0xFF;       //alpha
        argb[1] = ((int) bytes[1] & 0xFF);     //red
        argb[2] = ((int) bytes[2] & 0xFF);     //green
        argb[3] = ((int) bytes[3] & 0xFF);     //blue
        greyscaleVal = (argb[1] + argb[2] + argb[3])/3;
        pixelVal = argb[0];
        for(int i =0; i< 3; i++){
            pixelVal <<= 8;
            pixelVal += greyscaleVal;
        }
        return pixelVal;
    }

    public void averageFilter(int diameter){
        greysScaleWithAverageFilter = new BufferedImage(width, height, BufferedImage.TYPE_BYTE_GRAY);
        this.diameter = diameter;
        averageFilterWithoutBoundaries();
        calculateBoundries();
    }

    private void calculateBoundries() {
        int boundaryVal;
        int x;
        int y;
        for(x=0; x<width;x++){
            for(y=0; y<diameter;y++){
                boundaryVal = getBoundryVal(x,y);
                setPixelVal(boundaryVal,x,y);
                boundaryVal = getBoundryVal(x,height-y-1);
                setPixelVal(boundaryVal,x,height-y-1);
            }
        }
        for(y=diameter; y<height-diameter;y++){
            for(x=0; x<diameter;x++){
                boundaryVal = getBoundryVal(x,y);
                setPixelVal(boundaryVal,x,y);
                boundaryVal = getBoundryVal(width-x -1,y);
                setPixelVal(boundaryVal,width-x -1,y);
            }
        }
    }

    private int getBoundryVal(int xIn, int yIn){
        int val =0;
        int xB, yB;
        for(int x = xIn - diameter ; x<= xIn + diameter; x++){
            for(int y = yIn -diameter; y<= yIn +diameter;y++){
                if(x<0) xB=0;
                else if(x>=width) xB= width-1;
                else xB =x;
                if(y<0) yB=0;
                else if(y>= height) yB= height-1;
                else yB = y;
                val += (recreatedImg.getRGB(xB,yB) & 0xff);
            }
        }

        return val;
    }

    private void averageFilterWithoutBoundaries(){
        int allNeighboursVal=0;
        int x;
        int y;
        for(x =diameter; x< width -diameter;x++){
            y = diameter;
            allNeighboursVal = calculateNeighbours(x,y);
            setPixelVal(allNeighboursVal,x,y);
            for (y++; y< height - diameter; y++){
                allNeighboursVal = calculateByDiff(x,y,allNeighboursVal);
                setPixelVal(allNeighboursVal,x,y);
            }
        }
    }
    private int calculateNeighbours(int xIn, int yIn){
        int val =0;
        for(int x = xIn - diameter ; x<= xIn + diameter; x++){
            for(int y = yIn -diameter; y<= yIn +diameter;y++){
                val += (recreatedImg.getRGB(x,y) & 0xff);
            }
        }
        return val;
    }
    private int calculateByDiff(int xIn, int yIn, int val){
        for(int x= xIn-diameter; x<= xIn + diameter; x++)val -= (recreatedImg.getRGB(x,yIn-diameter-1) & 0xff);
        for(int x= xIn-diameter; x<= xIn + diameter; x++)val += (recreatedImg.getRGB(x,yIn+diameter) & 0xff);
        return val;
    }

    private void setPixelVal(int val, int x, int y){
        int pixelVal=255; //alpha to max
        int average = getAverage(val);
        pixelVal <<= 8;
        for(int i =0; i< 3; i++){
            pixelVal <<= 8;
            pixelVal += average;
        }
        greysScaleWithAverageFilter.setRGB(x,y, pixelVal);

    }

    private int getAverage(int val) {
        return  val/((diameter*2+1) * (diameter*2+1));
    }

    public void averageFilterColor(int diameter){
        colorWithAverageFilter = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
        this.diameter = diameter;
        averageFilterWithoutBoundariesColor();

    }

    private void averageFilterWithoutBoundariesColor(){
        int[] allNeighboursVal = new int[4];
        int x;
        int y;
        for(x =diameter; x< width -diameter;x++){
            y = diameter;
            allNeighboursVal = calculateNeighboursColor(x,y);
            setPixelValColor(allNeighboursVal,x,y);
            for (y++; y< height - diameter; y++){
                allNeighboursVal = calculateByDiffColor(x,y,allNeighboursVal);
                setPixelValColor(allNeighboursVal,x,y);
            }
        }
    }
    private int[] calculateNeighboursColor(int xIn, int yIn){
        int[] val = new int[4];
        for(int x = xIn - diameter ; x<= xIn + diameter; x++){
            for(int y = yIn -diameter; y<= yIn +diameter;y++){
                val[0] += (img.getRGB(x,y)>>24 & 0xff);
                val[1] += (img.getRGB(x,y)>>16 & 0xff);
                val[2] += (img.getRGB(x,y)>>8 & 0xff);
                val[3] += (img.getRGB(x,y) & 0xff);
            }
        }
        return val;
    }
    private void setPixelValColor(int[] val, int x, int y){
        int pixelVal = 0;
        int average;
        average=getAverage(val[0]);
        pixelVal += average;
        for(int i =1; i< 4; i++){
            pixelVal <<= 8;
            average=getAverage(val[i]);
            pixelVal += average;
        }
        colorWithAverageFilter.setRGB(x,y, pixelVal);

    }

    private int[] calculateByDiffColor(int xIn, int yIn, int[] val){
        for(int x= xIn-diameter; x<= xIn + diameter; x++){
            val[0] -= (img.getRGB(x,yIn-diameter-1)>>24 & 0xff);
            val[1] -= (img.getRGB(x,yIn-diameter-1)>>16 & 0xff);
            val[2] -= (img.getRGB(x,yIn-diameter-1)>>8 & 0xff);
            val[3] -= (img.getRGB(x,yIn-diameter-1) & 0xff);
        }
        for(int x= xIn-diameter; x<= xIn + diameter; x++){
            val[0] += (img.getRGB(x,yIn+diameter)>>24 & 0xff);
            val[1] += (img.getRGB(x,yIn+diameter)>>16 & 0xff);
            val[2] += (img.getRGB(x,yIn+diameter)>>8 & 0xff);
            val[3]+= (img.getRGB(x,yIn+diameter) & 0xff);
        }
        return val;
    }

}
