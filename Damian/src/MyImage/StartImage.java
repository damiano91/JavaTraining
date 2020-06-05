package MyImage;

import javax.swing.*;
import java.awt.*;

public class StartImage {
    public static void main(String[] args) {
        int diameter = 1;
        String url = "https://cdn.wallpaperdirect.com/shared-assets/images/products/151419home.jpg";
        MyImage img = new MyImage(url);
        img.createTxt();
        img.makeGreyscale();
        img.averageFilter(diameter);
        img.averageFilterColor(diameter);

        JFrame frame = new JFrame("Display image");
        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new GridLayout(2,2));
        ShowImage greyScale = new ShowImage(img.recreatedImg, 0, 0);
        ShowImage greyScaleWithAverageFilter = new ShowImage(img.greysScaleWithAverageFilter, 0, 0);
        ShowImage colorWithAverageFilter = new ShowImage((img.colorWithAverageFilter),0,0);
        ShowImage initial = new ShowImage(img.img, 0 ,0);
        mainPanel.add(greyScale);
        mainPanel.add(greyScaleWithAverageFilter);
        mainPanel.add(initial);
        mainPanel.add(colorWithAverageFilter);
        frame.getContentPane().add(mainPanel);
        frame.setSize(img.width*2,img.height*2);
        frame.setVisible(true);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);


    }
}
