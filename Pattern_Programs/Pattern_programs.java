package Pattern_Programs;

import java.util.Scanner;

public class Pattern_programs {
    public static void main(String[] args) {
        Scanner sc=new Scanner(System.in);
        System.out.println("Enter the no. of rows: ");
        int n=sc.nextInt();

        //RAT
        for (int i = 0; i < n; i++) {
            for (int j = 0; j <= i; j++) {
                System.out.print("*"+" ");
            }
            System.out.println();

        }
        System.out.println();

        //2nd program
        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= i; j++) {
                System.out.print(j+" ");
            }
            System.out.println();
        }

        //3rd Program
        System.out.println();
        for (int i = 0; i < n; i++) {
            for (int j = n; j > i; j--) {
                System.out.print("* ");
            }
            System.out.println();
        }
        System.out.println();

        //4th program
        for (int i = 0; i < n; i++) {
            for (int j = n; j > i; j--) {
                System.out.print("  ");
            }
//            for (int j = 0; j <= i; j++) {
//                System.out.print("* ");
//            }
//            for (int j = 0; j < i; j++) {
//                System.out.print("* ");
//            }
            for (int j = 0; j < 2*i+1; j++) {
                System.out.print("* ");
            }
            System.out.println();
        }
        System.out.println();

        int count=1;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j <= i; j++) {
                System.out.print(count+" ");
                count++;
            }
            System.out.println();
        }



    }
}
