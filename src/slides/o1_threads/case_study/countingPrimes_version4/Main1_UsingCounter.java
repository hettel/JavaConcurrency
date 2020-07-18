package slides.o1_threads.case_study.countingPrimes_version4;

import java.util.ArrayList;
import java.util.List;

import slides.o1_threads.case_study.countingPrimes_version4.util.Counter;
import slides.o1_threads.case_study.countingPrimes_version4.util.NumberBlock;

public class Main1_UsingCounter
{

  public static void main(String[] args) throws InterruptedException
  {
    System.out.println("Start search");
    long start = System.currentTimeMillis(); 
    
    NumberBlock numBlock = new NumberBlock(1_000_000);
    Counter counter = new Counter();
    
    final int numOfTasks = 4;
    
    List<Thread> threads = new ArrayList<>();
    for(int i=0; i<numOfTasks; i++)
    {
      TaskUsingCounter task = new TaskUsingCounter(2_000_000, numBlock, counter);
      threads.add( new Thread(task) );
    }
    
    threads.forEach( th -> th.start() );
    for(Thread th : threads )
    {
      th.join();
    }
    
    long end = System.currentTimeMillis();
 
    System.out.println("Elapsed Time " + (end - start) + " [ms]");
    System.out.println("Count " + counter.getValue() );
  }

}
