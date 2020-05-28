package MyImage;

import javax.swing.*;

public class StartImage {
    public static void main(String[] args) {
        String url = "https://cdn.wallpaperdirect.com/shared-assets/images/products/151419home.jpg";
        MyImage img = new MyImage(url);
        img.createTxt();
        img.makeGreyscale();

        JFrame frame = new JFrame("Display image");
        ShowImage panel = new ShowImage(img.recreatedImg);
        frame.getContentPane().add(panel);
        frame.setSize(img.width,img.height);
        frame.setVisible(true);
    }
}
