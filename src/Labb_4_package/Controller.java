package Labb_4_package;

import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;

/**
 * Created by David on 01-Dec-16.
 */
public class Controller extends JPanel implements ActionListener, ChangeListener{
    private Model model;
    private View view;

    private boolean measuring = false;
    private PrintWriter outputStream = null;

    private JSlider LSlider;
    private JSlider deltaSlider;

    private JToggleButton button;

    private Timer timer;
    private int speed = 100;
    private double time = 0;



    public Controller(Model model, View view) {
        setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));
        this.model = model;
        this.view = view;

        LSlider = new JSlider(1,5,model.getL());
        deltaSlider = new JSlider(10,200,100);

        LSlider.addChangeListener(this);
        deltaSlider.addChangeListener(this);

        JLabel sliderLLabel = new JLabel("L", JLabel.CENTER);
        JLabel sliderDeltaLabel = new JLabel("delta", JLabel.CENTER);

        button = new JToggleButton("Starta mätning");
        button.addActionListener(this);

        timer = new Timer(speed, this);
        timer.start();
        add(button);

        JPanel sliderPane = new JPanel();  //för formattering

        sliderPane.add(sliderLLabel);
        sliderPane.add(LSlider);
        sliderPane.add(deltaSlider);
        sliderPane.add(sliderDeltaLabel);

        add(sliderPane);


        try{outputStream = new PrintWriter(new FileWriter("CSVfile.txt"));
        } catch (IOException f) {}
    }

    public void stateChanged(ChangeEvent e) {
        if (e.getSource()==LSlider) {
            model.setL(LSlider.getValue());
        }

        if (e.getSource()==deltaSlider) {
            timer.setDelay(deltaSlider.getValue());
        }
    }

    public void saveToFile() {
        double[] posArray = model.getCoords();

        StringBuilder stringBuilder = new StringBuilder();

        stringBuilder.append(time/1000);

        for (double item : posArray) {
            stringBuilder.append(",");
            stringBuilder.append(item);
            stringBuilder.setLength(stringBuilder.length()-10);    //tar bort decimaler
        }

        //System.out.println(stringBuilder);

        outputStream.println(stringBuilder);
        outputStream.flush();
    }

    public void saveToFileNoSb() {   //version med "+"-operator istället för StringBuilder.
        double[] posArray = model.getCoords();

        String outString = "";

        outString = outString + (time/1000);

        for (double item : posArray) {
            outString = outString + "," + item;

            outString = outString.substring(0, outString.length() -10);    //tar bort decimaler
        }

        //System.out.println(outString);

        outputStream.println(outString);
        outputStream.flush();
    }

    public void actionPerformed(ActionEvent e) {

        if (e.getSource() == button) {
            if (measuring) {
                measuring = false;
                button.setText("Starta mätning");
            } else {
                measuring = true;
                button.setText("Stoppa mätning");
            }
        }
        if (measuring) {
            saveToFileNoSb();
        }

        time = time + timer.getDelay();

        model.updateParticles();
        view.repaint();

    }



}
