package HoughTransform;
import MyImage.ShowImage;

import javax.swing.*;
import java.awt.*;

public class StartHoughTransform {
    public static void main(String[] args) {
        //String url = "http://analizaobrazu.x25.pl/uploads/ckeditor/pictures/367/content_3.png";
        //String url = "https://i.wpimg.pl/730x0/m.komorkomania.pl/vivo-nex-3s-5g-10-1d043db8c90500.jpg";
        //String url = "https://bi.im-g.pl/im/ba/a9/13/z20618938MS,Figura.jpg";
        //String url = "https://www.gimp.org/tutorials/Straight_Line/straight_line_example.png";
        String url = "https://encrypted-tbn0.gstatic.com/images?q=tbn%3AANd9GcSk8Z6BUlysKf6kbMawNVcLB-JvMOiofY2Deg&usqp=CAU";
        HoughTransform houghImage = new HoughTransform(url);
        houghImage.makeHughTransform();
        houghImage.print2mostProminentLines();
        //houghImage.print2MostProminentPoints();

        JFrame frame = new JFrame("Display image");
        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new GridLayout(2,2));
        ShowImage originalDisplay = new ShowImage(houghImage.img, 0, 0);
        ShowImage greyScaleDisplay = new ShowImage(houghImage.greyScaleImg, 0, 0);
        ShowImage edgesDisplay = new ShowImage(houghImage.sobelImg, 0, 0);
        mainPanel.add(originalDisplay);
        mainPanel.add(greyScaleDisplay);
        mainPanel.add(edgesDisplay);
        frame.getContentPane().add(mainPanel);
        frame.setSize(houghImage.width * 2, houghImage.height*2);
        frame.setVisible(true);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);


        JFrame frameHugh = new JFrame("Display Hugh");
        JPanel mainPanelHugh = new JPanel();
        mainPanelHugh.setLayout(new GridLayout(1,1));
        ShowImage hughDisplay = new ShowImage(houghImage.hughImg, 0, 0);
        mainPanelHugh.add(hughDisplay);
        frameHugh.getContentPane().add(mainPanelHugh);
        frameHugh.setSize(houghImage.width, houghImage.height);
        frameHugh.setVisible(true);
        frameHugh.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

    }
}
