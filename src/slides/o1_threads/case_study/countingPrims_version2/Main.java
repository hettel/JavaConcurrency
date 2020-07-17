package slides.o1_threads.case_study.countingPrims_version2;

import java.util.ArrayList;
import java.util.List;

public class Main
{

  public static void main(String[] args) throws InterruptedException
  {
    System.out.println("Start search");
    long startTime = System.currentTimeMillis(); 
        
    
    final int start = 1_000_000;
    final int end = 2_000_000;
    
    // Number of threads correspond to the step size of a task
    final int numOfThreads = 7;
    List<Thread> threads = new ArrayList<>();
    for(int i=0; i< numOfThreads; i++)
    {
      Task task = new Task(start+i, end, numOfThreads );
      threads.add( new Thread(task) );
    }
    
    // Start threads and wait until they are finished
    threads.forEach( th -> th.start() );
    for(Thread th : threads )
    {
      th.join();
    }
    
    long endTime = System.currentTimeMillis();
 
    System.out.println("Duration " + (endTime - startTime) + " [ms]");
  }

}
