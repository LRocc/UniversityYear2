public class Factorial
{
    public static int product;
   public static int fACT(int a)
   {
       if(a == 1)
       {
           return 1;
       }
       else{
           return a * fACT( a -1 );
       }


       /**
        * The complexity of this algorithm is O(n)
        */
   }


   public static int fACT_Iter(int a)
   {
       if(a == 1)
       {
           return 1;
       }

       for(int j = 1 ; j <= a ; j ++)
       {
           product *= j;
       }

       return product;
   }

    /**
     * complexity of fat iter is O9n)
     */




}
