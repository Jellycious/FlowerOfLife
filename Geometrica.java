import javax.swing.*;
import java.awt.*;

public class Geometrica {

    private JFrame frame;
    private GeoPanel panel;

    private final int width = 1920;
    private final int heigth = 1080;
    public Geometrica(){
        frame = new JFrame("Geometrica");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setBackground(Color.black);
        frame.setSize(width, heigth);
        panel = new GeoPanel();
        frame.add(panel);
        frame.setVisible(true);
        tangentLines();
        flowerOfLife();
    }

    public void flowerOfLife(){
        FlowerOfLife flw = new FlowerOfLife(40, width / 2,heigth / 2);
        flw.setColor(Color.white);
        panel.addGeoElement(flw);
        frame.repaint();

        for (int i = 0; i < 4; i++){
            sleep(500);
            flw.grow(6);
            frame.repaint();
        }
        flw.outerCircle();
    }

    public void tangentLines(){
        int numberOfLines = 100;

        animateTangentLine(200, width /2, heigth /2, new Color(255,255,255), numberOfLines,10);
//        animateTangentLine(150, width /2, heigth /2, new Color(180,180,180), numberOfLines,10);
//        animateTangentLine(100, width /2, heigth /2, new Color(88,88,88), numberOfLines,10);
//        animateTangentLine(50, width /2, heigth /2, new Color(180,180,180), numberOfLines,10);
//        animateTangentLine(10, width /2, heigth /2, new Color(255,255,255), numberOfLines,10);

    }

    private void animateTangentLine(double radius, double x, double y, Color color, int amountOfLines, int sleepMillis){
        TangentLines tl = new TangentLines(radius,x,y);
        tl.setColor(color);
        panel.addGeoElement(tl);
        frame.repaint();

        int numberOfLines = amountOfLines;
        for (int i =  0; i < numberOfLines; i++){
            sleep(sleepMillis);
            tl.addLine((2*Math.PI) / numberOfLines * i);
            frame.repaint();
        }
        frame.repaint();
    }

    private void sleep(int millis){
        try {
            Thread.sleep(millis);
        }catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
    public static void main(String[] args) {
        new Geometrica();
    }

}
