package MyImage;

import java.awt.*;
import java.awt.image.BufferedImage;

public class ShowImage extends Panel {
    BufferedImage image;
    int x, y;

    ShowImage(BufferedImage img, int x, int y){
        this.x = x;
        this.y = y;
        image = img;
    }

    public void paint(Graphics g) {
        g.drawImage( image, 0, 0, null);
    }
}
