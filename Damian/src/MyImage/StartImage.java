package MyImage;

import javax.swing.*;
import java.awt.*;

public class StartImage {
    public static void main(String[] args) {
        int diameter = 3;
        String url = "https://cdn.wallpaperdirect.com/shared-assets/images/products/151419home.jpg";
        MyImage img = new MyImage(url);
        img.createTxt();
        img.makeGreyscale();
        img.averageFilter(diameter);

        JFrame frame = new JFrame("Display image");
        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new GridLayout(1,2));
        ShowImage greyScale = new ShowImage(img.recreatedImg, 0, 0);
        ShowImage greyScaleWithAverageFilter = new ShowImage(img.greysScaleWithAverageFilter, img.width, 0);
        mainPanel.add(greyScale);
        mainPanel.add(greyScaleWithAverageFilter);
        frame.getContentPane().add(mainPanel);
        frame.setSize(img.width*2,img.height);
        frame.setVisible(true);


    }
}
