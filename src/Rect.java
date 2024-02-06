import java.awt.Graphics2D;
import java.awt.Color;
import java.awt.geom.Rectangle2D;

public class Rect {
    public double x, y, width, height;
    public Color colour;

    public Rect(double x, double y, double width, double height, Color colour) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        this.colour = colour;
    }

    public void draw(Graphics2D g2) {
        g2.setColor(colour);
        g2.fill(new Rectangle2D.Double(x, y, width, height));
    }
}
