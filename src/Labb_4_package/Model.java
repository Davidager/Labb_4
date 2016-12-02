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
            if (isCloseToOther(item)) {
                item.stuckState = 1;

            }
            item.updatePosition();
        }
    }

    public boolean isCloseToOther(Particle particle) {
        int[] stuckArray = getStuckState();
        double x = particle.xCoord;
        double y = particle.yCoord;
        //System.out.println("yo");
        for (int i : stuckArray) {
            if (i==1) {
                if (Math.hypot(x-particleArray[i].xCoord, y-particleArray[i].yCoord) < 1) {
                    return true;
                }
            }
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
        int[] stuckArray = new int[particleArray.length];
        int i = 0;
        for (Particle item : particleArray) {
            stuckArray[i] = item.stuckState;
            i++;
        }
        return stuckArray;
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
        int stuckState = 0;

        private Particle(double xCoord, double yCoord) {
            this.xCoord = xCoord;
            this.yCoord = yCoord;
        }

        private Particle() {
            this(Math.random()*300, Math.random()*300);
        }

        public void updatePosition() {
            if (stuckState == 1) {

            } else if (xCoord < 0.5 || yCoord < 0.5 || xCoord > 297 || yCoord > 297.5) {
                stuckState = 1;
            } else {
                    double theta = Math.random()*2*Math.PI;
                    xCoord = xCoord + L*Math.cos(theta);
                    yCoord = yCoord + L*Math.sin(theta);
            }

        }
    }
}
