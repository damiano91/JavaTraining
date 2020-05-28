package MyImage;

import java.awt.*;
import java.awt.image.BufferedImage;

public class ShowImage extends Panel {
    BufferedImage image;

    ShowImage(BufferedImage img){
        image = img;
    }

    public void paint(Graphics g) {
        g.drawImage( image, 0, 0, null);
    }
}
