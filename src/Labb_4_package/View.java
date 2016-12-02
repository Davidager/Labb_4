package Labb_4_package;


import java.awt.*;
import java.awt.geom.Ellipse2D;
import javax.swing.*;

/**
 * Created by David on 30-Nov-16.
 */
public class View extends JPanel {
    private Model model;

    private int X_SIZE = 300;
    private int Y_SIZE = 300;

    public View (Model model) {
        this.model = model;

        setPreferredSize(new Dimension(X_SIZE,Y_SIZE));

    }

    public void paint(Graphics g) {
        Graphics2D g2 = (Graphics2D)g;
        double[] posArray = model.getCoords();
        int [] stuckArray = model.getStuckState();

        g2.clearRect(0,0,X_SIZE,Y_SIZE);

        for (int i = 0 ; i<posArray.length; i+=2) {
            Ellipse2D.Double circle = new Ellipse2D.Double(posArray[i], posArray[i+1], 1, 1);

            if (stuckArray[i/2] == 1 && g2.getColor().equals(Color.BLACK)) {
                g2.setColor(Color.RED);
            }  else if (stuckArray[i/2] == 0 && g2.getColor().equals(Color.RED)) {
                g2.setColor(Color.BLACK);
            }

            g2.fill(circle);
        }
    }
}
