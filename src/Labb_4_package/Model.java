package Labb_4_package;

import java.lang.*;

/**
 * Created by David on 30-Nov-16.
 */
public class Model {
    private int L=3;
    private Particle[] particleArray;

    public Model(int numPart){
        particleArray = new Particle[numPart];
        for (int i=0 ; i < numPart ; i++) {
             particleArray[i] = new Particle();
        }
    }

    public void updateParticles() {
        for (Particle item : particleArray) {
            item.updatePosition();
        }
    }
    public double[] getCoords() {   //hämtar koordinaterna för alla partiklar och skickar tillbaka som array
        double[] posArray = new double[particleArray.length*2];
        int i = 0;
        for (Particle item : particleArray) {
            posArray[i] = item.xCoord;
            i++;
            posArray[i] = item.yCoord;
            i++;
        }
        return posArray;
    }

    public void setL(int L) {
        this.L = L;
    }

    public int getL() {
        return L;
    }

    private class Particle {
        double xCoord;
        double yCoord;

        private Particle(double xCoord, double yCoord) {
            this.xCoord = xCoord;
            this.yCoord = yCoord;
        }

        private Particle() {
            this(Math.random()*600, Math.random()*600);
        }

        public void updatePosition() {
            double theta = Math.random()*2*Math.PI;
            xCoord = xCoord + L*Math.cos(theta);
            yCoord = yCoord + L*Math.sin(theta);
        }
    }
}
