import javax.swing.*;
import java.awt.*;

public class Main extends JFrame {
    Plane plane;

    public Main(){
        super("JognGraph");
        plane = new Plane(new Dimension(800, 600));

        add(plane);
        setSize(800, 600);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setVisible(true);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                new Main();
            }
        });
    }
}
