package case_study.o1_countingPrims.version1;

import java.util.ArrayList;
import java.util.List;

public class Main2
{
  public static void main(String[] args) throws InterruptedException
  {
    System.out.println("Start search");
    long startTime = System.currentTimeMillis(); 
    
    // Get number of available cores
    // Cores with hyper threading are counted twice
    final int numOfCores = Runtime.getRuntime().availableProcessors();
    
    // Use all cores
    final int numChunks = numOfCores;
    
    final int start = 1_000_000;
    final int end   = 2_000_000;
    
    // Create tasks
    List<Thread> threads = new ArrayList<>();
    final int delta = (end-start)/numChunks;
    for(int i=0; i < numChunks; i++)
    {
      // Note: the end value of the last task must be adjusted
      int startIndex = start + i*delta;
      int endIndex = i == numChunks-1 ? end : start + (i+1)*delta;
      Task task = new Task( startIndex,  endIndex);
      threads.add( new Thread( task) );
    }
    
    // Start tasks
    threads.forEach( th -> th.start() );
    
    // Wait until all tasks are finished
    for(Thread th : threads )
    {
      th.join();
    }
 
    long endTime = System.currentTimeMillis();
    System.out.println("Duration " + (endTime - startTime) + " [ms]");
  }
}
