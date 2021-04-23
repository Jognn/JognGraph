import javax.swing.*;
import java.awt.*;
import java.awt.geom.CubicCurve2D;


public class Plane extends JPanel {
    Dimension size;
    double dx = 0.01;
    int tileX = 128;
    int tileY = 128;


    Graphics2D g;

    public Plane(Dimension size){
        super();
        setPreferredSize(size);
    }

    @Override
    public void paintComponent(Graphics graphics) {
        this.size = getSize();
        System.out.println(size);

        this.g = (Graphics2D) graphics;
        g.setRenderingHint(
                RenderingHints.KEY_ANTIALIASING,
                RenderingHints.VALUE_ANTIALIAS_ON);
        g.translate(size.width/2,size.height/2);



        // Drawing axis
        drawAxis();
        // Drawing f(x)
        drawGraph();
        // Checking integer values
        checkValues();
    }

    public void drawAxis(){
        g.setColor(Color.BLACK);
        g.setStroke(new BasicStroke(2));

        // X axis
        g.drawLine(-size.width/2, 0, size.width/2, 0);
        g.drawString("X", size.width/2 - 15, -2);

        // Y axis
        g.drawLine(0, size.height, 0, -size.height/2);
        g.drawString("Y", 2, -size.height/2 + g.getFontMetrics().getHeight());

        // X axis lines
        for (int i = 0; i <= size.width/2; i += tileX){
            g.drawLine(i, (int)tileX/8, i, (int)-tileX/8);
            g.drawLine(-i, (int)tileX/8, -i, (int)-tileX/8);
        }

        // Y axis lines
        for (int i = 0; i <= size.height/2; i+= tileX){
            g.drawLine((int) -tileY/8, i, (int)tileY/8, i);
            g.drawLine((int) -tileY/8, -i, (int) tileY/8, -i);
        }

    }

    public double f(double x){
        return  -(Math.exp(x));
    }

    public void drawGraph(){
        g.setColor(Color.GREEN);
        g.setStroke(new BasicStroke(3));

        for(double x = -size.width*0.5; x <= size.width*0.5; x += dx ){
            double x1 = x;
            double y1 = f(x1);
            double x2 = x+3*dx;
            double y2 = f(x+3*dx);
            if (Math.abs(y2 - y1) < 1) {
                g.draw(new CubicCurve2D.Double(x1 * tileX, y1 * tileY,
                        (x1 + dx) * tileX, (y1 + dx) * tileY,
                        (x1 + 2 * dx) * tileX, (y1 + 2 * dx) * tileY,
                        x2 * tileX, y2 * tileY));
            }
        }


    }

    public void checkValues(){
        int pointSize = 6;
        g.setStroke(new BasicStroke(3, BasicStroke.CAP_ROUND, BasicStroke.JOIN_ROUND));
        g.setColor(Color.red);
        for(int x = (-size.width/2)/tileX; x < size.width/2; x++){
            g.drawOval((int)(x*tileX-pointSize/2), (int)(f(x)*tileY-pointSize/2), pointSize, pointSize);

        }
    }
}
