package Exe.Ex1;

import java.util.Scanner;
public class Ex1Naive {
    public static long largestPrimeDivider(long num) {
        if (num == 1 || num == 2){
            return num;
        }
        long ans = 2;
        for( long i=3; i < num / 2; i += 2){
            if(num % i == 0){
                ans = i;
            }
        }
        return ans;
    }
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        System.out.println("please insert x here: ");         //x>y
        long x = scan.nextLong();

        System.out.println("Please insert y here: ");
        long y = scan.nextLong();

        long z;
        while (y != 0) {
            z = y;
            y = x % y;
            x = z;
        }
        long ans = largestPrimeDivider(x);
        System.out.println("Your GPCD is: " +ans);
    }
}
