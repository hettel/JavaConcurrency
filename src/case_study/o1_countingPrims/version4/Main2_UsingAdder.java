package case_study.o1_countingPrims.version4;

import java.util.ArrayList;
import java.util.List;

import case_study.o1_countingPrims.version4.util.Adder;
import case_study.o1_countingPrims.version4.util.NumberBlock;

public class Main2_UsingAdder
{

  public static void main(String[] args) throws InterruptedException
  {
    System.out.println("Start search");
    long start = System.currentTimeMillis(); 
    
    NumberBlock numBlock = new NumberBlock(1_000_000);
    Adder adder = new Adder();
    
    final int numOfTasks = 4;
    
    List<Thread> threads = new ArrayList<>();
    for(int i=0; i<numOfTasks; i++)
    {
      TaskUsingAdder task = new TaskUsingAdder(2_000_000, numBlock, adder);
      threads.add( new Thread(task) );
    }
    
    threads.forEach( th -> th.start() );
    for(Thread th : threads )
    {
      th.join();
    }
    
    long end = System.currentTimeMillis();
 
    System.out.println("Duration " + (end - start) + " [ms]");
    System.out.println("Count " + adder.getValue() );
  }

}
