package Insertionsort;

public class CombineQuickSortInsertionSort {
    private long[] data;

    private int len;

    public CombineQuickSortInsertionSort(int max) {
        data = new long[max];
        len = 0;
    }

    public void insert(long value) {
        data[len] = value;
        len++;
    }

    public void display() {
        System.out.print("Data:");
        for (int j = 0; j < len; j++)
            System.out.print(data[j] + " ");
        System.out.println("");
    }

    public void quickSort() {
        recQuickSort(0, len - 1);
    }

    public void recQuickSort(int left, int right) {
        int size = right - left + 1;
        if (size < 11) // insertion sort if small
            insertionSort(left, right);
        else // quicksort if large
        {
            long median = medianOf3(left, right);
            int partition = partitionIt(left, right, median);
            recQuickSort(left, partition - 1);
            recQuickSort(partition + 1, right);
        }
    }

    public long medianOf3(int left, int right) {
        int center = (left + right) / 2;
        // order left & center
        if (data[left] > data[center])
            swap(left, center);
        // order left & right
        if (data[left] > data[right])
            swap(left, right);
        // order center & right
        if (data[center] > data[right])
            swap(center, right);

        swap(center, right - 1);
        return data[right - 1];
    }

    public void swap(int d1, int d2) {
        long temp = data[d1];
        data[d1] = data[d2];
        data[d2] = temp;
    }

    public int partitionIt(int left, int right, long pivot) {
        int leftPtr = left; // right of first elem
        int rightPtr = right - 1; // left of pivot
        while (true) {
            //find bigger
            while (data[++leftPtr] < pivot)
                ;
            //find smaller
            while (data[--rightPtr] > pivot)
                ;
            if (leftPtr >= rightPtr) // if pointers cross, partition done
                break;
            else
                swap(leftPtr, rightPtr);
        }
        swap(leftPtr, right - 1); // restore pivot
        return leftPtr; // return pivot location
    }

    public void insertionSort(int left, int right) {
        int in, out;
        //  sorted on left of out
        for (out = left + 1; out <= right; out++) {
            long temp = data[out]; // remove marked item
            in = out; // start shifts at out
            // until one is smaller,
            while (in > left && data[in - 1] >= temp) {
                data[in] = data[in - 1]; // shift item to right
                --in; // go left one position
            }
            data[in] = temp; // insert marked item
        }
    }

    public static void main(String[] args) {
        int maxSize = 16;
        CombineQuickSortInsertionSort arr = new CombineQuickSortInsertionSort(maxSize);

        for (int j = 0; j < maxSize; j++)
        {
            long n = (int) (java.lang.Math.random() * 99);
            arr.insert(n);
        }
        arr.display();
        long timeA = System.nanoTime();
        arr.quickSort();
        long timeB = System.nanoTime();
        arr.display();
        long result = timeB - timeA;
        System.out.println("Elapsed time: " + (result));
    }


    /**
     * TEST TO SWITCH CASE
     */


}