package Labb_4_package;


import java.awt.*;
import java.awt.geom.Ellipse2D;
import javax.swing.*;

/**
 * Created by David on 30-Nov-16.
 */
public class View extends JPanel {
    private Model model;

    private int X_SIZE = 600;
    private int Y_SIZE = 600;

    public View (Model model) {
        this.model = model;
        setPreferredSize(new Dimension(X_SIZE,Y_SIZE));

    }

    public void paint(Graphics g) {
        Graphics2D g2 = (Graphics2D)g;
        double[] posArray = model.getCoords();

        g2.clearRect(0,0,X_SIZE,Y_SIZE);

        for (int i = 0 ; i<posArray.length; i+=2) {
            //g2.draw(new Ellipse2D.Double(posArray[i], posArray[i+1], 7, 7));
            Ellipse2D.Double circle = new Ellipse2D.Double(posArray[i], posArray[i+1], 4, 4);
            g2.fill(circle);
        }
    }
}
