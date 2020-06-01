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
        //calculateBoundries();
    }

    private void calculateBoundries() {
        int allNeighboursVal;
        int x;
        int y;
    }

    private void averageFilterWithoutBoundaries(){
        int allNeighboursVal=0;
        int x;
        int y;
        for(x =diameter; x< width -diameter;x++){
            y = diameter;
            allNeighboursVal = calculateNeighbours(x,y);
            setPixelVal(allNeighboursVal,x,y);
            for (y = diameter+1; y< height - diameter; y++){
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

}

