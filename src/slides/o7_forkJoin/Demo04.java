package slides.o7_forkJoin;

import java.util.Arrays;
import java.util.concurrent.RecursiveTask;
import java.util.concurrent.ThreadLocalRandom;

public class Demo04
{

  public static void main(String[] args)
  {
    int[] array = new int[1_000_000];
    Arrays.parallelSetAll(array, i -> ThreadLocalRandom.current().nextInt(10_000_000) );

    int maxValue = max(array,0,array.length);

    System.out.println("Max = " + maxValue );
  }
  
  private static final int THRESHOLD = 10;
  
  public static int max(int[] array, int start, int end) {   
    // work phase
    if( end - start < THRESHOLD ) {
       int maxIndex = start;
       for(int i=start+1; i<end; i++) {
         if( array[i] > array[maxIndex] ) {
           maxIndex = i;
         }
       }
       return array[maxIndex];
    }
    else {
      // split phase
      int mid = (start + end)/2;
      
      @SuppressWarnings("serial")
      RecursiveTask<Integer> leftTask = new RecursiveTask<>()
      {
        @Override
        protected Integer compute()
        {
          return max(array, start, mid);
        }
      };
      leftTask.fork();
      
      int rightValue = max(array, mid, end);
      
      
      // combine phase
      // return Math.max(leftT.jo, rightValue);
      return Math.max(leftTask.join(), rightValue);
    }
  }

}
