package tirgul_05;
import java.util.*;
public class mat_ex3 {
    public static void main(String[] args) {
            Double[] someNumbers = { 2.0, 3.0, 1.0, 0.5 };
            String s = mat_ex3.printPoly(someNumbers);
            System.out.println(s);
        }

        public static String printPoly(Double[] doubles) {

            String polynomialString = "";
            int backwardsCounter = doubles.length - 1;

            for (int i = 0; i < doubles.length; i++) {
                polynomialString += doubles[i] + "x^" + backwardsCounter + "+";
                backwardsCounter --;
            }

            return polynomialString;

        }
}

