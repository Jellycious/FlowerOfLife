import javax.swing.*;
import java.awt.*;
import java.awt.geom.Ellipse2D;
import java.util.ArrayList;
import java.util.List;

public class GeoPanel extends JPanel{
    
    private List<GeoElement> geoElements;

    public  GeoPanel(){
        geoElements = new ArrayList<>();
    }
    public void paintComponent(Graphics g) {
        Graphics2D g2 = (Graphics2D) g;
        g2.setColor(Color.white);
        for (GeoElement e : geoElements){
            g2.setColor(e.getColor());
            for (Shape s : e.getShapes()){
                g2.draw(s);
            }
        }
    }

    public void addGeoElement(GeoElement geoElement){
        geoElements.add(geoElement);
    }

    public Shape getCircle(double radius, double x, double y){
        return new Ellipse2D.Double(x - radius, y - radius, 2.0 * radius, 2.0 * radius);
    }
}
