package Labb_4_package;

import java.lang.*;

/**
 * Created by David on 30-Nov-16.
 */
public class Model {
    private int L=3;
    private int numPart;

    private Particle[] particleArray;
    private int[] stuckArray23;

    public Model(int numPart){
        this.numPart = numPart;

        particleArray = new Particle[numPart];
        stuckArray23 = new int[numPart];
        for (int i=0 ; i < numPart ; i++) {
            particleArray[i] = new Particle();
            stuckArray23[i] = 0;
        }

    }

    public void updateParticles() {
        for (int i=0 ; i < numPart ; i++) {
            Particle item = particleArray[i];
            if (stuckArray23[i] == 0) {
                if (isCloseToOther(i)) {
                    stuckArray23[i] = 1;
                }
            }

            item.updatePosition(i);
        }
    }

    public boolean isCloseToOther(int arrayIndex) {
        Particle particle = particleArray[arrayIndex];
        double x = particle.xCoord;
        double y = particle.yCoord;

        int j = 0;
        for (int i : stuckArray23) {
            if (i==1) {
                Particle particle2 = particleArray[j];
                double x2 = particle2.xCoord;
                if (x2 < x + 1.3 && x2 > x - 1.3) {
                    double y2 = particle2.yCoord;
                    if (y2 < y + 1.3 && y2 > y - 1.3) {
                        if (particle != particle2) {
                            return true;
                        }
                    }
                }
            }
            j++;
        }
        return false;
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

    public int[] getStuckState() {   // det som färgar partiklarna
        return stuckArray23;
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
            this(Math.random()*300, Math.random()*300);
        }

        public void updatePosition(int arrayIndex) {
            if (stuckArray23[arrayIndex] == 1) {

            } else if (xCoord < 0.5 || yCoord < 0.5 || xCoord > 297 || yCoord > 297.5) {
                stuckArray23[arrayIndex] = 1;
            } else {
                    double theta = Math.random()*2*Math.PI;
                    xCoord = xCoord + L*Math.cos(theta);
                    yCoord = yCoord + L*Math.sin(theta);
            }

        }
    }
}
