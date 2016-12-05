package Labb_4_package;

import java.lang.*;
import java.util.ArrayList;
import java.util.Hashtable;

/**
 * Created by David on 30-Nov-16.
 */
public class Model {
    private int L=3;
    private int numPart;

    private double X_SIZE = 300;
    private double Y_SIZE = 300;


    private Particle[] particleArray;
    private int[] stuckArray23;
    private Hashtable divisions;


    public Model(int numPart){
        this.numPart = numPart;

        particleArray = new Particle[numPart];
        stuckArray23 = new int[numPart];

        /* skapar uppdelningen av ytan */
        divisions = new Hashtable(22);
        for (int i = 1; i<5; i++) {
            for (int j = 1; j<5; j++) {
                int key = (i*10)+j;
                divisions.put(key, new ArrayList());
            }
        }
        for (int i = 1; i < 4; i++) {
            int key = (i*10)+5;
            divisions.put(key, new ArrayList());
        }
        for (int j = 1; j < 4; j++) {
            int key = (5*10)+j;
            divisions.put(key, new ArrayList());
        }


        for (int i=0 ; i < numPart ; i++) {
            particleArray[i] = new Particle();
        }

    }

    public void updateParticles() {
        for (int i=0 ; i < numPart ; i++) {
            if (stuckArray23[i] == 0) {
                Particle item = particleArray[i];
                if (isCloseToOther(i)) {
                    ArrayList divList = (ArrayList)divisions.get(item.mainDivision);
                    divList.add(i);
                    if (item.edgeDivision != -1) {
                        ArrayList edgeDivList = (ArrayList)divisions.get(item.edgeDivision);
                        edgeDivList.add(i);
                    }

                    stuckArray23[i] = 1;
                } else {
                    item.updatePosition(i);
                }
            }
        }
    }


    public boolean isCloseToOther(int arrayIndex) {
        Particle particle = particleArray[arrayIndex];
        double x = particle.xCoord;
        double y = particle.yCoord;
        int mainDivision = particle.mainDivision;
        int edgeDivision = particle.edgeDivision;

        ArrayList<Integer> stuckMainDivisionList = (ArrayList<Integer>)divisions.get(mainDivision);

        for (int index : stuckMainDivisionList ) {
            Particle particle2 = particleArray[index];
            double x2 = particle2.xCoord;
            if (x2 < x + 1.1 && x2 > x - 1.1) {
                double y2 = particle2.yCoord;
                if (y2 < y + 1.1 && y2 > y - 1.1) {
                    if (particle != particle2) {
                        return true;
                    }
                }
            }
        }
        if (edgeDivision != -1) {
            ArrayList<Integer> stuckEdgeDivisionList = (ArrayList<Integer>)divisions.get(edgeDivision);
            for (int index : stuckEdgeDivisionList ) {
                Particle particle2 = particleArray[index];
                double x2 = particle2.xCoord;
                if (x2 < x + 1.1 && x2 > x - 1.1) {
                    double y2 = particle2.yCoord;
                    if (y2 < y + 1.1 && y2 > y - 1.1) {
                        if (particle != particle2) {
                            return true;
                        }
                    }
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
        int mainDivision;
        int edgeDivision;

        private Particle(double xCoord, double yCoord) {
            this.xCoord = xCoord;
            this.yCoord = yCoord;
            mainDivision = calculateDivision();
            edgeDivision = calculateEdgeDivision();
        }

        private Particle() {
            this(Math.random()*X_SIZE, Math.random()*Y_SIZE);
        }

        public int calculateDivision() {
            double x = this.xCoord;
            double y = this.yCoord;

            for (int i = 1; i<5; i++) {
                for (int j = 1; j<5; j++) {
                    if (X_SIZE * i / 4 > x && X_SIZE * (i - 1) / 4 < x) {
                        if (Y_SIZE * j / 4 > y && Y_SIZE * (j - 1) / 4 < y) {
                            return (i*10)+j;
                        }
                    }
                }
            }
            return 11;     // om den av någon anledning inte skulle delas in (t.ex. negativa värden)
        }

        public int calculateEdgeDivision() {
            double x = this.xCoord;
            double y = this.yCoord;
            int divLength = 4;

            for (int i = 1; i < 4; i++) {
                if (X_SIZE*i/4 > x - divLength && X_SIZE*i/4 < x + divLength) {  // om x i edgeDivision
                    return (i*10)+5;    //5 är till för att säga att det är en edgedivision
                }
            }

            for (int j = 1; j < 4; j++) {
                if (Y_SIZE*j/4 > y - divLength && Y_SIZE*j/4 < y + divLength) {  // om x i edgeDivision
                    return (5*10)+j;    //5 är till för att säga att det är en edgedivision
                }
            }
            return -1;   // om den inte har en edgedivision
        }

        public void updatePosition(int arrayIndex) {  // används aldrig på partikel som är stuck
            if (xCoord < 0.5 || yCoord < 0.5 || xCoord > 297 || yCoord > 297.5) {
                ArrayList mainDivList = (ArrayList)divisions.get(mainDivision);
                mainDivList.add(arrayIndex);
                if (edgeDivision != -1) {
                    ArrayList edgeDivList = (ArrayList)divisions.get(edgeDivision);
                    edgeDivList.add(arrayIndex);
                }
                stuckArray23[arrayIndex] = 1;
            /*} else if (((xCoord-150)*(xCoord-150))+((yCoord-150)*(yCoord-150)) > 46*46 &&
                    ((xCoord-150)*(xCoord-150))+((yCoord-150)*(yCoord-150)) < 46.5*46.5) {
                        ArrayList mainDivList = (ArrayList)divisions.get(mainDivision);
                        mainDivList.add(arrayIndex);
                        if (edgeDivision != -1) {
                            ArrayList edgeDivList = (ArrayList)divisions.get(edgeDivision);
                            edgeDivList.add(arrayIndex);
                        }*/
            } else {
                    double theta = Math.random()*2*Math.PI;
                    xCoord = xCoord + L*Math.cos(theta);
                    yCoord = yCoord + L*Math.sin(theta);
                    mainDivision = calculateDivision();
                    edgeDivision = calculateEdgeDivision();
                }

        }
    }
}
