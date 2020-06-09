package MyImage;

import javax.swing.*;
import java.awt.*;

public class StartImage {
    public static void main(String[] args) {
        int diameter = 2;
        String url = "https://cdn.wallpaperdirect.com/shared-assets/images/products/151419home.jpg";
        MyImage img = new MyImage(url);
        GaussFilter gaussImg = new GaussFilter(diameter, img.img);
        img.createTxt();
        img.makeGreyscale();
        img.averageFilter(diameter);
        img.averageFilterColor(diameter);

        JFrame frame = new JFrame("Display image");
        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new GridLayout(3,2));
        ShowImage greyScaleDisplay = new ShowImage(img.recreatedImg, 0, 0);
        ShowImage greyScaleWithAverageFilterDisplay = new ShowImage(img.greysScaleWithAverageFilter, 0, 0);
        ShowImage colorWithAverageFilterDisplay = new ShowImage((img.colorWithAverageFilter),0,0);
        ShowImage initialDisplay = new ShowImage(img.img, 0 ,0);
        ShowImage gaussImgDisplay = new ShowImage(gaussImg.getImgWithGaussFilter(),0,0);
        mainPanel.add(greyScaleDisplay);
        mainPanel.add(greyScaleWithAverageFilterDisplay);
        mainPanel.add(initialDisplay);
        mainPanel.add(colorWithAverageFilterDisplay);
        mainPanel.add(gaussImgDisplay);
        frame.getContentPane().add(mainPanel);
        frame.setSize(img.width*2,img.height*2);
        frame.setVisible(true);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);


    }
}
