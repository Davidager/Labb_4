package Labb_4_package;

import javax.swing.*;
import java.awt.*;

/**
 * Created by David on 01-Dec-16.
 */
public class MyFrame extends JFrame {
    Model model;
    View view;
    public MyFrame() {
        //setLayout(new BoxLayout(getContentPane(), BoxLayout.PAGE_AXIS));
        setLayout(new BoxLayout(getContentPane(), BoxLayout.PAGE_AXIS));

        this.model = new Model(1000);  // vid 2000 - 3000 börjar det gå långsammare med endast string i saveToFile
        this.view = new View(model);    // 10000 går då inte alls, medan 10000 inga problem med stringbuilder

        Controller controller = new Controller(model, view);

        add(controller);
        add(view);

        pack();

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
    }

    public static void main(String[] args) {
        new MyFrame();

    }
}
