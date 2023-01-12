package Exe.Ex1;
import java.util.Scanner;
public class Ex1 {
    public static long largestPrimeDivider(long num) {
        // first we deal with the possibility that the GCD is already 1 which is prime and there is no point going through the all function
        if (num == 1) {
            return num;
        }
        long ans = num;
        //we want to start checking for primes at 2, because that's the next one after 1
        //we want it to stop when i * i > num because we've passed the half way point.
        //Note: i*i is less computationally expensive than square rooting num
        //We will want to have i go 3,5,7 etc so we += 2 (we sort out the going from 2 -> 3 later)
        for (long i = 2; i * i <= ans; i += 2) {
            // while it's divisible by i, lets do so.
            // This gives us the capability to go from something like 24 -> 12 -> 6 -> 3
            while (ans % i == 0) {
                if (ans / i > 1) {
                    ans /= i;
                }
                // unless it gives us 1, then we know we have a number dividing by itself which is the answer.
                else {
                    return i;
                }
            }
            // now we take care of the case when we've done the loop once and i is 2, we want it to be 3 next and we're going to add 2, so lets minus 1
            if (i == 2) i--;
        }
        //we have broken out of the loop, that means that i*i > .num
        //We know that increasing i won't get us any further because i*j is the same as j*i
        //we know that num is a factor of the original number because we got there by dividing it by i
        //we know that num is the largest because we've checked all the numbers up to i and it never divided to give us 1

        return ans;
    }

        public static void main (String[] args){
            Scanner scan = new Scanner(System.in);
            System.out.println("please insert x here: ");         //Scanning for x while we know that x is the bigger integer of the 2 inputs .x>y
            long x = scan.nextLong();

            System.out.println("Please insert y here: ");        // Scanning for y . x>y
            long y = scan.nextLong();                            // Setting both inputs as long-type so that we will be able to take in large numbers.

            long z;
            while (y != 0) {                                     // This is an implementation of euclid's algorithm for finding the gcd between two numbers.
                z  = y;                                           // Basically we take the bigger integer and divide it by the smaller one.
                y = x % y;                                       // Then we take the small integer and replace the big integer with it, and then we take the remainder of the division
                x = z;                                           // and replace the smaller number with it, and we continue doing this division until y=0 and then we know that x=GCD.
            }
            long ans = largestPrimeDivider(x);                  // we now run the GCD we got through the function for finding the Greatest prime factor of a number
            System.out.println("Your GPCD is: " + ans);         // we know that the GCD if not already prime has a factor which is prime and it if it's a factor for thr gcd it will be a factor for x,y
        }

    }




