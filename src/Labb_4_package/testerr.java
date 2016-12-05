package Labb_4_package;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Hashtable;
import java.util.Stack;

/**
 * Created by David on 30-Nov-16.
 */
public class testerr {
    public static void main(String[] args) {
        Hashtable divisions = new Hashtable(16);
        divisions.put(42,new ArrayList());
        ArrayList<Integer> list = (ArrayList)divisions.get(42);
        list.add(25);
        list.add(74);
        list.add(57);
        list.add(33);
        list.add(98);

        for (int i : (ArrayList<Integer>)divisions.get(42)) {
            System.out.println(i/10);
            System.out.println(i%10);
        }

    }
}
