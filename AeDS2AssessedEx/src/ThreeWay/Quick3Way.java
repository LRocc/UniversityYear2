package ThreeWay;

import edu.princeton.cs.algs4.StdRandom;

import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.Comparator;

public class Quick3Way{

    public static void sort(Comparable[] a)
    {
        StdRandom.shuffle(a);
       // System.out.println(Arrays.toString(a));
        sort(a, 0, a.length - 1);
    }

    public static void sort(Object[] a, Comparator c)
    {
        StdRandom.shuffle(a);
        sort(a, 0, a.length - 1, c);
    }


    public static void sort(Comparable[] a, int lo, int hi)
    {
        if (hi <= lo) return;

        // partition
        int i = lo;
        int lt = lo;
        int gt = hi;

        while (i <= gt){
            if 		(less(a[i], a[lt])) 	exch(a, i++, lt++);

            else if (less(a[lt], a[i]))		exch(a, i, gt--);
            else							i++;
        }

        sort(a, lo, lt - 1);
        sort(a, gt + 1, hi);
    }

    public static void sort(Object[] a, int lo, int hi, Comparator c)
    {
        if (hi <= lo) return;

        // partition
        int i = lo;
        int lt = lo;
        int gt = hi;

        while (i <= gt){
            if (less(a[i], a[lt], c)) 		exch(a, i++, lt++);
            else if (less(a[lt], a[i], c))	exch(a, i, gt--);
            else							i++;
        }

        sort(a, lo, lt - 1, c);
        sort(a, gt + 1, hi, c);
    }


    // test client
    public static void main(String[] args)
    {
        Integer[] arraypart = {	1,1,2,2,3,4,5,6,710,30,0,50,0,0,5,5,5,5,5,5,70,79,88,88,4,2,22,21};
        Integer[] arraysort = new Integer[arraypart.length];
        System.arraycopy(arraypart, 0, arraysort, 0, arraypart.length);

        System.out.print("Original:\t");
        System.out.println(Arrays.toString(arraypart));
        System.out.println();

        System.out.print("Full sort:\t");
        Quick3Way.sort(arraysort);
        System.out.println(Arrays.toString(arraysort));
        System.out.println();
    }

    // private
    private static void exch(Comparable[] a, int i, int j)
    {
        //System.out.println(Arrays.toString(a));
        Comparable tmp = a[i];
        a[i] = a[j];
        a[j] = tmp;
        //System.out.println(Arrays.toString(a));
    }

    private static void exch(Object[] a, int i, int j)
    {
        Object tmp = a[i];
        a[i] = a[j];
        a[j] = tmp;
    }


    private static boolean less(Comparable a, Comparable b)
    { return a.compareTo(b) < 0; }

    private static boolean less(Object a, Object b, Comparator c)
    { return c.compare(a, b) < 0; }
}