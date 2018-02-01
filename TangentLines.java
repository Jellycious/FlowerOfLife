
import java.awt.*;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Line2D;
import java.util.ArrayList;
import java.util.List;

public class TangentLines implements GeoElement {

    private double centerX;
    private double centerY;
    private double radius;

    private List<Shape> shapeList;

    private Color color;

    public TangentLines(double radius, double x, double y){
        this.centerX = x;
        this.centerY = y;
        this.radius = radius;
        shapeList = new ArrayList<>();
        shapeList.add(new Ellipse2D.Double(x - radius, y - radius, 2.0 * radius, 2.0 * radius));
        color = Color.white;
    }

    @Override
    public Shape[] getShapes() {
        Shape[] shapeArr =  new Shape[shapeList.size()];
        shapeList.toArray(shapeArr);
        return shapeArr;
    }

    public void addLine(double position){
         double x = Math.cos(position) * radius + centerX;
         double y = Math.sin(position) * radius + centerY;

         double yTan = 1 / Math.sin(position) * 1000;
         double xTan = 1 / Math.cos(position) * 1000;
         shapeList.add(new Line2D.Double(x - xTan, y + yTan, x + xTan, y - yTan));
    }

    @Override
    public Color getColor() {
        return color;
    }

    public void setColor(Color color){
        this.color = color;
    }
}
