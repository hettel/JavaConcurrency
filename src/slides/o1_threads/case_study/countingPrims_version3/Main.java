package slides.o1_threads.case_study.countingPrims_version3;

import java.util.ArrayList;
import java.util.List;

public class Main
{

  public static void main(String[] args) throws InterruptedException
  {
    System.out.println("Start search");
    long start = System.currentTimeMillis(); 
    
    NumberBlock numBlock = new NumberBlock(1_000_000);
    
    final int numOfTasks = 4;
    
    List<Thread> threads = new ArrayList<>();
    for(int i=0; i<numOfTasks; i++)
    {
      Task task = new Task(2_000_000, numBlock);
      threads.add( new Thread(task) );
    }
    
    threads.forEach( th -> th.start() );
    for(Thread th : threads )
    {
      th.join();
    }
    
    long end = System.currentTimeMillis();
 
    System.out.println("Duration " + (end - start) + " [ms]");
  }

}
