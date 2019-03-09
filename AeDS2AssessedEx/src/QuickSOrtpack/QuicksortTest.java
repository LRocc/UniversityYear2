package QuickSOrtpack;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class QuicksortTest
{
    public static void main(String[] args) throws FileNotFoundException {

            ArrayList<Integer> times = new ArrayList<Integer>();
            //for (int f = 0; f < 100; f++) {

                Quicksort qsort = new Quicksort();
                int[] array = {5, 4, 7, 2, 1, 9, 300, 6, 10, 8};

                System.out.print("Original  Array : ");
                for (int i = 0; i < array.length; i++) {
                    System.out.print(array[i] + " ");
                }

                int length = array.length;
                long timeA = System.nanoTime();
                qsort.qsort(array, 0, length - 1);

                System.out.print("Sorted  Array : ");
                for (int i = 0; i < array.length; i++) {
                    System.out.print(array[i] + " ");
                }
                long timeB = System.nanoTime();
                long result = timeB - timeA;
                System.out.println("Elapsed time: " + (result));
                times.add((int) result);
           // }
            int total = 0;
            int avg;
            for(int a = 0; a < times.size(); a++)
            {
                    total += times.get(a);
            }
            avg = total / times.size();
            System.out.println("The Average IS:" + avg);
    }
}