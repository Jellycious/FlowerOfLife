import java.awt.*;
import java.util.List;
import java.awt.geom.Ellipse2D;
import java.util.ArrayList;

public class FlowerOfLife implements GeoElement {

    private List<Shape> shapes;
    private List<FlowerOfLife> flowers;
    private double radius;
    private double centerX;
    private double centerY;

    private Color color;
    private int layers = 0;

    public FlowerOfLife(double radius, double x, double y){
        this.radius = radius;
        this.centerX = x;
        this.centerY = y;

        shapes = new ArrayList<>();
        flowers = new ArrayList<>();
        //make center
        createCircle(radius, x, y);
    }

    private void createCircle(double radius, double x, double y){
        shapes.add(new Ellipse2D.Double(x - radius, y - radius, 2.0 * radius, 2.0 * radius));
    }

    public Shape[] getShapes (){
        for (FlowerOfLife f : flowers){
            Shape[] shapes = f.getShapes();
            for (Shape s: shapes){
                this.shapes.add(s);
            }
        }

        Shape[] shapeArr = new Shape[this.shapes.size()];
        this.shapes.toArray(shapeArr);
        return shapeArr;
    }

    private void createFlower(double radius, double x, double y){
        FlowerOfLife flw = new FlowerOfLife(radius, x, y);
        flowers.add(flw);
    }

    public void grow(int amount){
        if (layers < 1){
            double interval = (2 * Math.PI) / amount;
            for (int i = 0; i < amount; i++){
                double x = Math.cos(interval * i) * radius + centerX;
                double y = Math.sin(interval * i) * radius + centerY;
                createFlower(this.radius, x, y);
            }
            layers = 1;
        }else {
            growFlowers(amount);
            layers += 1;
        }
    }

    private void growFlowers(int amount){
        for (FlowerOfLife f : flowers){
            f.grow(amount);
        }
    }

    public void outerCircle(){
        createCircle(radius * layers + radius, centerX, centerY);
    }

    @Override
    public Color getColor() {
        return this.color;
    }

    public void setColor(Color color){
        this.color = color;
    }
}
