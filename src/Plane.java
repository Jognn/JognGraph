import javax.swing.*;
import java.awt.*;
import java.awt.geom.Line2D;

public class Plane extends JPanel {
    Dimension size;
    double scaleX = 1;
    double scaleY = 1;
    float dx = 0.1f;
    int tileX = 24;
    int tileY = 24;
    float minValueX = -5;
    float maxValueX = 5;

    public Plane(Dimension size){
        super();
        setPreferredSize(size);
    }

    @Override
    public void paintComponent(Graphics graphics) {
        Graphics2D g = (Graphics2D) graphics;
        this.size = getSize();
        System.out.println(size);

        g.translate(size.width/2,size.height/2);

        // Drawing axis
        drawAxis(g);
        // Drawing f(x)
        drawGraph(g);
        // Checking integer values
        checkValues(g);
    }

    public void drawAxis(Graphics2D g){
        g.setColor(Color.BLACK);
        g.setStroke(new BasicStroke(2));

        // X axis
        g.drawLine(-size.width/2, 0, size.width/2, 0);
        g.drawString("X", size.width/2 - 15, -2);

        // Y axis
        g.drawLine(0, size.height+1/2, 0, -size.height/2);
        g.drawString("Y", 2, -size.height/2 + g.getFontMetrics().getHeight());

        // X axis lines
        for (int i = 0; i <= size.width/2; i += tileX){
            g.drawLine(i, (int)tileX/4, i, (int)-tileX/4);
            g.drawLine(-i, (int)tileX/4, -i, (int)-tileX/4);
        }

        // Y axis lines
        for (int i = 0; i <= size.height/2; i+= tileX){
            g.drawLine((int) -tileY/4, i, (int)tileY/4, i);
            g.drawLine((int) -tileY/4, -i, (int) tileY/4, -i);
        }

    }

    public float f(float x){
        return (float) -((Math.pow(2, x)));
    }

    public void drawGraph(Graphics2D g){
        g.setColor(Color.BLUE);
        for(float x = (-size.width/2)/tileX; x < size.width/2; x+= dx){
            g.draw(new Line2D.Float(x*tileX, f(x), (x+dx)*tileX, f(x+dx)));
        }
        g.setColor(Color.ORANGE);
        g.setStroke(new BasicStroke(5));
        g.draw(new Line2D.Float(0,0,tileX,0));
        g.draw(new Line2D.Float(0,0,0, -tileY));
        // S
    }

    public void checkValues(Graphics2D g){
        int pointSize = 6;
        g.setStroke(new BasicStroke(3));
        g.setColor(Color.red);
        for(int x = (-size.width/2)/tileX; x < size.width/2; x++){
            g.drawOval((int)(x*tileX-pointSize/2), (int)(f(x)-pointSize/2), pointSize, pointSize);

        }
    }
}
