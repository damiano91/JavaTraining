package Image;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URL;

public class Image {
    BufferedImage img;
    BufferedImage recreatedImg;
    String txtPatch;
    int width, height;

    Image(String urlPatch){
        img = null;
        txtPatch = "./Damian/resources/Image/txtImage.txt";
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
        Color color;
        byte[] myBytes = new byte[4];
        try {
            FileOutputStream imgTxt = new FileOutputStream(txtPatch);
            for (int x = 0; x < width; x++) {
                for (int y = 0; y < height; y++) {
                    color = new Color(img.getRGB(x, y), true);
                    myBytes[0] = (byte) color.getAlpha();
                    myBytes[1] = (byte) color.getRed();
                    myBytes[2] = (byte) color.getGreen();
                    myBytes[3] = (byte) color.getBlue();
                    imgTxt.write(myBytes);
                }
            }
        }
        catch (IOException e){
        }
    }

    public void makeGreyscale(){
        Color color;
        byte[] bytes;
        int greyScaleVal;
        recreatedImg = new BufferedImage(width, height, BufferedImage.TYPE_BYTE_GRAY);
        try{
            FileInputStream fis = new FileInputStream(txtPatch);
            for (int x = 0; x < width; x++) {
                for (int y = 0; y < height; y++) {
                    bytes = fis.readNBytes(4);
                    greyScaleVal = getARGB(bytes);
                    color = new Color(greyScaleVal,greyScaleVal,greyScaleVal);
                    recreatedImg.setRGB(x,y,color.getRGB());
                }
            }

            File ouptut = new File("./Damian/resources/Image/grayscale.jpg");
            ImageIO.write(recreatedImg, "jpg", ouptut);
        }
        catch (IOException e){
        }

    }

    private int getARGB(byte[] bytes){
        int[] argb = new int[4];
        argb[1] = (int)(((int) bytes[1] & 0xFF)* 0.299);
        argb[2] = (int)(((int) bytes[2] & 0xFF)* 0.587);
        argb[3] = (int)(((int) bytes[3] & 0xFF)* 0.114);
        argb[0] = argb[1] + argb[2] + argb[3];
        return argb[0];
    }
}

