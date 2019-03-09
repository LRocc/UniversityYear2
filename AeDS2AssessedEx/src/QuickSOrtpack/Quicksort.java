package QuickSOrtpack;

public class Quicksort
{
    public void qsort(int [] a,int p, int r)
    {
        if(p < r)
        {
            int q = partition(a,p,r);
            //System.out.println("pivot is " + p);
            qsort(a,p,q-1);
            //System.out.println("pivot is " + p);
            qsort(a,q+1,r);
            //System.out.println("pivot is " + p);
        }
    }
    private int partition(int[]a,int p,int r)
    {
        //pivot
        int x = a[r];
        int i = p - 1; //index of smaller element
        int temp = 0;

        for(int j = p ; j<r; j++)
        {
            // If current element is smaller than or
            // equal to pivot
            if(a[j]<=x)
            {
                i++; //increment index of smaller element
                temp = a[i];
                a[i] = a[j];
                a[j] = temp; //swapping
            }
        }
        temp = a[i+1];
        a[i+1] = a[r];
        a[r] = temp;
        return (i+1);

    }
}



