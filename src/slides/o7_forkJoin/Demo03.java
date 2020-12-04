package slides.o7_forkJoin;

import java.util.Arrays;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.RecursiveTask;
import java.util.concurrent.ThreadLocalRandom;

public class Demo03
{
  private static final int THRESHOLD = 2;

  @SuppressWarnings("serial")
  static class Task extends RecursiveTask<Integer>
  {   
    private final int[] array;
    private final int start, end;

    Task(int[] array, int start, int end)
    {
      this.array = array;
      this.start = start;
      this.end = end;
    }

    @Override
    protected Integer compute()
    {
      if (end - start < THRESHOLD)
      {
        // Work phase
        int maxIndex = start;
        for (int i = start + 1; i < end; i++)
        {
          if (array[i] > array[maxIndex])
          {
            maxIndex = i;
          }
        }
        return array[maxIndex];
      }
      else
      {
        // splitting
        int mid = (start + end) / 2;
        
        // calculate left Task in parallel
        Task leftTask = new Task(array, start, mid);
        leftTask.fork();
        
        Task rightTask = new Task(array, mid, end);
        Integer rightValue = rightTask.compute();

        // combine
        return Math.max(leftTask.join(), rightValue );
      }
    }
  }

  public static void main(String[] args)
  {
    int[] array = new int[1_000_000];
    Arrays.parallelSetAll(array, i -> ThreadLocalRandom.current().nextInt(10_000_000) );

    ForkJoinPool executor = new ForkJoinPool(8);
    Task root = new Task(array, 0, array.length);
    executor.execute(root);  // non-blocking call

    // or using the build-in pool
    // ForkJoinPool.commonPool().execute(root);  // non-blocking
    // ForkJoinPool.commonPool().invoke(root);   // blocking

    System.out.println("Max " + root.join());
  }
}
