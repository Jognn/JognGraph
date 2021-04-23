import java.awt.*;
import java.awt.geom.Line2D;
import java.util.ArrayList;
import java.util.LinkedList;

public class Function {

    // TO DO: DEPEDENCY INJECTION :)

    public LinkedList<float[]> punkty;

    public Function(){
    }

    public float formula(float x){
        return (float) -(Math.exp(1/x));
    }

    public void calculatePoints(Dimension size, int tileX, int tileY){

        punkty = new LinkedList<>();

        for(float x = (float) (-size.width/2); x < (float) (size.width/2); x+= 0.1f){
            punkty.add(new float[]{x, formula(x)});
        }
    }

    public void interpolatePoints(){
        for(float[] punkt : punkty){

        }

    }


}
